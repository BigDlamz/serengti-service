package za.co.serengti.receipt.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptItem {

    public  String sku;
    private Integer quantity;
    private BigDecimal price;

}
