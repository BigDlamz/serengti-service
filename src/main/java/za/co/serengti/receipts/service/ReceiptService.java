package za.co.serengti.receipts.service;

import za.co.serengti.customers.domain.Customer;
import za.co.serengti.customers.entity.CustomerEntity;
import za.co.serengti.customers.service.CustomerService;
import za.co.serengti.merchants.MerchantService;
import za.co.serengti.receipts.RecordMapper;
import za.co.serengti.receipts.domain.POSSystem;
import za.co.serengti.receipts.domain.Receipt;
import za.co.serengti.receipts.domain.ReceiptDetails;
import za.co.serengti.receipts.domain.Store;
import za.co.serengti.receipts.entity.POSSystemEntity;
import za.co.serengti.receipts.entity.ReceiptEntity;
import za.co.serengti.receipts.entity.StoreEntity;
import za.co.serengti.receipts.repository.ReceiptRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final CustomerService customerService;
    private final MerchantService merchantService;
    private final RecordMapper converter;

    @Inject
    public ReceiptService(ReceiptRepository receiptRepository, RecordMapper converter, CustomerService customerService, MerchantService merchantService) {
        this.receiptRepository = receiptRepository;
        this.converter = converter;
        this.customerService = customerService;
        this.merchantService = merchantService;
    }

    /**
     * Create a new receipt and store it in the database
     */
    @Transactional
    public void process(Transaction tx) {
        var metaData = tx.getMetaData();
        POSSystemEntity posSystem = merchantService.findPOS(metaData.getPosSystemID());
        StoreEntity store = merchantService.findStore(metaData.getStoreID());
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
