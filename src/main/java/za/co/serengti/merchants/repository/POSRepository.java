package za.co.serengti.merchants.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import za.co.serengti.merchants.entity.POSSystem;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class POSRepository implements PanacheRepository<POSSystem> {

    public Long save(POSSystem posSystem) {
        persistAndFlush(posSystem);
        return posSystem.posSystemID;
    }
}
