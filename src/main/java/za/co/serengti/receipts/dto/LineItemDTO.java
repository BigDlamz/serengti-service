package za.co.serengti.receipts.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Long id;
    private ProductDTO product;
    private Integer quantity;
}