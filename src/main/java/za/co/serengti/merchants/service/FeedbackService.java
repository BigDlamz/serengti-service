package za.co.serengti.merchants.service;

import lombok.extern.slf4j.Slf4j;
import za.co.serengti.merchants.entity.Feedback;
import za.co.serengti.merchants.repository.FeedbackRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
@Slf4j
public class FeedbackService {

    @Inject
    FeedbackRepository feedbackRepository;

    @Inject
    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public void addFeedback(Feedback feedback) {
        log.info("Adding feedback for receiptId: {} payload: {}", feedback.getReceiptId(), feedback);
        feedbackRepository.storeFeedback(feedback);
    }

    public List<Feedback> getFeedbacksForStore(Long storeId) {
        return feedbackRepository.getFeedbackForStore(storeId);
    }

    public Double getAverageRatingForStore(Long storeId) {
        return feedbackRepository.getAverageRatingForStore(storeId);
    }
}
