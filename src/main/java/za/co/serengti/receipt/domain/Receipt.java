package za.co.serengti.receipt.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Receipt {
    private Store store;
    private POSSystem posSystem;
    private Customer customer;
    private LocalDateTime timestamp;
    private List<ReceiptItem> receiptItems;
    private BigDecimal totalAmountPaid;

    public void calculateTotalAmountPaid() {

        totalAmountPaid =  receiptItems.stream()
                .map(receiptItem -> receiptItem.getPrice()
                        .multiply(BigDecimal.valueOf(receiptItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }
}

