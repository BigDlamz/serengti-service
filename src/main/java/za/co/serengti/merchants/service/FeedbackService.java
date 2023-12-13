package za.co.serengti.merchants.service;

import lombok.extern.slf4j.Slf4j;
import za.co.serengti.merchants.entity.Feedback;
import za.co.serengti.merchants.repository.FeedbackRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
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

    public List<Feedback> getFeedbackForStore(Long storeId) {
        return feedbackRepository.findFeedbackForStore(storeId);
    }

    public Double getAverageRatingForStore(Long storeId) {
        Double averageRating = feedbackRepository.findAverageRatingForStore(storeId);
        if (averageRating == null || averageRating.isNaN()) {
            return 0.0;
        }
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.#", symbols);
        return Double.parseDouble(df.format(feedbackRepository.findAverageRatingForStore(storeId)));
    }

    public boolean hasBeenRated(Long receiptId) {
        Feedback feedback = feedbackRepository.findFeedbackForReceipt(receiptId);
        return Objects.nonNull(feedback);
    }

    public List<Feedback> getLatestFeedbackForStore(Long storeId) {
        return feedbackRepository.findTopFeedbacksForStore(storeId);
    }
}
