package za.co.serengti.merchants.service;

import za.co.serengti.merchants.dto.ProductDTO;
import za.co.serengti.merchants.entity.POSSystem;
import za.co.serengti.merchants.entity.Product;
import za.co.serengti.merchants.entity.ProductIdentifier;
import za.co.serengti.merchants.entity.Store;
import za.co.serengti.merchants.repository.ProductIdentifierRepository;
import za.co.serengti.merchants.repository.ProductRepository;
import za.co.serengti.util.RecordMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.Optional;

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

    @Transactional
    public Product save(Product product) {
        productRepository.persist(product);
        return product;
    }

    @Transactional
    public Product findOrSaveProduct(ProductDTO productDTO, POSSystem posSystem, Store store) {
        ProductIdentifier productIdentifier = productIdentifierRepository.findBySkuAndStoreIdAndPosSystemId(productDTO.getSku(), store, posSystem);
        if (productIdentifier != null) {
            // If ProductIdentifier is found, return the associated Product
            return productIdentifier.getProduct();
        } else {
            // If no ProductIdentifier is found, create and save a new Product
            Product product = mapper.convert(productDTO, Product.class);
            productRepository.persist(product);

            // Create and save a new ProductIdentifier linking the Product to the sku, storeId, and posSystemId
            ProductIdentifier newProductIdentifier = new ProductIdentifier();
            newProductIdentifier.setProduct(product);
            newProductIdentifier.setSku(productDTO.getSku());
            newProductIdentifier.setStore(store);
            newProductIdentifier.setPosSystem(posSystem);
            productIdentifierRepository.persist(newProductIdentifier);

            return product;
        }
}
}