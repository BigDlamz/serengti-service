package za.co.serengti.receipts.service;

import za.co.serengti.merchants.dto.ProductDTO;
import za.co.serengti.merchants.entity.MetaData;
import za.co.serengti.merchants.service.ProductService;
import za.co.serengti.receipts.entity.LineItem;
import za.co.serengti.receipts.entity.Receipt;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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
                .map(prod ->
                        LineItem.builder()
                                .product(productService.findOrSaveProduct(prod,meta))
                                .quantity(prod.getQuantity())
                                .receipt(receipt)
                                .build()
                ).collect(Collectors.toList());
    }

    public void saveLineItems(List<LineItem> lineItems) {
        lineItems.forEach(lineItem -> {
            lineItem.persist();
        });
    }
}