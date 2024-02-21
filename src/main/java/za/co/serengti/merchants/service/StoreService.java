package za.co.serengti.merchants.service;

import za.co.serengti.merchants.entity.Feedback;
import za.co.serengti.merchants.entity.Special;
import za.co.serengti.merchants.entity.Store;
import za.co.serengti.merchants.repository.StoreRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class StoreService{

    private final FeedbackService feedbackService;
    private final SpecialService specialService;
    private final StoreRepository storeRepository;

    @Inject
    public StoreService(FeedbackService feedbackService, SpecialService specialService, StoreRepository storeRepository) {
        this.feedbackService = feedbackService;
        this.specialService = specialService;
        this.storeRepository = storeRepository;

    }

    public Store find(Long storeId) {
        return storeRepository.findById(storeId);
    }

    public List<Special> retrieveSpecials(Long storeId){
        return specialService.findSpecials(storeId);
    }

    public List<Feedback> retrieveFeedback(Long storeId){
        return feedbackService.getFeedback(storeId);
    }

    public void saveFeedback(Feedback feedback){
        feedbackService.saveFeedback(feedback);
    }

    public Double retrieveAverageRating(Long storeId){
        return feedbackService.getAverageRating(storeId);
    }

    public boolean hasServiceBeenRated(Long receiptId){
        return feedbackService.hasServiceBeenRated(receiptId);
    }

    public List<Feedback> retrieveTopFeedback(Long storeId){
        return feedbackService.getTopFeedback(storeId);
    }

}
