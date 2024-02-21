package za.co.serengti.shoppers.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import za.co.serengti.shoppers.entity.Shopper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.Optional;

@ApplicationScoped
public class ShopperRepository implements PanacheRepository<Shopper> {

    @Transactional
    public <T extends Shopper> T save(T user) {
        persistAndFlush(user);
        return user;
    }

    public Optional<Shopper> findByEmailAddress(String emailAddress) {
        return find("identifierType = 'email_address' and emailAddress = ?1", emailAddress).firstResultOptional();
    }

    public Optional<Shopper> findByMobileNumber(String mobileNumber) {
        return find("identifier_type = 'mobile_number' and mobileNumber = ?2", mobileNumber).firstResultOptional();
    }

}
