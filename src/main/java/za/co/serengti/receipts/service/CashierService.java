package za.co.serengti.receipts.service;

import za.co.serengti.receipts.entity.Cashier;
import za.co.serengti.receipts.repository.CashierRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CashierService {
    private final CashierRepository cashierRepository;

    @Inject
    public CashierService(CashierRepository cashierRepository) {
        this.cashierRepository = cashierRepository;
    }

    public Cashier find(Long cashierId) {
        return cashierRepository.findById(cashierId);
    }

    public Cashier save(Cashier cashier) {
        return cashierRepository.save(cashier);
    }

}
