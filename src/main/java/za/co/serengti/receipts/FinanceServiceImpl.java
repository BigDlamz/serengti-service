package za.co.serengti.receipts;

import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigDecimal;
import java.util.List;

@ApplicationScoped
public class FinanceServiceImpl implements FinanceService {

    @Override
    public BigDecimal getSubTotal(List<LineItemDTO> items) {

        return items
                .stream()
                .map(item -> item.getUnitPrice().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    @Override
    public BigDecimal getVatAmount(BigDecimal subTotal) {

        return subTotal.multiply(ZA_VAT_RATE);

    }

    @Override
    public BigDecimal getTotalDue(BigDecimal subTotal, BigDecimal vatAmount) {

        return subTotal.add(vatAmount);

    }
}
