package za.co.serengti.receipt.service;

import za.co.serengti.receipt.RecordMapper;
import za.co.serengti.receipt.domain.*;
import za.co.serengti.receipt.entity.*;
import za.co.serengti.receipt.repository.CustomerRepository;
import za.co.serengti.receipt.repository.POSRepository;
import za.co.serengti.receipt.repository.ReceiptRepository;
import za.co.serengti.receipt.repository.StoreRepository;
import za.co.serengti.receipt.util.Identification;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@ApplicationScoped
public class ReceiptManagementService {

    private final ReceiptRepository receiptRepository;
    private final RecordMapper converter;
    private final POSRepository posRepository;
    private final StoreRepository storeRepo;
    private final Identification customerIdentifier;
    private final CustomerRepository customerRepo;

    @Inject
    public ReceiptManagementService(ReceiptRepository receiptRepository, RecordMapper converter, POSRepository posRepository, StoreRepository storeRepo, Identification customerIdentifier, CustomerRepository customerRepo) {
        this.receiptRepository = receiptRepository;
        this.converter = converter;
        this.posRepository = posRepository;
        this.storeRepo = storeRepo;
        this.customerIdentifier = customerIdentifier;
        this.customerRepo = customerRepo;
    }

    /**
     * Create a new receipt and store it in the database
     */
    @Transactional
    public void process(Transaction tx) {
        var metaData = tx.getMetaData();
        POSSystemEntity posSystem = posRepository.findById(metaData.getPosSystem());
        StoreEntity store = storeRepo.findById(metaData.getStore());
        CustomerEntity customer = findOrSaveCustomer(tx.getCutomerIdentifier()).orElse(null);
        ReceiptEntity receipt = prepare(posSystem, store, customer, tx.getReceiptDetails());
        receiptRepository.merge(receipt);
    }

    /**
     * Assemble the receipt from the transaction details
     */

    private ReceiptEntity prepare(POSSystemEntity posSystem, StoreEntity store, CustomerEntity customer, ReceiptDetails details) {
        Receipt receipt = Receipt
                .builder()
                .timestamp(details.getTimestamp())
                .store(converter.map(store, Store.class))
                .posSystem(converter.map(posSystem, POSSystem.class))
                .customer(converter.map(customer, Customer.class))
                .receiptItems(details.getLineItems())
                .build();
                receipt.calculateTotalAmountPaid();
        return converter.map(receipt, ReceiptEntity.class);
    }

    /**
     * Search the database for the customer or save them if not found
     */
    @Transactional
    private Optional<CustomerEntity> findOrSaveCustomer(String identifier) {
        Identification.Type type = customerIdentifier.determineIdentifierType(identifier);
        Optional<CustomerEntity> customer = Optional.empty();
        if (type == Identification.Type.EMAIL) {
            customer = ofNullable(customerRepo.findByEmailAddress(identifier)
                    .orElseGet(() -> customerRepo.save(new EmailCustomerEntity("Philani", type.name(), identifier))));
        } else if (type == Identification.Type.MOBILE) {
            customer = ofNullable(customerRepo.findByMobileNumber(identifier)
                    .orElseGet(() -> customerRepo.save(new MobileCustomerEntity("Philani", type.name(), identifier))));
        }
        return customer;
    }

    /**
     * Retrieve a single receipt by ID
     */
    public RetrieveReceiptResponse findReceipt(Long ID) {
        ReceiptEntity receiptEntity = receiptRepository.findById(ID);
        return RetrieveReceiptResponse
                .builder()
                .receipt(converter.map(receiptEntity, Receipt.class))
                .build();
    }
}
