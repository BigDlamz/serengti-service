package za.co.serengti.receipts.service;

import za.co.serengti.receipts.dto.CashierDTO;
import za.co.serengti.receipts.entity.Cashier;
import za.co.serengti.receipts.repository.CashierRepository;
import za.co.serengti.util.RecordMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CashierService {
    private final CashierRepository cashierRepository;
    private final RecordMapper mapper;

    @Inject
    public CashierService(CashierRepository cashierRepository, RecordMapper mapper) {
        this.cashierRepository = cashierRepository;
        this.mapper = mapper;
    }

    public CashierDTO find(Long cashierId) {
        Cashier cashier = cashierRepository.findById(cashierId);
        return mapper.convert(cashier, CashierDTO.class);
    }

    public CashierDTO save(CashierDTO cashierDTO) {
        Cashier cashier = cashierRepository.save(mapper.convert(cashierDTO, Cashier.class));
        return mapper.convert(cashier, CashierDTO.class);
    }

}
