package za.co.serengti.application;

import za.co.serengti.customers.dto.CustomerDTO;
import za.co.serengti.customers.service.CustomerService;
import za.co.serengti.merchants.dto.POSSystemDTO;
import za.co.serengti.merchants.dto.ProductDTO;
import za.co.serengti.merchants.dto.StoreDTO;
import za.co.serengti.merchants.service.MerchantManager;
import za.co.serengti.merchants.service.ProductService;
import za.co.serengti.receipts.dto.ReceiptDTO;
import za.co.serengti.receipts.service.ReceiptService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@Path("/receipts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReceiptResource {


    private final ReceiptService receiptService;
    private final MerchantManager merchantService;
    private final CustomerService customerService;
    private final ProductService productService;

    public ReceiptResource(ReceiptService receiptService, MerchantManager merchantService, CustomerService customerService, ProductService productService) {
        this.receiptService = receiptService;
        this.merchantService = merchantService;
        this.customerService = customerService;
        this.productService = productService;
    }

    @POST
    public void save(SaveReceiptRequest request, @HeaderParam("X-POS-ID") Long posId, @HeaderParam("X-STORE-ID") String storeId) {

        if(Objects.isNull(posId) || Objects.isNull(storeId)) {
            throw new BadRequestException("Missing header");
        }

        POSSystemDTO pos = merchantService.findPosSystem(posId);
        StoreDTO store = merchantService.findStore(Long.parseLong(storeId));
        CustomerDTO customer = customerService.findOrSaveCustomer(request.getCustomerIdentifier());
        List<ProductDTO> purchases = productService.findOrSavePurchasedProducts(request.getPurchasedItems(),pos, store);

        var receiptDTO = ReceiptDTO.builder()
                .posSystem(pos)
                .store(store)
                .customer(customer)
                .lineItems(receiptService.createLineItems(purchases))
                .timestamp(LocalDateTime.now())
                .build();

        receiptDTO.calculateTotalAmountPaid();
        receiptService.save(receiptDTO);
    }

    @GET
    @Path("/{id}")
    public Response findReceipt(@PathParam("id") Long customerID) {
        return Response.ok(receiptService.find(customerID)).build();
    }
}
