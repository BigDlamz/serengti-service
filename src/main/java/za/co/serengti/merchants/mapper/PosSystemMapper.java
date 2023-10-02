package za.co.serengti.merchants.mapper;

import za.co.serengti.merchants.dto.POSSystemDTO;
import za.co.serengti.merchants.entity.POSSystem;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PosSystemMapper {

    public POSSystem toEntity(POSSystemDTO dto) {
        return POSSystem.builder()
                .posSystemID(dto.getPosSystemID())
                .name(dto.getName())
                .version(dto.getVersion())
                .build();
    }

    public POSSystemDTO toDto(POSSystem entity) {
        return POSSystemDTO.builder()
                .posSystemID(entity.getPosSystemID())
                .name(entity.getName())
                .version(entity.getVersion())
                .build();
    }
}
