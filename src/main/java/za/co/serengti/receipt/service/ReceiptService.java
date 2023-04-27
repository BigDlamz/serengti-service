package za.co.serengti.receipt.service;

import za.co.serengti.receipt.RecordMapper;
import za.co.serengti.receipt.dto.*;
import za.co.serengti.receipt.entity.*;
import za.co.serengti.receipt.repository.*;
import za.co.serengti.receipt.service.request.Transaction;
import za.co.serengti.receipt.service.response.RetrieveReceiptResponse;
import za.co.serengti.receipt.util.CustomerIdentifier;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;

@ApplicationScoped
public class ReceiptService{

    @Inject
    ReceiptRepository receiptRepo;

    @Inject
    RecordMapper converter;

    @Inject
    POSRepository posRepository;

    @Inject
    StoreRepository storeRepo;

    @Inject
    CustomerIdentifier customerIdentifier;

    @Inject
    CustomerRepository customerRepo;

    public ReceiptService(ReceiptRepository receiptRepo, RecordMapper converter) {
        this.receiptRepo = receiptRepo;
        this.converter = converter;
    }

    /**
     * Create a new receipt and store it in the database
     */
    @Transactional
    public void save(Transaction transaction) {
        ReceiptDTO receiptDTO = extractDetails(transaction);
        var receipt = covertToEntity(receiptDTO);
        Receipt savedReceit = receiptRepo.merge(receipt);
    }

    /**
     * Convert the receiptDTO to a receipt entity
     */
    private Receipt covertToEntity(ReceiptDTO receiptDTO) {
        return converter.map(receiptDTO, Receipt.class);
    }

    /**
     * Search the database for the customer or save them if not found
     */
    @Transactional
    private Optional<Customer> findOrSaveNewCustomer(String identifier) {

        CustomerIdentifier.Type type = customerIdentifier.determineIdentifierType(identifier);
        Optional<Customer> customer = Optional.empty();

        if (type == CustomerIdentifier.Type.EMAIL) {
            customer = customerRepo.findByEmailAddress(identifier);
            if (customer.isEmpty()) {
                customer = Optional.of(customerRepo.save(new EmailAddressCustomer("Philani", type.name(), identifier)));
            }
        } else if (type == CustomerIdentifier.Type.MOBILE) {
            customer = customerRepo.findByMobileNumber(identifier);
            if (customer.isEmpty()) {
                customer = Optional.of(customerRepo.save(new MobileNumberCustomer("Philani", type.name(), identifier)));
            }
        }
        return customer;
    }


    /**
     * Assemble the receipt object graph relationships
     */
    private ReceiptDTO extractDetails(Transaction transaction) {
        final POSSystem posSystem = posRepository.findById(transaction.getMetaData().getPosSystem());
        final Store store = storeRepo.findById(transaction.getMetaData().getStore());
        final Optional<Customer> customer = findOrSaveNewCustomer(transaction.getCutomerIdentifier());

        return ReceiptDTO
                .builder()
                .timestamp(transaction.getReceiptDetails().getTimestamp())
                .totalAmountPaid(transaction.getReceiptDetails().getTotalAmountPaid())
                .store(converter.map(store, StoreDTO.class))
                .posSystem(converter.map(posSystem, POSSystemDTO.class))
                .customer(converter.map(customer.get(), CustomerDTO.class))
                .receiptItems(Collections.singletonList(converter.map(transaction.getReceiptDetails().getLineItems(), ReceiptItemDTO.class)))
                .build();
    }

    /**
     * Retrieve a single receipt by ID
     */
    public RetrieveReceiptResponse retrieveReceipt(Long ID) {
        Receipt receipt = receiptRepo.findById(ID);
        return RetrieveReceiptResponse
                .builder()
                .receipt(converter.map(receipt, ReceiptDetails.class))
                .build();
    }
}
