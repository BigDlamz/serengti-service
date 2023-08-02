package za.co.serengti.application;

import lombok.Builder;
import lombok.Data;
import za.co.serengti.merchants.dto.ProductDTO;
import za.co.serengti.receipts.dto.CashierDTO;
import za.co.serengti.receipts.dto.PromotionsDTO;
import za.co.serengti.receipts.dto.TaxInvoiceDTO;
import za.co.serengti.receipts.dto.TillDTO;

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
    private List<ProductDTO> purchasedItems;
    private TaxInvoiceDTO taxInvoice;

}
