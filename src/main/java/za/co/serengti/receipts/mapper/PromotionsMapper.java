package za.co.serengti.receipts.mapper;

import za.co.serengti.receipts.dto.PromotionsDTO;
import za.co.serengti.receipts.entity.Promotions;

import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class PromotionsMapper {

    public Promotions toEntity(PromotionsDTO dto) {
        return Promotions
                .builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }

    public PromotionsDTO toDto(Promotions entity) {
        return PromotionsDTO.builder()
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }
}
