package za.co.serengti.application;

import za.co.serengti.customers.dto.CustomerDTO;
import za.co.serengti.customers.service.CustomerService;
import za.co.serengti.merchants.dto.POSSystemDTO;
import za.co.serengti.merchants.dto.StoreDTO;
import za.co.serengti.merchants.service.MerchantManager;
import za.co.serengti.receipts.dto.ReceiptDTO;
import za.co.serengti.receipts.service.ReceiptService;

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

    @Inject
    MerchantManager merchantService;

    @Inject
    CustomerService customerService;

    @POST
    public void save(SaveReceiptRequest request, @HeaderParam("X-POS-ID") Long posId, @HeaderParam("X-STORE-ID") String storeId) {

        if(posId == null || storeId == null) {
            throw new BadRequestException("Missing header");
        }

        POSSystemDTO pos = merchantService.findPOS(posId);
        StoreDTO store = merchantService.findStore(Long.parseLong(storeId));
        CustomerDTO customer = customerService.findOrSaveCustomer(request.getCustomerIdentifier());

        ReceiptDTO receiptDTO = ReceiptDTO.builder()
                .posSystem(pos)
                .store(store)
                .customer(customer)
                .lineItems(request.getReceiptDetails().getLineItems())
                .build();

        receiptDTO.calculateTotalAmountPaid();

        Long receiptId =receiptService.save(receiptDTO);
    }

    @GET
    @Path("/{id}")
    public Response findReceipt(@PathParam("id") Long customerID) {
        return Response.ok(receiptService.find(customerID)).build();
    }
}
