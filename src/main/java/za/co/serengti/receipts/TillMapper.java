package za.co.serengti.receipts;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TillMapper {

    public Till toEntity(TillDTO dto) {

        return Till
                .builder()
                .tillId(dto.getTillId())
                .tillNumber(dto.getTillNo())
                .build();

    }

    public TillDTO toDto(Till entity) {

        return TillDTO
                .builder()
                .tillId(entity.getTillId())
                .tillNo(entity.getTillNumber())
                .build();
    }
}
