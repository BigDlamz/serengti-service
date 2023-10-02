package za.co.serengti.merchants.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import za.co.serengti.merchants.entity.MetaData;
import za.co.serengti.merchants.entity.ProductIdentifier;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class ProductIdentifierRepository implements PanacheRepository<ProductIdentifier> {

    public ProductIdentifier findBySkuAndPosSystemAndStore(String sku, MetaData meta) {

        Map<String, Object> params = new HashMap<>();
        params.put("sku", sku);
        params.put("posSystem", meta.getPosSystem());
        params.put("store", meta.getStore());

        return find("sku = :sku and posSystem = :posSystem and store = :store", params).firstResult();
    }

    }