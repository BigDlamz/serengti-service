package za.co.serengti.receipts.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class TaxInvoiceDTO {
    private String serialNumber;
    private BigDecimal amountBeforeTax;
    private BigDecimal amountAfterTax;

}
