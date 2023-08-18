package za.co.serengti.merchants.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import za.co.serengti.merchants.entity.Product;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecialDTO {

    private Long specialId;
    private StoreDTO store;
    private Product product;
    private String name;
    private String description;
    private String specialImageUrl;
    private BigDecimal oldPrice;
    private BigDecimal newPrice;
    private Date startDate;
    private Date endDate;

}
