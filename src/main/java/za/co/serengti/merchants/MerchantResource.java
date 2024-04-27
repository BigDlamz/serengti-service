package za.co.serengti.merchants;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.Objects;
import java.util.Set;

@Path("/v1/merchants")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MerchantResource {

    private final MerchantService merchantService;

    @Inject
    public MerchantResource(MerchantService merchantService) {
        this.merchantService = merchantService;

    }

    @POST
    @Path("/feedback")
    @Operation(summary = "Provide feedback for a store")

    public Response saveFeedback(FeedbackDTO feedbackDTO) {

        merchantService.saveFeedback(feedbackDTO);

        return Response.status(Response.Status.CREATED)
                .build();

    }

    @GET
    @Path("/specials/{merchantId}")
    @Operation(summary = "Find specials for a specific store")
    @APIResponse(responseCode = "200", description = "Specials found")
    @APIResponse(responseCode = "404", description = "No specials were found")
    @APIResponse(responseCode = "500", description = "An internal server error occurred")

    public Response getSpecials(@PathParam("merchantId") Long merchantId) {

        var specials = merchantService.retrieveSpecials(merchantId);

        if (specials.isEmpty()) {

            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No specials found for merchant ID: " + merchantId)
                    .build();

        }
        return Response
                .ok(Set.copyOf(specials))
                .build();

    }

    @GET
    @Path("/feedbacks/{merchantId}")
    @Operation(summary = "Retrieve all feedback for a merchant")

    public Response retrieveFeedback(@PathParam("merchantId") Long merchantId) {

        var feedbackList = merchantService.retrieveFeedback(merchantId);

        return Response
                .ok(feedbackList)
                .build();

    }

    @GET
    @Path("/feedbacks/latest/{merchantId}")
    @Operation(summary = "Retrieve just the latest feedback for a merchant")

    public Response getLatestFeedback(@PathParam("merchantId") Long merchantId) {

        var feedbackList = merchantService.retrieveLatestFeedback(merchantId);

        return Response
                .ok(feedbackList)
                .build();

    }

    @GET
    @Path("/ratings/{merchantId}")
    @Operation(summary = "Retrieve the average rating for a store")

    public Response retrieveRating(@PathParam("merchantId") Long storeId) {

        Double rating = merchantService.retrieveAverageRating(storeId);

        return Response.ok(Objects.requireNonNullElse(rating, 0))
                .build();

    }

    @GET
    @Path("/feedbacks/exists/{receiptId}")
    @Operation(summary = "Check if the service has been rated for this receipt")

    public Response isRated(@PathParam("receiptId") Long receiptId) {

        Boolean isRated = merchantService.isRated(receiptId);

        return Response
                .ok(isRated)
                .build();

    }


}
