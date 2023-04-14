package za.co.serengti.receipt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptDetailsDTO {

    private LocalDateTime transactionDate;
    private List<ProductDTO> lineItems;
    private BigDecimal totalAmountPaid;

}
