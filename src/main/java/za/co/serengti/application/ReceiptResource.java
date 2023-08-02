package za.co.serengti.application;

import za.co.serengti.receipts.service.ReceiptService;
import za.co.serengti.util.RecordMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Objects;

@Path("/receipts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReceiptResource {

    private final ReceiptService receiptService;

    private final RecordMapper mapper;

    public ReceiptResource(ReceiptService receiptService,  RecordMapper mapper) {
        this.receiptService = receiptService;
        this.mapper = mapper;
    }

    @POST
    public void save(SaveReceiptRequestDTO request, @HeaderParam("POS-ID") Long posId, @HeaderParam("STORE-ID") Long storeId) {

        if(Objects.isNull(posId) || Objects.isNull(storeId)) {
            throw new BadRequestException("Mandatory header is missing");
        }
         receiptService.process(request, posId, storeId);
    }
}
