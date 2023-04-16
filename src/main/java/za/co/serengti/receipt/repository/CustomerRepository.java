package za.co.serengti.receipt.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import za.co.serengti.receipt.entity.Customer;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {

    public Optional<Customer> findByEmailAddress(String emailAddress) {
        return find("identifier_type = ?1 and email_address = ?2", "email_address", emailAddress).firstResultOptional();
    }

    public Optional<Customer> findByMobileNumber(String mobileNumber) {
        return find("identifier_type = ?1 and mobile_number = ?2", "mobile_number", mobileNumber).firstResultOptional();
    }

}
