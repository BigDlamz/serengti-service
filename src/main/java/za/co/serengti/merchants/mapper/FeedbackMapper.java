package za.co.serengti.merchants.mapper;

import za.co.serengti.merchants.dto.FeedbackDTO;
import za.co.serengti.merchants.entity.Feedback;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FeedbackMapper {

    public Feedback convertToEntity(FeedbackDTO feedbackDto) {
        Feedback feedback = new Feedback();
        feedback.setFeedbackId(feedbackDto.getFeedbackId());
        feedback.setReceiptId(feedbackDto.getReceiptId());
        feedback.setStarRating(feedbackDto.getStarRating());
        feedback.setUserComment(feedbackDto.getUserComment());
        return feedback;
    }

    public FeedbackDTO convertToDto(Feedback feedback) {
        FeedbackDTO feedbackDto = new FeedbackDTO();
        feedbackDto.setFeedbackId(feedback.getFeedbackId());
        feedbackDto.setReceiptId(feedback.getReceiptId());
        feedbackDto.setStarRating(feedback.getStarRating());
        feedbackDto.setUserComment(feedback.getUserComment());
        return feedbackDto;
    }

}
