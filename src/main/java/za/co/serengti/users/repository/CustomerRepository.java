package za.co.serengti.users.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import za.co.serengti.users.entity.User;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.Optional;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<User> {

    @Transactional
    public <T extends User> T save(T customer) {
        persistAndFlush(customer);
        return customer;
    }

    public Optional<User> findByEmailAddress(String emailAddress) {
        return find("identifier_type = ?1 and email_address = ?2", "email_address", emailAddress).firstResultOptional();
    }

    public Optional<User> findByMobileNumber(String mobileNumber) {
        return find("identifier_type = ?1 and mobile_number = ?2", "mobile_number", mobileNumber).firstResultOptional();
    }

}
