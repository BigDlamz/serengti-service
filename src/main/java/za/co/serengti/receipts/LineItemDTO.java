package za.co.serengti.receipts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LineItemDTO {

    @JsonIgnore
    private Long lineItemID;
    private String productName;
    private String productDescription;
    private Integer quantity;
    private BigDecimal unitPrice;
    @JsonIgnore
    private ReceiptDTO receipt;

}