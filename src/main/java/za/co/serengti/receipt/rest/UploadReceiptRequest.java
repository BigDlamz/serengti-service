package za.co.serengti.receipt.rest;

import lombok.Builder;
import lombok.Data;
import za.co.serengti.receipt.dto.ReceiptDetails;

@Data
@Builder
public class UploadReceiptRequest {

    private String cutomerIdentifier;
    private ReceiptDetails receiptDetails;

}
