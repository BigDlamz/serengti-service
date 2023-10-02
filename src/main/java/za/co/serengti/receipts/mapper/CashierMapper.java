package za.co.serengti.receipts.mapper;

import za.co.serengti.receipts.dto.CashierDTO;
import za.co.serengti.receipts.entity.Cashier;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CashierMapper {

    public Cashier toEntity(CashierDTO dto) {
        return Cashier
                .builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .build();
    }

    public CashierDTO toDto(Cashier entity) {
        return CashierDTO.builder()
                .name(entity.getName())
                .surname(entity.getSurname())
                .build();
    }
}
