package za.co.serengti.merchants.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import za.co.serengti.util.ProductCategory;

import jakarta.persistence.Column;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    @JsonIgnore
    private Long productID;
    private String name;
    private String description;
    @JsonInclude(JsonInclude.Include. NON_NULL)
    private String ean13Code;
    @JsonInclude(JsonInclude.Include. NON_NULL)
    private String universalProductCode;
    @JsonInclude(JsonInclude.Include. NON_NULL)
    private String sku;
    private String category;
    private int quantity;
    private BigDecimal unitPrice;

}
