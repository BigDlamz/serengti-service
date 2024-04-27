package za.co.serengti.receipts;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LineItemMapper {

    public LineItem toEntity(LineItemDTO dto) {

        return LineItem.builder()
                .lineItemID(dto.getLineItemID())
                .productName(dto.getProductName())
                .productDescription(dto.getProductDescription())
                .unitPrice(dto.getUnitPrice())
                .quantity(dto.getQuantity())
                .build();

    }

    public LineItemDTO toDTO(LineItem entity) {

        return LineItemDTO.builder()
                .lineItemID(entity.getLineItemID())
                .productName(entity.getProductName())
                .productDescription(entity.getProductDescription())
                .unitPrice(entity.getUnitPrice())
                .quantity(entity.getQuantity())
                .build();

    }
}
