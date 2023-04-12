package za.co.serengti.receipt.service.request;

import lombok.Builder;
import lombok.Data;
import za.co.serengti.receipt.dto.ReceiptDetails;

@Data
@Builder
public class GenerateReceiptRequest {

    private RequestMetaData metaData;
    private String cutomerIdentifier;
    private ReceiptDetails receiptDetails;

}
