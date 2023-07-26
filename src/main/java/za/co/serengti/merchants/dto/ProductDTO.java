package za.co.serengti.merchants.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    @JsonIgnore
    private Long id;
    private String name;
    private String description;
    private String EAN13;
    private String universalProductCode;
    private String SKU;
    private BigDecimal price;
    @JsonIgnore
    private Integer quantity;
}
