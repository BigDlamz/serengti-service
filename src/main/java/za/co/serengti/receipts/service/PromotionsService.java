package za.co.serengti.receipts.service;

import za.co.serengti.receipts.entity.Promotions;
import za.co.serengti.receipts.repository.PromotionsRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class PromotionsService {
    private final PromotionsRepository promotionsRepository;

    @Inject
    public PromotionsService(PromotionsRepository promotionsRepository) {
        this.promotionsRepository = promotionsRepository;
    }

    public Promotions find(Long promotionsId) {
        return promotionsRepository.findById(promotionsId);
    }

    public Promotions save(Promotions promotions) {
        return promotionsRepository.save(promotions);
    }
}
