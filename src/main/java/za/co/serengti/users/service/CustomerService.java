package za.co.serengti.users.service;

import za.co.serengti.users.entity.User;
import za.co.serengti.users.entity.EmailUser;
import za.co.serengti.users.entity.MobileUser;
import za.co.serengti.users.repository.CustomerRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

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
    public User findOrSaveCustomer(String identifier) {
        Identification.Type type = customerIdentifier.determineIdentifierType(identifier);
        User user = null;
        if (type == Identification.Type.EMAIL) {
            user = customerRepo.findByEmailAddress(identifier)
                    .orElseGet(() -> customerRepo.save(new EmailUser("Philani", type.name(), identifier)));
        } else if (type == Identification.Type.MOBILE) {
            user = customerRepo.findByMobileNumber(identifier)
                    .orElseGet(() -> customerRepo.save(new MobileUser("Philani", type.name(), identifier)));
        }
        return user;
    }
}
