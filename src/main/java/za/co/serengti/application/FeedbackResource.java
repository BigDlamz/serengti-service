package za.co.serengti.application;

import za.co.serengti.merchants.dto.FeedbackDTO;
import za.co.serengti.merchants.entity.Feedback;
import za.co.serengti.merchants.mapper.FeedbackMapper;
import za.co.serengti.merchants.service.FeedbackService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
