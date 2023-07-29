package za.co.serengti.merchants.service;

import za.co.serengti.merchants.dto.POSSystemDTO;
import za.co.serengti.merchants.dto.ProductDTO;
import za.co.serengti.merchants.dto.StoreDTO;
import za.co.serengti.merchants.entity.POSSystem;
import za.co.serengti.merchants.entity.Product;
import za.co.serengti.merchants.entity.ProductIdentifier;
import za.co.serengti.merchants.entity.Store;
import za.co.serengti.merchants.repository.ProductIdentifierRepository;
import za.co.serengti.merchants.repository.ProductRepository;
import za.co.serengti.receipts.dto.PurchasedItemDTO;
import za.co.serengti.util.RecordMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProductService {

    private final ProductIdentifierRepository productIdentifierRepository;
    private final ProductRepository productRepository;
    private final RecordMapper mapper;

    public ProductService(ProductRepository productRepository, ProductIdentifierRepository productIdentifierRepository, RecordMapper mapper) {
        this.productRepository = productRepository;
        this.productIdentifierRepository = productIdentifierRepository;
        this.mapper = mapper;
    }

    public ProductDTO find(Long posSystemID, Long storeID, String sku) {
        Optional<Product> product = productRepository.findBySku(posSystemID, storeID, sku);
        return mapper.convert(product.orElse(null), ProductDTO.class);
    }

    @Transactional
    public Product save(Product product) {
        productRepository.persist(product);
        return product;
    }

    @Transactional
    public List<ProductDTO> findOrSaveProducts(List<PurchasedItemDTO> purchasedItems, POSSystemDTO posSystem, StoreDTO store) {
        return purchasedItems
                .stream()
                .map(purchasedItem -> {
                    Optional<ProductIdentifier> optionalIdentifier = Optional.ofNullable(productIdentifierRepository.findBySkuAndStoreIdAndPosSystemId(purchasedItem.getSku(), mapper.convert(store,Store.class), mapper.convert(posSystem, POSSystem.class)));
                    Product product;
                    if (optionalIdentifier.isPresent()) {
                        product = optionalIdentifier.get().getProduct();
                    } else {
                        Product prod = Product.builder()
                                .name(purchasedItem.getName()) // Assuming you have getName in PurchasedItemDTO
                                .description(purchasedItem.getDescription()) // Assuming you have getDescription in PurchasedItemDTO
                                .build();

                        product = productRepository.save(prod);

                        ProductIdentifier identifier = ProductIdentifier.builder()
                                .product(product)
                                .store(mapper.convert(store, Store.class))
                                .posSystem(mapper.convert(posSystem, POSSystem.class))
                                .sku(purchasedItem.getSku())
                                .build();
                        // set ean13Code and universalProductCode if they are available in PurchasedItemDTO
                        productIdentifierRepository.persist(identifier);
                    }
                    return mapper.convert(product, ProductDTO.class);
                })
                .collect(Collectors.toList());
    }
}