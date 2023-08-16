package za.co.serengti.merchants.mapper;
import lombok.extern.slf4j.Slf4j;
import za.co.serengti.util.ProductCategory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Slf4j
@Converter(autoApply = true)
public class ProductCategoryConverter implements AttributeConverter<ProductCategory, String> {

    @Override
    public String convertToDatabaseColumn(ProductCategory category) {
        if (category == null) {
            return null;
        }
        String categoryValue = category.getValue();
        log.info("Converted ProductCategory to database column: {}", categoryValue);
        return categoryValue;
    }

    @Override
    public ProductCategory convertToEntityAttribute(String dbValue) {
        if (dbValue == null) {
            return null;
        }
        return ProductCategory.fromDbValue(dbValue);
    }
}
