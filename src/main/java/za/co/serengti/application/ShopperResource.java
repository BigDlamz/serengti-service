package za.co.serengti.application;

import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import za.co.serengti.receipts.entity.Receipt;
import za.co.serengti.receipts.mapper.ReceiptMapper;
import za.co.serengti.shoppers.service.ShopperService;
import za.co.serengti.util.Validate;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Path("/shoppers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ShopperResource {

    private final ShopperService shopperService;
    private final ReceiptMapper receiptMapper;
    private final Validate validate;

    public ShopperResource(ShopperService shopperService, ReceiptMapper receiptMapper, Validate validate) {
        this.shopperService = shopperService;
        this.receiptMapper = receiptMapper;
        this.validate = validate;
    }

    @POST
    @Path("/receipts/")
    @Operation(summary = "Save a new user receipt")
    @APIResponse(responseCode = "201", description = "Receipt saved")
    @APIResponse(responseCode = "500", description = "An internal server error occurred")
    @RunOnVirtualThread
    public Response saveReceipt(@Valid SaveReceiptRequest request) {
        Long receiptId = shopperService.saveReceipt(request);
        return Response
                .created(URI.create("/shoppers/receipts/" + receiptId))
                .build();
    }

    @GET
    @Path("/receipts/{receiptId}")
    @Operation(summary = "Retrieve a receipt by receipt ID")
    @APIResponse(responseCode = "200", description = "Receipt found")
    @APIResponse(responseCode = "404", description = "Receipt not found")
    @APIResponse(responseCode = "500", description = "An internal server error occurred")
    @RunOnVirtualThread
    public Response retrieveReceipt(@PathParam("receiptId") Long receiptId) {
        validate.notNull(receiptId, "receiptId");
        Receipt receipt = shopperService.retrieveReceipt(receiptId);
        if (Objects.isNull(receipt)) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Receipt not found for ID: " + receiptId)
                    .build();
        }
        return Response.ok(receiptMapper.toDto(receipt)).build();
    }

    @GET
    @Path("/receipts")
    @Operation(summary = "Retrieve shoppers  receipts by email and transaction date")
    @APIResponse(responseCode = "200", description = "Receipts found")
    @APIResponse(responseCode = "404", description = "No receipts found for email")
    @APIResponse(responseCode = "500", description = "An internal server error occurred")
    @RunOnVirtualThread
    public Response retrieveReceiptsByEmailAndDate(@QueryParam("email") String email, @QueryParam("transactionDate") String transactionDate) {
        LocalDate date;
        try {
            date = LocalDate.parse(transactionDate, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid date format. Use YYYY-MM-DD.")
                    .build();
        }
        List<Receipt> receipts = shopperService.retrieveReceiptsByEmailAndDate(email, date);

        if (receipts.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ResponseMessage.builder().message("No receipts found for email: " + email + " on date: " + transactionDate).build())
                    .build();
        }

        return Response.ok(receipts
                        .stream()
                        .map(receiptMapper::toDto)
                        .collect(Collectors.toList()))
                .build();
    }

    @GET
    @Path("/receipts/counts")
    @Operation(summary = "Find the total number of receipts for a shopper")
    @APIResponse(responseCode = "500", description = "An internal server error occurred")
    @RunOnVirtualThread
    public Response retrieveReceiptsTotalCount(@QueryParam("email") String email) {
        validate.notNull(email, "email");
        return Response.ok(ReceiptCount.builder().count(shopperService.retrieveReceiptsTotalCount(email)).build())
                .build();
    }

    @PUT
    @Operation(summary = "Update the receipt as being viewed")
    @Path("/receipts/views/{receiptId}")
    @RunOnVirtualThread
    public Response updateReceiptAsViewed(@PathParam("receiptId") Long receiptId) {
        if (shopperService.updateReceiptAsViewed(receiptId)) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Operation(summary = "Find the total number of unread receipts for a shopper")
    @Path("/receipts/unread")
    @RunOnVirtualThread
    public Response retrieveUnreadReceipts(@QueryParam("email") String email) {
        validate.notNull(email, "email");
        Long unreadReceipts = shopperService.retrieveUnreadReceipts(email);
        return Response.ok(ReceiptCount.builder().count(unreadReceipts).build())
                .build();
    }
}

