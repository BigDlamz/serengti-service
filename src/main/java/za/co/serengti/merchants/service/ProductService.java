package za.co.serengti.merchants.service;

import za.co.serengti.merchants.dto.POSSystemDTO;
import za.co.serengti.merchants.dto.ProductDTO;
import za.co.serengti.merchants.dto.StoreDTO;
import za.co.serengti.merchants.entity.Product;
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

    private final ProductRepository productRepository;
    private final RecordMapper mapper;

    public ProductService(ProductRepository productRepository, RecordMapper mapper) {
        this.productRepository = productRepository;
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
                    Optional<Product> optional = productRepository.findBySku(posSystem.getId(), store.getId(), purchasedItem.getSku());
                    Product product;
                    if (optional.isPresent()) {
                        product = optional.get();
                    } else {
                        Product prod = null;
                        product = productRepository.save(prod);
                    }
                    return mapper.convert(product, ProductDTO.class);
                })
                .collect(Collectors.toList());
    }
}
