package za.co.serengti.receipts.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import za.co.serengti.receipts.entity.Till;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TillRepository implements PanacheRepository<Till> {

    public Till save(Till till) {
        persistAndFlush(till);
        return till;
    }

}
