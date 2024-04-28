package za.co.serengti.receipts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import za.co.serengti.shoppers.ShopperDTO;
import za.co.serengti.merchants.MerchantDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class  ReceiptDTO {

    private Long receiptId;
    private LocalDateTime timestamp;
    private MerchantDTO merchant;
    private ShopperDTO shopper;
    private List<LineItemDTO> lineItems;
    private TillDTO till;
    private CashierDTO cashier;
    private BigDecimal discountAmount;
    private BigDecimal subTotal;
    private BigDecimal vatRate;
    private BigDecimal vatAmount;
    private BigDecimal totalDueAfterTax;
    private BigDecimal amountPaid;
    private Boolean viewed;

}

