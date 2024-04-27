package za.co.serengti.receipts;

import jakarta.transaction.Transactional;

public interface CashierService {
    Cashier find(Long cashierId);

    @Transactional
    CashierDTO save(CashierDTO cashier);
}
