package za.co.serengti.merchants.service;

import lombok.extern.slf4j.Slf4j;
import za.co.serengti.merchants.entity.Feedback;
import za.co.serengti.merchants.repository.FeedbackRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
@Slf4j
public class FeedbackService {


    FeedbackRepository feedbackRepository;

    @Inject
    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public void addFeedback(Feedback feedback) {
        log.info("Adding feedback for receiptId: {} payload: {}", feedback.getReceiptId(), feedback);
        feedbackRepository.saveFeedback(feedback);
    }

    public List<Feedback> getFeedbacksForStore(Long storeId) {
        return feedbackRepository.findFeedbackForStore(storeId);
    }

    public Double getAverageRatingForStore(Long storeId) {
        return feedbackRepository.findAverageRatingForStore(storeId);
    }

    public boolean hasUserGivenFeedbackForReceipt(Long receiptId) {
        Feedback feedback = feedbackRepository.findFeedbackForReceipt(receiptId);
        return Objects.nonNull(feedback);
    }

    public List<Feedback> getTop5FeedbacksForStore(Long storeId) {
        return feedbackRepository.findTopFeedbacksForStore(storeId);
    }
}
