package za.co.serengti.receipt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptItemDTO {

    public Long id;
    private Integer quantity;
    private ReceiptDTO receipt;
    private ProductDTO product;

}
