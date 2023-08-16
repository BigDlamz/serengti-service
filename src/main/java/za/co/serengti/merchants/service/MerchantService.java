package za.co.serengti.merchants.service;

import za.co.serengti.merchants.entity.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MerchantService {

    private final POSService posService;
    private final StoreService storeService;
    private final ProductService productService;

    @Inject
    public MerchantService(POSService posService, StoreService storeService, ProductService productService) {
        this.posService = posService;
        this.storeService = storeService;
        this.productService = productService;
    }

    public void register(MetaData metaData) {
        //TODO
    }
    public void registerProduct(Product product, MetaData metaData) {
        //TODO
    }
    public Store findStore(Long storeId) {
        return storeService.find(storeId);
    }

    public POSSystem findPosSystem(Long posId) {
        return  posService.findPOS(posId);
    }

    public void saveSpecial(Special special){

    }
}
