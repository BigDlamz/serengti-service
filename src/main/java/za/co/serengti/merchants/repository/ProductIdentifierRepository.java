package za.co.serengti.merchants.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import za.co.serengti.merchants.entity.POSSystem;
import za.co.serengti.merchants.entity.ProductIdentifier;
import za.co.serengti.merchants.entity.Store;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class ProductIdentifierRepository implements PanacheRepository<ProductIdentifier> {

    public ProductIdentifier findBySkuAndStoreIdAndPosSystemId(String sku, Store store, POSSystem posSystem) {

        Map<String, Object> params = new HashMap<>();
        params.put("sku", sku);
        params.put("store", store);
        params.put("posSystem", posSystem);

        return find("sku = :sku and store = :store and posSystem = :posSystem", params).firstResult();
    }


    }