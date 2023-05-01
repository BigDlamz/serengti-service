package za.co.serengti.customers.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import za.co.serengti.customers.entity.Customer;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.Optional;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {

    @Transactional
    public <T extends Customer> T save(T customer) {
        persistAndFlush(customer);
        return customer;
    }

    public Optional<Customer> findByEmailAddress(String emailAddress) {
        return find("identifier_type = ?1 and email_address = ?2", "email_address", emailAddress).firstResultOptional();
    }

    public Optional<Customer> findByMobileNumber(String mobileNumber) {
        return find("identifier_type = ?1 and mobile_number = ?2", "mobile_number", mobileNumber).firstResultOptional();
    }

}
