package za.co.serengti.merchants.service;

import za.co.serengti.merchants.dto.POSSystemDTO;
import za.co.serengti.merchants.dto.ProductDTO;
import za.co.serengti.merchants.dto.StoreDTO;
import za.co.serengti.merchants.entity.POSSystem;
import za.co.serengti.merchants.entity.Product;
import za.co.serengti.merchants.entity.Store;
import za.co.serengti.merchants.repository.ProductRepository;
import za.co.serengti.receipts.dto.PurchasedItem;
import za.co.serengti.util.RecordMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@ApplicationScoped
public class ProductService {

    private final ProductRepository productRepository;
    private final RecordMapper mapper;

    public ProductService(ProductRepository productRepository, RecordMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    public ProductDTO find(Long posSystemID, Long storeID, String sku) {
        Product product = productRepository.findByPosStoreAndSku(posSystemID, storeID, sku);
        return mapper.convert(product, ProductDTO.class);
    }

    @Transactional
    public Product save(Product product) {
        productRepository.persist(product);
        return product;
    }

    @Transactional
    public List<ProductDTO> findOrSavePurchasedProducts(List<PurchasedItem> purchasedItems, POSSystemDTO posSystem, StoreDTO store) {

        List<ProductDTO> products = purchasedItems.stream()
                .map(purchasedItem -> {
                    Optional<Product> optionalProduct = ofNullable(productRepository.findByPosStoreAndSku(posSystem.getId(), store.getId(), purchasedItem.getSku()));
                    Product product;
                    if (optionalProduct.isPresent()) {
                        product = optionalProduct.get();
                    } else {

                        Product prod = Product.builder()
                                .posSystem(mapper.convert(posSystem, POSSystem.class))
                                .store(mapper.convert(store, Store.class))
                                .sku(purchasedItem.getSku())
                                .description("Description")
                                .price(purchasedItem.getPrice())
                                .quantity(null) //DROP the quantity column but keep the quantity field in the DTO and entity
                                .build();

                        product = productRepository.save(prod);
                    }
                    product.setQuantity(purchasedItem.getQuantity());

                    return mapper.convert(product, ProductDTO.class);
                })
                .collect(Collectors.toList());
        return products;
    }
}
