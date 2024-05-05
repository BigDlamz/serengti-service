package za.co.serengti.receipts;

import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import za.co.serengti.shoppers.ApiResponses;
import za.co.serengti.shoppers.StatsDTO;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;


@Path("/v1/shoppers/receipts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReceiptResource {

    private final ReceiptService receiptService;

    public ReceiptResource(ReceiptService receiptService) {

        this.receiptService = receiptService;
    }

    @POST
    @Operation(summary = "Save a new receipt")
    @APIResponse(responseCode = "201", description = "Receipt saved")
    @APIResponse(responseCode = "500", description = "An internal server error occurred")
    @RunOnVirtualThread

    public Response save(@Valid ReceiptDTO request) {

        ReceiptDTO receipt = receiptService.save(request);

        return Response
                .created(URI.create("/shoppers/receipts/" + receipt.getReceiptId()))
                .build();

    }

    @GET
    @Path("{receiptId}")
    @Operation(summary = "Retrieve a receipt by receipt ID")
    @APIResponse(responseCode = "200", description = "Receipt found")
    @APIResponse(responseCode = "404", description = "Receipt not found")
    @APIResponse(responseCode = "500", description = "An internal server error occurred")
    @RunOnVirtualThread

    public Response retrieve(@PathParam("receiptId") Long receiptId) {

        if (Objects.isNull(receiptId)) {

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Receipt ID is required")
                    .build();

        }

        ReceiptDTO receipt = receiptService.find(receiptId);

        if (Objects.isNull(receipt)) {

            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Receipt not found for ID: " + receiptId)
                    .build();

        }

        return Response.ok(receipt).build();

    }

    @GET
    @Operation(summary = "Retrieve receipts by email and transaction date")
    @APIResponse(responseCode = "200", description = "Receipts found")
    @APIResponse(responseCode = "404", description = "No receipts found for email")
    @APIResponse(responseCode = "500", description = "An internal server error occurred")
    @RunOnVirtualThread

    public Response retrieve(@QueryParam("email") String email, @QueryParam("transactionDate") String txDate) {

        LocalDate date;

        try {

            date = LocalDate.parse(txDate, DateTimeFormatter.ISO_LOCAL_DATE);

        } catch (DateTimeParseException e) {

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid date format. Use YYYY-MM-DD.")
                    .build();
        }

        List<ReceiptDTO> receipts = receiptService.find(email, date);

        if (receipts.isEmpty()) {

            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ApiResponses.builder().message("No receipts found for email: " + email + " on date: " + txDate).build())
                    .build();

        }

        return Response.ok(receipts).build();

    }

    @GET
    @Path("total-count")
    @Operation(summary = "Find the total number of receipts for a shopper")
    @APIResponse(responseCode = "500", description = "An internal server error occurred")
    @RunOnVirtualThread

    public Response retrieveTotalCount(@QueryParam("email") String email) {

        if (Objects.isNull(email)) {

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Email address is required")
                    .build();

        }

        return Response.ok(StatsDTO.builder()
                        .totalReceiptsCount(receiptService.findTotalCount(email))
                        .build())
                .build();
    }

    @PUT
    @Operation(summary = "Update the receipt has been read")
    @Path("{receiptId}/read")
    @RunOnVirtualThread

    public Response updateStatus(@PathParam("receiptId") Long receiptId) {

        UpdateStatusRequest request = UpdateStatusRequest.builder()
                .receiptId(receiptId)
                .status(Status.READ)
                .build();

        if (receiptService.updateStatus(request)) {

            return Response.ok().build();

        } else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }

    @GET
    @Operation(summary = "Find the total number of unread receipts for a shopper")
    @Path("unread")
    @RunOnVirtualThread

    public Response retrieveUnread(@QueryParam("email") String email) {

        if (Objects.isNull(email)) {

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Email address is required")
                    .build();
        }

        Long unreadReceiptsCount = receiptService.findUnread(email);

        return Response.ok(StatsDTO
                        .builder()
                        .unreadReceiptsCount(unreadReceiptsCount)
                        .build())
                .build();
    }

}

