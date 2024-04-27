package za.co.serengti.merchants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecialDTO {

    private Long specialId;
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal oldPrice;
    private BigDecimal newPrice;
    private LocalDate startDate;
    private LocalDate endDate;

}
