package za.co.serengti.receipts;

import java.math.BigDecimal;
import java.util.List;

public interface FinanceService {

    BigDecimal ZA_VAT_RATE = new BigDecimal("0.15");

    BigDecimal getSubTotal(List<LineItemDTO> items);

    BigDecimal getVatAmount(BigDecimal subTotal);

    BigDecimal getTotalDue(BigDecimal subTotal, BigDecimal vatAmount);

}
