package za.co.serengti.merchants;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class SpecialRepository implements PanacheRepository<Special> {

    public List<Special> findByStoreId(Long merchantId) {

        return list("merchant.id", merchantId);

    }
}
