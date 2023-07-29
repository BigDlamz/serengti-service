package za.co.serengti.merchants.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import za.co.serengti.merchants.entity.Product;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {

    public Optional<Product> findBySku(Long posSystemID, Long storeId, String sku) {
        return Optional.ofNullable(find("pos_system_id = ?0 and store_id = ?1 and sku = ?2", posSystemID, storeId, sku).firstResult());
    }

    public Product save(Product product) {
        persistAndFlush(product);
        return product;
    }
}
