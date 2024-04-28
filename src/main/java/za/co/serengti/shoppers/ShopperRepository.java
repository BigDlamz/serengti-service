package za.co.serengti.shoppers;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
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

    public Optional<Shopper> findByEmailAddress(String email) {

        return find("emailAddress = ?1", email).firstResultOptional();

    }


}
