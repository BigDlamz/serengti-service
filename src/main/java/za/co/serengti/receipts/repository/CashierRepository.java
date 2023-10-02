package za.co.serengti.receipts.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import za.co.serengti.receipts.entity.Cashier;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CashierRepository implements PanacheRepository<Cashier> {

    public Cashier save(Cashier cashier) {
        persistAndFlush(cashier);
        return cashier;
    }
}
