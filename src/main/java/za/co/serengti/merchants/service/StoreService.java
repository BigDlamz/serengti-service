package za.co.serengti.merchants.service;

import za.co.serengti.merchants.entity.Store;
import za.co.serengti.merchants.repository.StoreRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class StoreService implements StoreServiceInterface{

    private final FeedbackService feedbackService;
    private final StoreRepository storeRepository;

    @Inject
    public StoreService(FeedbackService feedbackService, StoreRepository storeRepository) {
        this.feedbackService = feedbackService;
        this.storeRepository = storeRepository;

    }

    public Store find(Long storeId) {
        return storeRepository.findById(storeId);
    }

}
