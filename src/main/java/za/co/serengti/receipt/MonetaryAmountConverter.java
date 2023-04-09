package za.co.serengti.receipt;

import javax.money.MonetaryAmount;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.MonetaryConversions;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import org.javamoney.moneta.Money;

import java.math.BigDecimal;

@Converter(autoApply = true)
public class MonetaryAmountConverter implements AttributeConverter<MonetaryAmount, BigDecimal> {

    private static final CurrencyConversion conversion = MonetaryConversions.getConversion("ZAR");

    @Override
    public BigDecimal convertToDatabaseColumn(MonetaryAmount monetaryAmount) {
        if (monetaryAmount == null) {
            return null;
        }
        return monetaryAmount.with(conversion).getNumber().numberValue(BigDecimal.class);
    }

    @Override
    public MonetaryAmount convertToEntityAttribute(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return null;
        }
        return Money.of(bigDecimal, conversion.getCurrency());
    }
}
