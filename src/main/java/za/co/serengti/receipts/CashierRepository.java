package za.co.serengti.receipts;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CashierRepository implements PanacheRepository<Cashier> {

    public Cashier save(Cashier cashier) {

        persistAndFlush(cashier);
        return cashier;

    }
}
