package za.co.serengti.receipts;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CashierServiceImpl implements CashierService {

    private final CashierRepository cashierRepository;
    private final CashierMapper convertor;

    @Inject
    public CashierServiceImpl(CashierRepository cashierRepository, CashierMapper convertor) {
        this.cashierRepository = cashierRepository;
        this.convertor = convertor;
    }

    @Override
    public Cashier find(Long cashierId) {

        return cashierRepository.findById(cashierId);

    }

    @Override
    @Transactional
    public CashierDTO save(CashierDTO cashier) {

        Cashier entity = convertor.toEntity(cashier);
        var savedEntity = cashierRepository.save(entity);
        return convertor.toDTO(savedEntity);

    }

}
