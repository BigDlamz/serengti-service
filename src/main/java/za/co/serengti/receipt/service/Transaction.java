package za.co.serengti.receipt.service;

import lombok.Builder;
import lombok.Data;
import za.co.serengti.receipt.domain.ReceiptDetails;
import za.co.serengti.receipt.application.MetaData;

@Data
@Builder
public class Transaction {

    private MetaData metaData;
    private String cutomerIdentifier;
    private ReceiptDetails receiptDetails;

}
