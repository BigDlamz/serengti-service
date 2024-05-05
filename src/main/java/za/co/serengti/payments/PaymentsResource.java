package za.co.serengti.payments;

import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import za.co.serengti.shoppers.*;


@Path("/v1/shoppers/payments")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PaymentsResource {

    final PaymentService paymentService;
    final ShopperService shopperService;


    public PaymentsResource(PaymentService paymentService, ShopperService shopperService) {
        this.paymentService = paymentService;
        this.shopperService = shopperService;
    }

    @POST
    @Operation(summary = "Pay for goods and services")
    @APIResponse(responseCode = "200", description = "Payment successful")
    @APIResponse(responseCode = "500", description = "An internal server error occurred")
    @RunOnVirtualThread

    public Response pay(@Valid PaymentRequest request) {

        paymentService.savePayment(request);

        return Response.ok().build();

    }

    @GET
    @Path("{paymentId}")
    @Operation(summary = "Retrieve a payment made by shopper")
    @RunOnVirtualThread

    public Response retrievePayment(@QueryParam("paymentId") Long paymentId) {

        PaymentDTO payment = paymentService.find(paymentId);

        return Response.ok(payment).build();

    }

    @GET
    @Operation(summary = "Retrieve total value payments of payments by a shopper")
    @Path("total")
    @RunOnVirtualThread

    public Response retrieveTotalPayments(@QueryParam("email") String email) {

        ShopperDTO shopper = shopperService.find(email);

        return Response.ok(StatsDTO
                        .builder()
                        .totalPayments(paymentService.getTotalPayments(shopper.getShopperId()))
                        .build())
                .build();

    }

}
