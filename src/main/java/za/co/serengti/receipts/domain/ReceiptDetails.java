package za.co.serengti.receipts.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import za.co.serengti.merchants.dto.ProductDTO;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptDetails {

    private LocalDateTime timestamp;
    private List<ProductDTO> lineItems;
}
