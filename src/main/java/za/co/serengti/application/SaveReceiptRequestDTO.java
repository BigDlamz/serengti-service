package za.co.serengti.application;

import lombok.Builder;
import lombok.Data;
import za.co.serengti.receipts.dto.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class SaveReceiptRequestDTO {

    private String customerIdentifier;
    private LocalDateTime transactionDate;
    private TillDTO till;
    private CashierDTO cashier;
    private PromotionsDTO promotions;
    private List<PurchasedItemDTO> purchasedItems;
    private TaxInvoiceDTO taxInvoice;

}
