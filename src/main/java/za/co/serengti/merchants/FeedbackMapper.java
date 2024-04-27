package za.co.serengti.merchants;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDateTime;

@ApplicationScoped
public class FeedbackMapper {

    public Feedback toEntity(FeedbackDTO saveFeedbackDTO) {

        Feedback feedback = new Feedback();
        feedback.setReceiptId(saveFeedbackDTO.getReceiptId());
        feedback.setRating(saveFeedbackDTO.getStarRating());
        feedback.setUserComment(saveFeedbackDTO.getUserComment());
        feedback.setFeedbackDate(LocalDateTime.now());
        return feedback;

    }

    public FeedbackDTO toDTO(Feedback feedback) {

        FeedbackDTO saveFeedbackDTO = new FeedbackDTO();
        saveFeedbackDTO.setReceiptId(feedback.getReceiptId());
        saveFeedbackDTO.setStarRating(feedback.getRating());
        saveFeedbackDTO.setUserComment(feedback.getUserComment());
        return saveFeedbackDTO;

    }

}
