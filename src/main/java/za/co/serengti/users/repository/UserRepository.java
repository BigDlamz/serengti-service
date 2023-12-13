package za.co.serengti.users.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import za.co.serengti.users.entity.User;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.Optional;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    @Transactional
    public <T extends User> T save(T user) {
        persistAndFlush(user);
        return user;
    }

    public Optional<User> findByEmailAddress(String emailAddress) {
        return find("identifierType = 'email_address' and emailAddress = ?1", emailAddress).firstResultOptional();
    }

    public Optional<User> findByMobileNumber(String mobileNumber) {
        return find("identifier_type = 'mobile_number' and mobileNumber = ?2", mobileNumber).firstResultOptional();
    }

}
