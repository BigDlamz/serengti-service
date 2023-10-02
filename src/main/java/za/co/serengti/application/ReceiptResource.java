package za.co.serengti.application;

import io.smallrye.common.annotation.RunOnVirtualThread;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import za.co.serengti.receipts.entity.Receipt;
import za.co.serengti.receipts.mapper.ReceiptMapper;
import za.co.serengti.receipts.service.ReceiptService;
import za.co.serengti.util.Validate;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Path("/receipts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReceiptResource {

    private final ReceiptService receiptService;
    private final ReceiptMapper receiptMapper;
    private final Validate validate;

    public ReceiptResource(ReceiptService receiptService, ReceiptMapper receiptMapper, Validate validate) {
        this.receiptService = receiptService;
        this.receiptMapper = receiptMapper;
        this.validate = validate;
    }

    @POST
    @Operation(summary = "Save a new user receipt")
    @APIResponse(responseCode = "201", description = "Receipt saved")
    @APIResponse(responseCode = "500", description = "An internal server error occurred")
    @RunOnVirtualThread
    public Response saveReceipt(@Valid SaveReceiptRequest request) {
        Long receiptId = receiptService.save(request);
        return Response
                .created(URI.create("/receipts/" + receiptId))
                .build();
    }

    @GET
    @Path("{receiptId}")
    @Operation(summary = "Find a specific receipt by ID")
    @APIResponse(responseCode = "200", description = "Receipt found")
    @APIResponse(responseCode = "404", description = "Receipt not found")
    @APIResponse(responseCode = "500", description = "An internal server error occurred")
    public Response findReceipt(@PathParam("receiptId") Long receiptId) {
        validate.notNull(receiptId, "receiptId");
        Receipt receipt = receiptService.find(receiptId);

        if (Objects.isNull(receipt)) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Receipt not found for ID: " + receiptId)
                    .build();
        }
        return Response.ok(receiptMapper.toDto(receipt)).build();
    }

    @GET
    @Operation(summary = "Find user receipts by email and transaction date")
    @APIResponse(responseCode = "200", description = "Receipts found")
    @APIResponse(responseCode = "404", description = "No receipts found for email")
    @APIResponse(responseCode = "500", description = "An internal server error occurred")
    public Response findAllReceiptsByEmailAndDate(@QueryParam("email") String email, @QueryParam("transactionDate") String transactionDate) {
        LocalDate date;
        try {
            date = LocalDate.parse(transactionDate, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid date format. Use YYYY-MM-DD.")
                    .build();
        }

        List<Receipt> receipts = receiptService.findAllReceiptsByCustomerEmailAndDate(email, date);

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
    @Path("counts")
    @Operation(summary = "Find the total number of receipts for a user")
    @APIResponse(responseCode = "500", description = "An internal server error occurred")
    public Response findTotalUserReceiptCount(@QueryParam("email") String email) {
        validate.notNull(email, "email");
        return Response.ok(ReceiptCount.builder()
                .count(receiptService.findTotalUserReceiptCount(email))
                .build())
                .build();
    }

    @PUT
    @Operation(summary = "Update the receipt as being read")
    @Path("/reads/{receiptId}")
    public Response updateReceiptAsRead(@PathParam("receiptId") Long receiptId) {
        if (receiptService.markReceiptAsRead(receiptId)) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Operation(summary = "Find the total number of unread receipts for a user")
    @Path("/unread")
    public Response findUnreadReceipts(@QueryParam("email") String email) {
        validate.notNull(email, "email");
        return Response.ok(ReceiptCount.builder()
                .count(receiptService.findUnreadReceiptsByEmail(email)).build())
                .build();
    }
}

