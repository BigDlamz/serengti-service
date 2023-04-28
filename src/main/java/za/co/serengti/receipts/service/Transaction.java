package za.co.serengti.receipts.service;

import lombok.Builder;
import lombok.Data;
import za.co.serengti.receipts.domain.ReceiptDetails;
import za.co.serengti.application.MetaData;

@Data
@Builder
public class Transaction {

    private MetaData metaData;
    private String cutomerIdentifier;
    private ReceiptDetails receiptDetails;

}
