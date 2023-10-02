package za.co.serengti.receipts.service;

import za.co.serengti.receipts.dto.PromotionsDTO;
import za.co.serengti.receipts.entity.Promotions;
import za.co.serengti.receipts.mapper.PromotionsMapper;
import za.co.serengti.receipts.repository.PromotionsRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PromotionsService {
    private final PromotionsRepository promotionsRepository;
    private final PromotionsMapper promotionsMapper;


    @Inject
    public PromotionsService(PromotionsRepository promotionsRepository, PromotionsMapper promotionsMapper) {
        this.promotionsRepository = promotionsRepository;
        this.promotionsMapper = promotionsMapper;
    }

    public Promotions find(Long promotionsId) {
        return promotionsRepository.findById(promotionsId);
    }

    public Promotions save(Promotions promotions) {
        return promotionsRepository.save(promotions);
    }

    public PromotionsDTO toDto(Promotions entity) {
        return promotionsMapper.toDto(entity);
    }

    public Promotions toEntity(PromotionsDTO dto) {
        return promotionsMapper.toEntity(dto);
    }

}
