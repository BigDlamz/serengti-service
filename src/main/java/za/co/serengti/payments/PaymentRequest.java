package za.co.serengti.payments;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import za.co.serengti.receipts.CashierDTO;
import za.co.serengti.receipts.LineItemDTO;
import za.co.serengti.receipts.TillDTO;
import za.co.serengti.shoppers.ShopperDTO;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentRequest {

    private Long merchantId;
    private ShopperDTO shopper;
    private LocalDateTime txDate;
    private CashierDTO cashier;
    private TillDTO till;
    private List<LineItemDTO> lineItems;

}
