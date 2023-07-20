package za.co.serengti.application;

import lombok.Builder;
import lombok.Data;
import za.co.serengti.receipts.dto.PurchasedItem;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class SaveReceiptRequest {
    private String customerIdentifier;
    private LocalDateTime transactionDate;
    private List<PurchasedItem> lineItems;
    private BigDecimal totalAmountPaid;
    private TaxInvoice taxInvoice;
    private Till till;
    private Cashier cashier;
}
