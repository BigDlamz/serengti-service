package za.co.serengti.receipt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReceiptDTO {
    private StoreDTO store;
    private POSSystemDTO posSystem;
    private CustomerDTO customer;
    private LocalDateTime timestamp;
    private BigDecimal totalAmountPaid;
    private List<ProductDTO> receiptItems;

}

