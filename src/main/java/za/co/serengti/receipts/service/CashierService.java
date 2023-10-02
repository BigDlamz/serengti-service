package za.co.serengti.receipts.service;

import za.co.serengti.receipts.dto.CashierDTO;
import za.co.serengti.receipts.entity.Cashier;
import za.co.serengti.receipts.mapper.CashierMapper;
import za.co.serengti.receipts.repository.CashierRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CashierService {
    private final CashierRepository cashierRepository;
    private final CashierMapper cashierMapper;

    @Inject
    public CashierService(CashierRepository cashierRepository, CashierMapper cashierMapper) {
        this.cashierRepository = cashierRepository;
        this.cashierMapper = cashierMapper;
    }

    public Cashier find(Long cashierId) {
        return cashierRepository.findById(cashierId);
    }

    public Cashier save(Cashier cashier) {
        return cashierRepository.save(cashier);
    }

    public CashierDTO toDto(Cashier entity) {
        return cashierMapper.toDto(entity);
    }
    public Cashier toEntity(CashierDTO dto) {
        return cashierMapper.toEntity(dto);
    }

}
