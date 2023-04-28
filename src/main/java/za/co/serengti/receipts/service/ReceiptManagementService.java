package za.co.serengti.receipts.service;

import za.co.serengti.customers.domain.Customer;
import za.co.serengti.customers.entity.CustomerEntity;
import za.co.serengti.customers.service.CustomerService;
import za.co.serengti.receipts.RecordMapper;
import za.co.serengti.receipts.domain.POSSystem;
import za.co.serengti.receipts.domain.Receipt;
import za.co.serengti.receipts.domain.ReceiptDetails;
import za.co.serengti.receipts.domain.Store;
import za.co.serengti.receipts.entity.POSSystemEntity;
import za.co.serengti.receipts.entity.ReceiptEntity;
import za.co.serengti.receipts.entity.StoreEntity;
import za.co.serengti.receipts.repository.POSRepository;
import za.co.serengti.receipts.repository.ReceiptRepository;
import za.co.serengti.receipts.repository.StoreRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class ReceiptManagementService {

    private final ReceiptRepository receiptRepository;
    private final RecordMapper converter;
    private final POSRepository posRepository;
    private final StoreRepository storeRepo;
    private final CustomerService customerService;

    @Inject
    public ReceiptManagementService(ReceiptRepository receiptRepository, RecordMapper converter, POSRepository posRepository, StoreRepository storeRepo, CustomerService customerService) {
        this.receiptRepository = receiptRepository;
        this.converter = converter;
        this.posRepository = posRepository;
        this.storeRepo = storeRepo;
        this.customerService = customerService;
    }

    /**
     * Create a new receipt and store it in the database
     */
    @Transactional
    public void process(Transaction tx) {
        var metaData = tx.getMetaData();
        POSSystemEntity posSystem = posRepository.findById(metaData.getPosSystem());
        StoreEntity store = storeRepo.findById(metaData.getStore());
        CustomerEntity customer = customerService.findOrSaveCustomer(tx.getCutomerIdentifier()).orElse(null);
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
