package za.co.serengti.receipts;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CashierMapper {

    public Cashier toEntity(CashierDTO dto) {
        return Cashier
                .builder()
                .cashierId(dto.getCashierId())
                .name(dto.getName())
                .surname(dto.getSurname())
                .build();
    }

    public CashierDTO toDTO(Cashier entity) {
        return CashierDTO.builder()
                .cashierId(entity.getCashierId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .build();
    }
}
