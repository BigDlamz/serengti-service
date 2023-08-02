package za.co.serengti.receipts.service;

import za.co.serengti.merchants.dto.ProductDTO;
import za.co.serengti.merchants.entity.POSSystem;
import za.co.serengti.merchants.entity.Store;
import za.co.serengti.merchants.service.ProductService;
import za.co.serengti.receipts.entity.LineItem;

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

    public List<LineItem> processLineItems(List<ProductDTO> purchases, POSSystem posSystem, Store store) {
        return purchases
                .stream()
                .map(prod ->
                        LineItem.builder()
                                .product(productService.findOrSaveProduct(prod,posSystem,store))
                                .quantity(prod.getQuantity())
                                .build()
                ).collect(Collectors.toList());
    }
}