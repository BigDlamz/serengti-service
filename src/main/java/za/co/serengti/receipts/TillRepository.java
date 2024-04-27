package za.co.serengti.receipts;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TillRepository implements PanacheRepository<Till> {

    public Till save(Till till) {
        persistAndFlush(till);
        return till;
    }

}
