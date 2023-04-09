package za.co.serengti.receipt.rest;


import za.co.serengti.receipt.entity.Receipt;
import za.co.serengti.receipt.service.ReceiptService;
import za.co.serengti.receipt.vo.ReceiptVO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.util.Set;


@Path("/receipts")
public class ReceiptResource {

    @Inject
    ReceiptService receiptService;

    @POST
    @Consumes("applicaiton/json")
    @Produces("applicaiton/json")
    public Response uploadReceipt(ReceiptVO receipt) {
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{id}")
    @Consumes("applicaiton/json")
    @Produces("applicaiton/json")
    public Response retrieveReceipt(@PathParam("id") Long customerID) {
        //TODO  Receipt receipt = receiptService.retrieveReceipt(customerID);
        //   if (receipt == null) {
        //      return Response.status(Response.Status.NOT_FOUND).build();
        //   }
        /// return Response.ok(receipt).build();
     //   return null;

        return null;
    }

    @GET
    public Set<Receipt> retrieveReceipts(@PathParam("id") Long id, Timestamp fromDate, Timestamp toDate) {
        //TODO return receiptService.retrieveReceipts(id,fromDate,toDate);
  return null;
    }



}
