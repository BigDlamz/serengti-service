package za.co.serengti.merchants.entity;

import za.co.serengti.util.ProductCategory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ProductCategoryConverter implements AttributeConverter<ProductCategory, String> {

    @Override
    public String convertToDatabaseColumn(ProductCategory category) {
        if (category == null) {
            return null;
        }
        return category.getValue();
    }

    @Override
    public ProductCategory convertToEntityAttribute(String dbValue) {
        if (dbValue == null) {
            return null;
        }
        return ProductCategory.fromDbValue(dbValue);
    }
}
