package za.co.serengti.receipts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import za.co.serengti.users.dto.CustomerDTO;
import za.co.serengti.merchants.dto.POSSystemDTO;
import za.co.serengti.merchants.dto.StoreDTO;

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
    private POSSystemDTO posSystem;
    private StoreDTO store;
    private CustomerDTO customer;
    private List<LineItemDTO> lineItems;
    private TillDTO till;
    private CashierDTO cashier;
    private PromotionsDTO promotions;
    public BigDecimal discountAmount;
    public BigDecimal subTotal;
    public BigDecimal vatRate;
    public BigDecimal vatAmount;
    public BigDecimal totalDueAfterTax;
    public BigDecimal amountPaid;
    public BigDecimal changeDue;

}

