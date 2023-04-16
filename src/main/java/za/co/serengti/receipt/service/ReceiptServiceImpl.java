package za.co.serengti.receipt.service;

import za.co.serengti.receipt.EntityRecordMapper;
import za.co.serengti.receipt.dto.ReceiptDetailsDTO;
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

    @Inject
    ProductRepository productRepository;

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

        //determine customer identifier type
        var ID = request.getCutomerIdentifier();
        CustomerIdentifier.Type type = this.customerIdentifier.determineIdentifierType(ID);

        Optional<Customer> customer = null;
        if(type == CustomerIdentifier.Type.EMAIL_ADDRESS) {
            //lookup customer by email address
            customer = customerRepository.findByEmailAddress(request.getCutomerIdentifier());
            if(customer == null || customer.isEmpty()) {
                    //create new customer if not found
                    customerRepository.persist(new EmailAddressCustomer(ID));
            }
        } else if(type == CustomerIdentifier.Type.MOBILE_NUMBER) {
            //lookup customer by mobile number
            customer = customerRepository.findByMobileNumber(request.getCutomerIdentifier());
            if(customer == null || customer.isEmpty()){
                //create new mobile customer if not found
                customerRepository.persist(new MobileNumberCustomer(ID));
            }
        }

        ReceiptDetailsDTO receiptDetails = request.getReceiptDetails();

        Receipt receipt = converter.map(receiptDetails, Receipt.class);

        //get transaction time & amount paid
        receipt.timestamp = request.getReceiptDetails().getTimestamp();
        receipt.totalAmountPaid = receiptDetails.getTotalAmountPaid();

        receipt.receiptItems.forEach(item -> {
           productRepository.persist(item.getProduct());
        });

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
