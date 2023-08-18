package za.co.serengti.merchants.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import za.co.serengti.merchants.entity.Special;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class SpecialRepository implements PanacheRepository<Special> {

    public List<Special> findByStoreId(Long storeId) {
        return list("store.id", storeId);
    }
}
