package za.co.serengti.application;

import lombok.Builder;
import lombok.Data;
import za.co.serengti.merchants.dto.ProductDTO;
import za.co.serengti.receipts.dto.CashierDTO;
import za.co.serengti.receipts.dto.PromotionsDTO;
import za.co.serengti.receipts.dto.TillDTO;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class SaveReceiptRequest {

    @NotNull(message =  "posSystemId is required")
    private Long posSystemId;
    @NotNull(message =  "storeId is required")
    private Long storeId;
    private String customerIdentifier;
    private LocalDateTime transactionDate;
    private TillDTO till;
    private CashierDTO cashier;
    private PromotionsDTO promotions;
    private List<ProductDTO> lineItems;
    public BigDecimal discountAmount;
    public BigDecimal subTotal;
    public BigDecimal vatRate;
    public BigDecimal vatAmount;
    public BigDecimal totalDueAfterTax;
    public BigDecimal amountPaid;
    public BigDecimal changeDue;

}
