package za.co.serengti.merchants;

import java.util.List;

public interface MerchantService {

    MerchantDTO find(Long merchantId);

    List<SpecialDTO> retrieveSpecials(Long merchantId);

    List<FeedbackDTO> retrieveFeedback(Long storeId);

    void saveFeedback(FeedbackDTO feedbackDTO);

    Double retrieveAverageRating(Long merchantId);

    boolean isRated(Long receiptId);

    List<FeedbackDTO> retrieveLatestFeedback(Long storeId);
}
