package za.co.serengti.application;

import lombok.Builder;
import lombok.Data;
import za.co.serengti.receipts.dto.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class SaveReceiptRequestDTO {
    private String customerIdentifier;
    private LocalDateTime transactionDate;
    private List<PurchasedItemDTO> lineItems;
    private BigDecimal totalAmountPaid;
    private TaxInvoiceDTO taxInvoice;
    private TillDTO till;
    private CashierDTO cashier;
    private PromotionsDTO promotions;
}
