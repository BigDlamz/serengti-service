package za.co.serengti.customers.service;

import za.co.serengti.customers.entity.Customer;
import za.co.serengti.customers.entity.EmailCustomer;
import za.co.serengti.customers.entity.MobileCustomer;
import za.co.serengti.customers.repository.CustomerRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class CustomerService {

    final Identification customerIdentifier;
    final CustomerRepository customerRepo;

    @Inject
    public CustomerService(Identification customerIdentifier, CustomerRepository customerRepository) {
        this.customerIdentifier = customerIdentifier;
        this.customerRepo = customerRepository;
    }

    @Transactional
    public Customer findOrSaveCustomer(String identifier) {
        Identification.Type type = customerIdentifier.determineIdentifierType(identifier);
        Customer customer = null;
        if (type == Identification.Type.EMAIL) {
            customer = customerRepo.findByEmailAddress(identifier)
                    .orElseGet(() -> customerRepo.save(new EmailCustomer("Philani", type.name(), identifier)));
        } else if (type == Identification.Type.MOBILE) {
            customer = customerRepo.findByMobileNumber(identifier)
                    .orElseGet(() -> customerRepo.save(new MobileCustomer("Philani", type.name(), identifier)));
        }
        return customer;
    }
}
