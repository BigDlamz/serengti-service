package za.co.serengti.merchants.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import za.co.serengti.merchants.entity.Product;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {

    public Optional<Product> findBySku(String sku, Long posSystemID, Long storeId) {
        return Optional.ofNullable(find("sku = ?0 and pos_system_id = ?1 and store_id = ?2", sku, posSystemID, storeId).firstResult());
    }

    public Product save(Product product) {
        persistAndFlush(product);
        return product;
    }
}
