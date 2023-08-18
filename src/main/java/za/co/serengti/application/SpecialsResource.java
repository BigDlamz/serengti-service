package za.co.serengti.application;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import za.co.serengti.merchants.entity.Special;
import za.co.serengti.merchants.service.SpecialService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Path("/specials")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SpecialsResource {

    private final SpecialService specialService;

    @Inject
    public SpecialsResource(SpecialService specialService) {
        this.specialService = specialService;
    }

    @GET
    @Path("{storeId}")
    @Operation(summary = "Find specials for a specific store")
    @APIResponse(responseCode = "200", description = "Specials found")
    @APIResponse(responseCode = "404", description = "No specials were found")
    @APIResponse(responseCode = "500", description = "An internal server error occurred")

    public Response getSpecialsForStore(@PathParam("storeId") Long storeId) {
        List<Special> specialsList = specialService.findSpecials(storeId);
        if (specialsList.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("No specials found for store ID: " + storeId).build();
        }
        Set<Special> specialsSet = new HashSet<>(specialsList);
        return Response.ok(specialsSet).build();
    }
}
