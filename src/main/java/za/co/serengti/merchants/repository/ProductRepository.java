package za.co.serengti.merchants.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import za.co.serengti.merchants.entity.Product;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {

    public Product findByPosStoreAndSku(Long posSystemID, Long storeId, String sku) {
        return find("pos_system_id = ?1 and store_id = ?2 and sku = ?3", posSystemID, storeId, sku).firstResult();
    }

    public Product save(Product product) {
        persistAndFlush(product);
        return product;
    }
}
