package za.co.serengti.receipt.rest;


import za.co.serengti.receipt.service.ReceiptService;
import za.co.serengti.receipt.service.request.GenerateReceiptRequest;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/receipts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReceiptResource {

    @Inject
    ReceiptService receiptService;

    @POST
    public void generateReceipt(GenerateReceiptRequest request, @HeaderParam("X-POS-ID") Long posId, @HeaderParam("X-STORE-ID") String storeId) {
        receiptService.generateReceipt(request);
    }

    @GET
    @Path("/{id}")
    public Response retrieveReceipt(@PathParam("id") Long customerID) {
        return Response.ok(receiptService.retrieveReceipt(customerID)).build();
    }
}
