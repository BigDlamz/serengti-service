package za.co.serengti.receipts.service;

import za.co.serengti.merchants.dto.ProductDTO;
import za.co.serengti.merchants.entity.MetaData;
import za.co.serengti.merchants.service.ProductService;
import za.co.serengti.receipts.entity.LineItem;
import za.co.serengti.receipts.entity.Receipt;
import za.co.serengti.util.ProductCategory;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class LineItemsService {
    private final ProductService productService;

    @Inject
    public LineItemsService(ProductService productService) {
        this.productService = productService;
    }

    public List<LineItem> processLineItems(List<ProductDTO> purchases, MetaData meta, Receipt receipt) {
        return purchases
                .stream()
                .map(prod -> {
                    // Validate the category
                    if (!ProductCategory.isValidCategory(prod.getCategory())) {
                        throw new BadRequestException("Invalid category value provided for product: " + prod.getName());
                    }
                    // Convert to LineItem
                    return LineItem.builder()
                            .product(productService.findOrSaveProduct(prod, meta))
                            .quantity(prod.getQuantity())
                            .receipt(receipt)
                            .build();
                })
                .collect(Collectors.toList());
    }

    public void saveLineItems(List<LineItem> lineItems) {
        lineItems.forEach(lineItem -> {
            lineItem.persist();
        });
    }
}