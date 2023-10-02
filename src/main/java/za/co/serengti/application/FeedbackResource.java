package za.co.serengti.application;

import za.co.serengti.merchants.dto.FeedbackDTO;
import za.co.serengti.merchants.entity.Feedback;
import za.co.serengti.merchants.mapper.FeedbackMapper;
import za.co.serengti.merchants.service.FeedbackService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/feedback")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FeedbackResource {


    private final FeedbackService feedbackService;
    private final FeedbackMapper feedbackMapper;

    @Inject
    public FeedbackResource(FeedbackService feedbackService, FeedbackMapper feedbackMapper) {
        this.feedbackService = feedbackService;
        this.feedbackMapper = feedbackMapper;
    }

    @POST
    public Response addFeedback(FeedbackDTO feedbackDto) {
        Feedback feedback = feedbackMapper.convertToEntity(feedbackDto);
        feedbackService.addFeedback(feedback);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/store/{storeId}")
    public List<FeedbackDTO> getFeedbacksForStore(@PathParam("storeId") Long storeId) {
        List<Feedback> feedbacks = feedbackService.getFeedbacksForStore(storeId);
        return feedbacks.stream()
                .map(feedbackMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @GET
    @Path("/average-rating/store/{storeId}")
    public Response getAverageRatingForStore(@PathParam("storeId") Long storeId) {
        Double averageRating = feedbackService.getAverageRatingForStore(storeId);
        if (averageRating != null) {
            return Response.ok(averageRating).build();
        } else {
            return Response.ok(0).build();
        }
    }

    @GET
    @Path("/exists/{receiptId}")
    public Response hasUserGivenFeedbackForReceipt(@PathParam("receiptId") Long receiptId) {
        if (feedbackService.hasUserGivenFeedbackForReceipt(receiptId)) {
            return Response.ok(true).build(); // Feedback exists for this receipt
        } else {
            return Response.ok(false).build(); // No feedback has been given for this receipt
        }
    }

    @GET
    @Path("/top5/store/{storeId}")
    public List<FeedbackDTO> getTop5FeedbacksForStore(@PathParam("storeId") Long storeId) {
        List<Feedback> feedbacks = feedbackService.getTop5FeedbacksForStore(storeId);
        return feedbacks.stream()
                .map(feedbackMapper::convertToDto)
                .collect(Collectors.toList());
    }

}
