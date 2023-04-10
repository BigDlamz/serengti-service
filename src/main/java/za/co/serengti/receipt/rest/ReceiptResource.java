package za.co.serengti.receipt.rest;


import za.co.serengti.receipt.dto.ReceiptDTO;
import za.co.serengti.receipt.service.ReceiptService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;


@Path("/receipts")
public class ReceiptResource {

    @Inject
    ReceiptService receiptService;

    @GET
    @Path("/{id}")
    @Consumes("applicaiton/json")
    @Produces("applicaiton/json")
    public Response retrieveReceipt(@PathParam("id") Long customerID) {
        return Response.ok(receiptService.retrieveReceipt(customerID)).build();
    }

}
