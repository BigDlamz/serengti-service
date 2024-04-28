package za.co.serengti.receipts;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CashierServiceImpl implements CashierService {

    private final CashierRepository repository;
    private final CashierMapper convertor;

    @Inject
    public CashierServiceImpl(CashierRepository repository, CashierMapper convertor) {
        this.repository = repository;
        this.convertor = convertor;
    }

    @Override
    public Cashier find(Long cashierId) {

        return repository.findById(cashierId);

    }

    @Override
    @Transactional
    public CashierDTO save(CashierDTO cashier) {

        Cashier entity = convertor.toEntity(cashier);
        var savedEntity = repository.save(entity);
        return convertor.toDTO(savedEntity);

    }

}
