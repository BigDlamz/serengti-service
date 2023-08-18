package za.co.serengti.receipts.mapper;

import za.co.serengti.merchants.entity.MetaData;
import za.co.serengti.receipts.dto.TillDTO;
import za.co.serengti.receipts.entity.Till;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TillMapper {

    public Till toEntity(TillDTO dto, MetaData meta) {
        return Till
                .builder()
                .posSystem(meta.getPosSystem())
                .store(meta.getStore())
                .tillNumber(dto.getTillNumber())
                .build();

    }

    public TillDTO toDto(Till entity) {
        return TillDTO
                .builder()
                .tillNumber(entity.getTillNumber())
                .build();
    }
}
