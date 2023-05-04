package za.co.serengti.receipts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import za.co.serengti.customers.dto.CustomerDTO;
import za.co.serengti.merchants.dto.POSSystemDTO;
import za.co.serengti.merchants.dto.StoreDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptDTO {
    private Long id;
    private POSSystemDTO posSystem;
    private StoreDTO store;
    private CustomerDTO customer; ;
    private LocalDateTime timestamp;
    private BigDecimal totalAmountPaid;
    private List<LineItemDTO> lineItems;

    public void calculateTotalAmountPaid() {
        totalAmountPaid =  lineItems.stream()
                .map(lineItem -> lineItem.getProduct().getPrice()
                        .multiply(BigDecimal.valueOf(lineItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }
}

