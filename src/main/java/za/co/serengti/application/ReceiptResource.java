package za.co.serengti.application;

import za.co.serengti.receipts.dto.ReceiptDTO;
import za.co.serengti.receipts.entity.Receipt;
import za.co.serengti.receipts.mapper.ReceiptMapper;
import za.co.serengti.receipts.service.ReceiptService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    @Path("/id/{receiptId}")
    public ReceiptDTO find(@PathParam("receiptId") Long receiptId) {
        if(Objects.isNull(receiptId)) {
            throw new BadRequestException("ReceiptId cannot be null");
        }
        return receiptMapper.toDto(receiptService.find(receiptId));
    }

    /**
     * Finds all receipts by customer email.
     * //TODO specify data range
     * @param email email address to use when looking up receipts
     * @return the receipt DTO
     */
    @GET
    @Path("/email/{email}")
    public List<ReceiptDTO> findAllReceipts(@PathParam("email") String email) {
        if(Objects.isNull(email)) {
            throw new BadRequestException("Email cannot be null");
        }
        List<Receipt> receipts = receiptService.findAllByCustomerEmail(email);
        return receipts
                .stream()
                .map(receiptMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Finds customer total receipts count
     *
     * @param email email address to use when looking up receipts
     * @return the receipt DTO
     */
    @GET
    @Path("/email/{email}/count")
    public Long findCustomerTotalReceipts(@PathParam("email") String email) {
        if(Objects.isNull(email)) {
            throw new BadRequestException("Email cannot be null");
        }
        return receiptService.findCustomerTotalReceipts(email);
    }
}
