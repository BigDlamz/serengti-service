package za.co.serengti.customers.service;

import za.co.serengti.customers.entity.CustomerEntity;
import za.co.serengti.customers.entity.EmailCustomerEntity;
import za.co.serengti.customers.entity.MobileCustomerEntity;
import za.co.serengti.customers.repository.CustomerRepository;
import za.co.serengti.receipts.util.Identification;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@ApplicationScoped
public class CustomerService {

    final Identification customerIdentifier;
    final CustomerRepository customerRepo;

    public CustomerService(Identification customerIdentifier, CustomerRepository customerRepo) {
        this.customerIdentifier = customerIdentifier;
        this.customerRepo = customerRepo;
    }

    /**
     * Search the database for the customer or save them if not found
     */
    @Transactional
    public Optional<CustomerEntity> findOrSaveCustomer(String identifier) {
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
}
