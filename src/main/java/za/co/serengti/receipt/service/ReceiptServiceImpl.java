package za.co.serengti.receipt.service;

import za.co.serengti.receipt.EntityRecordMapper;
import za.co.serengti.receipt.dto.ReceiptDetailsDTO;
import za.co.serengti.receipt.entity.POSSystem;
import za.co.serengti.receipt.entity.Receipt;
import za.co.serengti.receipt.entity.Store;
import za.co.serengti.receipt.repository.CustomerRepository;
import za.co.serengti.receipt.repository.POSRepository;
import za.co.serengti.receipt.repository.ReceiptRepository;
import za.co.serengti.receipt.repository.StoreRepository;
import za.co.serengti.receipt.service.request.ReceiptRequest;
import za.co.serengti.receipt.service.response.RetrieveReceiptResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class ReceiptServiceImpl implements ReceiptService {

    @Inject
    ReceiptRepository repository;

    @Inject
    EntityRecordMapper converter;

    @Inject
    POSRepository posRepository;

    @Inject
    StoreRepository storeRepository;

    @Inject
    CustomerRepository customerRepository;

    public ReceiptServiceImpl(ReceiptRepository repository, EntityRecordMapper converter) {
        this.repository = repository;
        this.converter = converter;
    }

    /**
     * Create a new receipt and store it in the database
     */
    @Override
    @Transactional
    public void save(ReceiptRequest request) {
        //get POS & Store details
        POSSystem posSystem = posRepository.findById(request.getMetaData().getPosSystem());
        Store store = storeRepository.findById(request.getMetaData().getStore());

        //get customer details
   //     Customer customer = customerRepository.findById(request.getCutomerIdentifier());

        //prepare receipt
        Receipt receipt = converter.map(request.getReceiptDetails(), Receipt.class);

        repository.persist(receipt);
    }

    /**
     * Retrieve a single receipt by ID
     */
    @Override
    public RetrieveReceiptResponse retrieveReceipt(Long receiptID) {
        Receipt receipt = repository.findById(receiptID);
        return RetrieveReceiptResponse
                .builder()
                .receipt(converter.map(receipt, ReceiptDetailsDTO.class))
                .build();
    }
}
