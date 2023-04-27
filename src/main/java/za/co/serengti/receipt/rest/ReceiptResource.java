package za.co.serengti.receipt.rest;

import za.co.serengti.receipt.service.ReceiptService;
import za.co.serengti.receipt.service.request.Transaction;

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
    public void uploadReceipt(UploadReceiptRequest request, @HeaderParam("X-POS-ID") Long posId, @HeaderParam("X-STORE-ID") String storeId) {

        var metaData = RequestMetaData.builder()
                .posSystem(posId)
                .store(Long.parseLong(storeId))
                .build();

        var incomingReceipt = Transaction.builder()
                .metaData(metaData)
                .cutomerIdentifier(request.getCutomerIdentifier())
                .receiptDetails(request.getReceiptDetails())
                .build();

        receiptService.save(incomingReceipt);
    }

    @GET
    @Path("/{id}")
    public Response retrieveReceipt(@PathParam("id") Long customerID) {
        return Response.ok(receiptService.retrieveReceipt(customerID)).build();
    }
}
