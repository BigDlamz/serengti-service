package za.co.serengti.merchants.service;

import za.co.serengti.merchants.dto.ProductDTO;
import za.co.serengti.merchants.entity.*;
import za.co.serengti.merchants.mapper.ProductMapper;
import za.co.serengti.merchants.repository.ProductIdentifierRepository;
import za.co.serengti.merchants.repository.ProductRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class ProductService {

    private final ProductIdentifierRepository identifierRepository;
    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    public ProductService(ProductRepository productRepository, ProductIdentifierRepository identifierRepository, ProductMapper mapper) {
        this.productRepository = productRepository;
        this.identifierRepository = identifierRepository;
        this.mapper = mapper;
    }

    @Transactional
    public Product save(Product product) {
        productRepository.persist(product);
        return product;
    }

    @Transactional
    public Product findOrSaveProduct(ProductDTO productDTO, MetaData meta) {
        ProductIdentifier productIdentifier = identifierRepository.findBySkuAndPosSystemAndStore(productDTO.getSku(), meta);
        if (productIdentifier != null) {
            return productIdentifier.getProduct();
        } else {
            Product product = saveProduct(productDTO);
            saveProductIdentifier(productDTO, meta, product);
            return product;
        }
    }

    private Product saveProduct(ProductDTO productDTO) {
        Product product = mapper.toEntity(productDTO);
        productRepository.persist(product);
        return product;
    }

    private void saveProductIdentifier(ProductDTO productDTO, MetaData meta, Product product) {
        ProductIdentifier newIdentifier = mapper.toProductIdentifierEntity(productDTO, product, meta);
        identifierRepository.persist(newIdentifier);
    }

}