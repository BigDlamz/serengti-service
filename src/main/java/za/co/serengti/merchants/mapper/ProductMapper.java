package za.co.serengti.merchants.mapper;

import za.co.serengti.merchants.dto.ProductDTO;
import za.co.serengti.merchants.entity.*;
import za.co.serengti.util.ProductCategory;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductMapper {

    public Product toEntity(ProductDTO dto) {
        return Product.builder()
                .productID(dto.getProductID())
                .name(dto.getName())
                .description(dto.getDescription())
                .unitPrice(dto.getUnitPrice())
                .category(ProductCategory.fromDbValue(dto.getCategory()))
                .build();
    }

    public ProductIdentifier toProductIdentifierEntity(ProductDTO dto, Product product, MetaData meta) {
        return ProductIdentifier.builder()
                .product(product)
                .store(meta.getStore())
                .posSystem(meta.getPosSystem())
                .ean13Code(dto.getEan13Code())
                .universalProductCode(dto.getUniversalProductCode())
                .sku(dto.getSku())
                .build();
    }

    public ProductDTO toDto(Product product) {
        return ProductDTO.builder()
                .productID(product.getProductID())
                .name(product.getName())
                .description(product.getDescription())
          //      .ean13Code(productIdentifier.getEan13Code())
          //      .universalProductCode(productIdentifier.getUniversalProductCode())
          //      .sku(productIdentifier.getSku())
                .unitPrice(product.getUnitPrice())
                .category(product.getCategory().getValue())
                .build();
    }
}
