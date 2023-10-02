package za.co.serengti.receipts.mapper;

import za.co.serengti.merchants.mapper.ProductMapper;
import za.co.serengti.receipts.dto.LineItemDTO;
import za.co.serengti.receipts.entity.LineItem;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LineItemMapper {

    final private ProductMapper productMapper;

    public LineItemMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public LineItemDTO toDto(LineItem entity) {
        return LineItemDTO.builder()
                .lineItemID(entity.getLineItemID())
                .product(productMapper.toDto(entity.getProduct()))  // Assuming you have a ProductMapper class
                .quantity(entity.getQuantity())
                .build();
    }
}
