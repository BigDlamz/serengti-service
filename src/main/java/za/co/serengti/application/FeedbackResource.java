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
import java.util.Objects;
import java.util.stream.Collectors;

@Path("/feedback")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FeedbackResource {


    private final FeedbackService feedbackService;
    private final FeedbackMapper mapper;

    @Inject
    public FeedbackResource(FeedbackService feedbackService, FeedbackMapper mapper) {
        this.feedbackService = feedbackService;
        this.mapper = mapper;
    }

    @POST
    public Response addFeedback(FeedbackDTO feedbackDto) {
        Feedback feedback = mapper.convertToEntity(feedbackDto);
        feedbackService.addFeedback(feedback);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/store/{storeId}")
    public List<FeedbackDTO> getFeedbackForStore(@PathParam("storeId") Long storeId) {
        List<Feedback> feedbackList = feedbackService.getFeedbackForStore(storeId);
        return feedbackList.stream()
                .map(mapper::convertToDto)
                .collect(Collectors.toList());
    }

    @GET
    @Path("/average-rating/store/{storeId}")
    public Response getAverageRatingForStore(@PathParam("storeId") Long storeId) {
        Double averageRating = feedbackService.getAverageRatingForStore(storeId);
        return Response.ok(Objects.requireNonNullElse(averageRating, 0)).build();

    }

    @GET
    @Path("/exists/{receiptId}")
    public Response hasUserGivenFeedbackForReceipt(@PathParam("receiptId") Long receiptId) {
        Boolean isRated = feedbackService.hasBeenRated(receiptId);
         return Response.ok(isRated).build();
    }

    @GET
    @Path("/top5/store/{storeId}")
    public List<FeedbackDTO> getLatestFeedbackForStore(@PathParam("storeId") Long storeId) {
        List<Feedback> feedbackList = feedbackService.getLatestFeedbackForStore(storeId);
        return feedbackList
                .stream()
                .map(mapper::convertToDto)
                .collect(Collectors.toList());
    }
}
