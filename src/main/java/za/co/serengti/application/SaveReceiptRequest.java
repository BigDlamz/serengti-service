package za.co.serengti.application;

import lombok.Builder;
import lombok.Data;
import za.co.serengti.receipts.dto.ReceiptDetails;


@Data
@Builder
public class SaveReceiptRequest {

    private String customerIdentifier;
    private ReceiptDetails receiptDetails;

}
