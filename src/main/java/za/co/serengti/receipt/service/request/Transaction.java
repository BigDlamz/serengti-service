package za.co.serengti.receipt.service.request;

import lombok.Builder;
import lombok.Data;
import za.co.serengti.receipt.dto.ReceiptDetails;
import za.co.serengti.receipt.rest.RequestMetaData;

@Data
@Builder
public class Transaction {

    private RequestMetaData metaData;
    private String cutomerIdentifier;
    private ReceiptDetails receiptDetails;

}
