package za.co.serengti.receipt.service;

import za.co.serengti.receipt.EntityRecordMapper;
import za.co.serengti.receipt.dto.*;
import za.co.serengti.receipt.entity.*;
import za.co.serengti.receipt.repository.*;
import za.co.serengti.receipt.service.request.ReceiptRequest;
import za.co.serengti.receipt.service.response.RetrieveReceiptResponse;
import za.co.serengti.receipt.util.CustomerIdentifier;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Optional;

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
    CustomerIdentifier customerIdentifier;

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

        ReceiptDetails receiptDetails = request.getReceiptDetails();

        ReceiptDTO receiptDTO = assembleReceipt(request);

        Receipt receipt = converter.map(receiptDTO, Receipt.class);

        receipt.setTimestamp(receiptDetails.getTimestamp());
        receipt.setTotalAmountPaid(receiptDetails.getTotalAmountPaid());

        repository.persist(receipt);
    }

    /**
     * Search the database for the customer or save them if not found
     */
    private Optional<Customer> findOrSaveNewCustomer(ReceiptRequest request) {

        var identifier = request.getCutomerIdentifier();
        CustomerIdentifier.Type type = this.customerIdentifier.determineIdentifierType(identifier);
        Optional<Customer> customer = Optional.empty();

        if (type == CustomerIdentifier.Type.EMAIL_ADDRESS) {
            customer = customerRepository.findByEmailAddress(request.getCutomerIdentifier());
            if (customer.isEmpty()) {
                customerRepository.persist(new EmailAddressCustomer(null,"Philani","identifier","email_address"));
            }
        } else if (type == CustomerIdentifier.Type.MOBILE_NUMBER) {
            customer = customerRepository.findByMobileNumber(request.getCutomerIdentifier());
            if (customer.isEmpty()) {
                customerRepository.persist(new EmailAddressCustomer(null,"Philani","identifier","email_address"));

            }
        }
        return customer;
    }


    /**
     * Assemble the receipt object graph relationships
     */
    private ReceiptDTO assembleReceipt(ReceiptRequest request) {
        POSSystem posSystem = posRepository.findById(request.getMetaData().getPosSystem());
        Store store = storeRepository.findById(request.getMetaData().getStore());
        Optional<Customer> customer = findOrSaveNewCustomer(request);
        return ReceiptDTO
                .builder()
                .store(converter.map(store, StoreDTO.class))
                .posSystem(converter.map(posSystem, POSSystemDTO.class))
                .customer(converter.map(customer.get(), CustomerDTO.class))
                .receiptItems(request.getReceiptDetails().getLineItems())
                .build();
    }

    /**
     * Retrieve a single receipt by ID
     */
    @Override
    public RetrieveReceiptResponse retrieveReceipt(Long receiptID) {
        Receipt receipt = repository.findById(receiptID);
        return RetrieveReceiptResponse
                .builder()
                .receipt(converter.map(receipt, ReceiptDetails.class))
                .build();
    }
}
