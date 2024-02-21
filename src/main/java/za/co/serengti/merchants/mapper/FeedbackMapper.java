package za.co.serengti.merchants.mapper;

import za.co.serengti.merchants.dto.FeedbackDTO;
import za.co.serengti.merchants.entity.Feedback;

import jakarta.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;

@ApplicationScoped
public class FeedbackMapper {

    public Feedback toEntity(FeedbackDTO feedbackDto) {
        Feedback feedback = new Feedback();
        feedback.setFeedbackId(feedbackDto.getFeedbackId());
        feedback.setReceiptId(feedbackDto.getReceiptId());
        feedback.setStarRating(feedbackDto.getStarRating());
        feedback.setUserComment(feedbackDto.getUserComment());
        feedback.setFeedbackDate(LocalDateTime.now());
        return feedback;
    }

    public FeedbackDTO toDto(Feedback feedback) {
        FeedbackDTO feedbackDto = new FeedbackDTO();
        feedbackDto.setFeedbackId(feedback.getFeedbackId());
        feedbackDto.setReceiptId(feedback.getReceiptId());
        feedbackDto.setStarRating(feedback.getStarRating());
        feedbackDto.setUserComment(feedback.getUserComment());
        return feedbackDto;
    }

}
