package za.co.serengti.application;

import za.co.serengti.receipts.dto.ReceiptDTO;
import za.co.serengti.receipts.mapper.ReceiptMapper;
import za.co.serengti.receipts.service.ReceiptService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Objects;

/**
 * Handles receipt-related HTTP requests.
 */
@Path("/receipts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReceiptResource {

    private final ReceiptService receiptService;
    private final ReceiptMapper receiptMapper;

    public ReceiptResource(ReceiptService receiptService, ReceiptMapper receiptMapper) {
        this.receiptService = receiptService;
        this.receiptMapper = receiptMapper;
    }

    /**
     * Saves a receipt.
     *
     * @param request the request to save a receipt
     * @param posId the POS ID
     * @param storeId the store ID
     */
    @POST
    public void save(SaveReceiptRequest request, @HeaderParam("POS-ID") Long posId, @HeaderParam("STORE-ID") Long storeId) {
        if(Objects.isNull(request)) {
            throw new BadRequestException("Request body cannot be null");
        }
        if(Objects.isNull(posId)) {
            throw new BadRequestException("POS-ID header is missing");
        }
        if(Objects.isNull(storeId)) {
            throw new BadRequestException("STORE-ID header is missing");
        }
        receiptService.process(request, posId, storeId);
    }

    /**
     * Finds a receipt by ID.
     *
     * @param receiptId the receipt ID to find
     * @return the receipt DTO
     */
    @GET
    @Path("/{receiptId}")
    public ReceiptDTO find(@PathParam("receiptId") Long receiptId) {
        if(Objects.isNull(receiptId)) {
            throw new BadRequestException("ReceiptId cannot be null");
        }
        return receiptMapper.toDto(receiptService.find(receiptId));
    }
}
