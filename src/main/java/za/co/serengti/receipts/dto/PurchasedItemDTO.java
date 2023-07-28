package za.co.serengti.receipts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchasedItemDTO {
    String sku;
    String ean13Code;
    String universalProductCode;
    String name;
    String description;
    BigDecimal itemPricePaid;
    Integer quantity;
}
