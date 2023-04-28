package za.co.serengti.receipt.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import za.co.serengti.receipt.entity.CustomerEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.Optional;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<CustomerEntity> {

    @Transactional
    public <T extends CustomerEntity> T save(T customer) {
        persistAndFlush(customer);
        return customer;
    }

    public Optional<CustomerEntity> findByEmailAddress(String emailAddress) {
        return find("identifier_type = ?1 and email_address = ?2", "email_address", emailAddress).firstResultOptional();
    }

    public Optional<CustomerEntity> findByMobileNumber(String mobileNumber) {
        return find("identifier_type = ?1 and mobile_number = ?2", "mobile_number", mobileNumber).firstResultOptional();
    }

}
