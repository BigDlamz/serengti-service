package za.co.serengti.application;

import za.co.serengti.receipts.service.ReceiptService;
import za.co.serengti.receipts.service.Transaction;

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
    public void save(SaveReceiptRequest request, @HeaderParam("X-POS-ID") Long posId, @HeaderParam("X-STORE-ID") String storeId) {

        Transaction tx = Transaction.builder()
                .metaData(MetaData.builder()
                        .posSystemID(posId)
                        .storeID(Long.parseLong(storeId))
                        .build())
                .cutomerIdentifier(request.getCustomerIdentifier())
                .receiptDetails(request.getReceiptDetails())
                .build();

        receiptService.process(tx);
    }

    @GET
    @Path("/{id}")
    public Response retrieveReceipt(@PathParam("id") Long customerID) {
        return Response.ok(receiptService.findReceipt(customerID)).build();
    }
}
