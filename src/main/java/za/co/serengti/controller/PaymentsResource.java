package za.co.serengti.controller;

import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import za.co.serengti.controller.dto.PaymentRequestDTO;
import za.co.serengti.payments.service.PaymentRequest;
import za.co.serengti.payments.service.PaymentsService;
import za.co.serengti.shoppers.service.ShopperService;


@Path("/payments")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PaymentsResource {

    final PaymentsService paymentsService;
    final ShopperService shopperService;

    public PaymentsResource(PaymentsService paymentsService, ShopperService shopperService) {
        this.paymentsService = paymentsService;
        this.shopperService = shopperService;
    }

    @POST
    @Operation(summary = "Pay for goods and services")
    @APIResponse(responseCode = "200", description = "Payment successful")
    @APIResponse(responseCode = "500", description = "An internal server error occurred")
    @RunOnVirtualThread
    public Response pay(@Valid PaymentRequestDTO paymentRequest) {

        paymentsService.processPayment(PaymentRequest.builder()
                .posId(paymentRequest.getPosId())
                .storeId(paymentRequest.getStoreId())
                .transactionDate(paymentRequest.getTransactionDate())
                .payingCustomerIdentifier(paymentRequest.getPayingCustomerIdentifier())
                .purchasedProducts(paymentRequest.getPurchasedProducts())
                .build());

        return Response.ok().build();

    }

}
