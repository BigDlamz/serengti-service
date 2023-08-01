package za.co.serengti.application;

import za.co.serengti.customers.dto.CustomerDTO;
import za.co.serengti.customers.service.CustomerService;
import za.co.serengti.merchants.dto.POSSystemDTO;
import za.co.serengti.merchants.dto.ProductDTO;
import za.co.serengti.merchants.dto.StoreDTO;
import za.co.serengti.merchants.service.MerchantService;
import za.co.serengti.merchants.service.ProductService;
import za.co.serengti.receipts.dto.ReceiptDTO;
import za.co.serengti.receipts.entity.Till;
import za.co.serengti.receipts.service.ReceiptService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@Path("/receipts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReceiptResource {

    private final ReceiptService receiptService;
    private final MerchantService merchantService;
    private final CustomerService customerService;
    private final ProductService productService;

    public ReceiptResource(ReceiptService receiptService, MerchantService merchantService, CustomerService customerService, ProductService productService) {
        this.receiptService = receiptService;
        this.merchantService = merchantService;
        this.customerService = customerService;
        this.productService = productService;
    }

    @POST
    public void save(SaveReceiptRequestDTO request, @HeaderParam("POS-ID") Long posId, @HeaderParam("STORE-ID") String storeId) {

        if(Objects.isNull(posId) || Objects.isNull(storeId)) {
            throw new BadRequestException("Missing header");
        }

        POSSystemDTO posSystem = merchantService.findPosSystem(posId);
        StoreDTO store = merchantService.findStore(Long.parseLong(storeId));

        CustomerDTO customer = customerService.findOrSaveCustomer(request.getCustomerIdentifier());

        List<ProductDTO> purchases = productService.findOrSaveProducts(request.getPurchasedItems(),posSystem, store);


        var receipt = ReceiptDTO.builder()
                .posSystem(posSystem)
                .store(store)
                .customer(customer)
                .purchasedItems(receiptService.createLineItems(purchases))
                .timestamp(request.getTransactionDate())
                .till(request.getTill())
                .cashier(request.getCashier())
                .promotions(request.getPromotions())
                .amountBeforeTax(request.getTaxInvoice().getAmountBeforeTax())
                .amountAfterTax(request.getTaxInvoice().getAmountAfterTax())
                .build();

        receiptService.save(receipt);
    }

    @GET
    @Path("/{id}")
    public ReceiptDTO findReceipt(@PathParam("id") Long customerID) {
        return receiptService.find(customerID);
    }
}
