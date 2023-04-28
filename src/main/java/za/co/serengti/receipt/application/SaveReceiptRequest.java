package za.co.serengti.receipt.application;

import lombok.Builder;
import lombok.Data;
import za.co.serengti.receipt.domain.ReceiptDetails;

@Data
@Builder
public class SaveReceiptRequest {

    private String customerIdentifier;
    private ReceiptDetails receiptDetails;

}
