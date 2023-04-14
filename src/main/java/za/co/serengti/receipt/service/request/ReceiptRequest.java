package za.co.serengti.receipt.service.request;

import lombok.Builder;
import lombok.Data;
import za.co.serengti.receipt.dto.ReceiptDetailsDTO;
import za.co.serengti.receipt.rest.RequestMetaData;

@Data
@Builder
public class ReceiptRequest {

    private RequestMetaData metaData;
    private String cutomerIdentifier;
    private ReceiptDetailsDTO receiptDetails;

}
