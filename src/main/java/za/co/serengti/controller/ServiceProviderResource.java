package za.co.serengti.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import za.co.serengti.merchants.dto.FeedbackDTO;
import za.co.serengti.merchants.mapper.FeedbackMapper;
import za.co.serengti.merchants.service.StoreService;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Path("/stores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ServiceProviderResource {

    private final StoreService storeService;
    private final FeedbackMapper convertor;

    @Inject
    public ServiceProviderResource(StoreService storeService, FeedbackMapper convertor) {
        this.storeService = storeService;
        this.convertor = convertor;
    }

    @GET
    @Path("/specials/{storeId}")
    @Operation(summary = "Find specials for a specific store")
    @APIResponse(responseCode = "200", description = "Specials found")
    @APIResponse(responseCode = "404", description = "No specials were found")
    @APIResponse(responseCode = "500", description = "An internal server error occurred")

    public Response getSpecials(@PathParam("storeId") Long storeId) {
        var specials = storeService.retrieveSpecials(storeId);
        if (specials.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No specials found for store ID: " + storeId)
                    .build();
        }
        return Response.ok(Set.copyOf(specials)).build();
    }

    @POST
    @Path("/feedback")
    @Operation(summary = "Provide feedback for a store")
    public Response saveFeedback(FeedbackDTO feedbackDto) {
        var feedback = convertor.toEntity(feedbackDto);
        storeService.saveFeedback(feedback);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/feedback/{storeId}")
    @Operation(summary = "Retrieve all feedback for a store")
    public List<FeedbackDTO> retrieveFeedback(@PathParam("storeId") Long storeId) {
        var feedbackList = storeService.retrieveFeedback(storeId);
        return feedbackList.stream()
                .map(convertor::toDto)
                .collect(Collectors.toList());
    }

    @GET
    @Path("/ratings/{storeId}")
    @Operation(summary = "Retrieve the average rating for a store")
    public Response retrieveRating(@PathParam("storeId") Long storeId) {
        Double averageRating = storeService.retrieveAverageRating(storeId);
        return Response.ok(Objects.requireNonNullElse(averageRating, 0)).build();

    }

    @GET
    @Path("/feedback/exists/{receiptId}")
    @Operation(summary = "Check if the service has been rated for this receipt")
    public Response hasServiceBeenRated(@PathParam("receiptId") Long receiptId) {
        Boolean isRated = storeService.hasServiceBeenRated(receiptId);
        return Response.ok(isRated).build();
    }

    @GET
    @Path("/feedback/top/{storeId}")
    @Operation(summary = "Retrieve the top feedback for a store")
    public List<FeedbackDTO> getTopFeedback(@PathParam("storeId") Long storeId) {
        var feedbackList = storeService.retrieveTopFeedback(storeId);
        return feedbackList
                .stream()
                .map(convertor::toDto)
                .collect(Collectors.toList());
    }
}
