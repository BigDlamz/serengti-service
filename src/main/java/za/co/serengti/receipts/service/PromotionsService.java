package za.co.serengti.receipts.service;

import za.co.serengti.receipts.dto.PromotionsDTO;
import za.co.serengti.receipts.entity.Promotions;
import za.co.serengti.receipts.repository.PromotionsRepository;
import za.co.serengti.util.RecordMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class PromotionsService {
    private final PromotionsRepository promotionsRepository;
    private final RecordMapper mapper;

    @Inject
    public PromotionsService(PromotionsRepository promotionsRepository, RecordMapper mapper) {
        this.promotionsRepository = promotionsRepository;
        this.mapper = mapper;
    }

    public PromotionsDTO find(Long promotionsId) {
        Promotions promotions = promotionsRepository.findById(promotionsId);
        return mapper.convert(promotions, PromotionsDTO.class);
    }
}
