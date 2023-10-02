package za.co.serengti.merchants.entity;

import za.co.serengti.util.ProductCategory;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

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
