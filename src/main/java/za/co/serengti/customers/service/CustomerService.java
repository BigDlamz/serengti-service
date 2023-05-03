package za.co.serengti.customers.service;

import za.co.serengti.customers.dto.CustomerDTO;
import za.co.serengti.customers.entity.Customer;
import za.co.serengti.customers.entity.EmailCustomer;
import za.co.serengti.customers.entity.MobileCustomer;
import za.co.serengti.customers.repository.CustomerRepository;
import za.co.serengti.util.RecordMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class CustomerService {

    final Identification customerIdentifier;
    final CustomerRepository customerRepo;
    final RecordMapper recordMapper;

    @Inject
    public CustomerService(Identification customerIdentifier, CustomerRepository customerRepository, RecordMapper recordMapper) {
        this.customerIdentifier = customerIdentifier;
        this.customerRepo = customerRepository;
        this.recordMapper = recordMapper;
    }

    @Transactional
    public CustomerDTO findOrSaveCustomer(String identifier) {
        Identification.Type type = customerIdentifier.determineIdentifierType(identifier);
        Customer customer = null;
        if (type == Identification.Type.EMAIL) {
            customer = customerRepo.findByEmailAddress(identifier)
                    .orElseGet(() -> customerRepo.save(new EmailCustomer("Philani", type.name(), identifier)));
        } else if (type == Identification.Type.MOBILE) {
            customer = customerRepo.findByMobileNumber(identifier)
                    .orElseGet(() -> customerRepo.save(new MobileCustomer("Philani", type.name(), identifier)));
        }
        return recordMapper.convert(customer, CustomerDTO.class);
    }
}
