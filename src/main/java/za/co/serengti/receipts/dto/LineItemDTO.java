package za.co.serengti.receipts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import za.co.serengti.merchants.dto.ProductDTO;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LineItemDTO {
    ReceiptDTO receipt;
    ProductDTO product;

}
