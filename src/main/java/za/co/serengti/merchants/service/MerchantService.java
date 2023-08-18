package za.co.serengti.merchants.service;

import za.co.serengti.merchants.entity.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class MerchantService {

    private final ProductService productService;
    private final StoreService storeService;
    private final POSService posService;
    private final SpecialService specialService;


    @Inject
    public MerchantService(POSService posService, StoreService storeService, ProductService productService, SpecialService specialService) {
        this.posService = posService;
        this.storeService = storeService;
        this.productService = productService;
        this.specialService = specialService;
    }

    public void register(MetaData metaData) {
        //TODO
    }

    public Store findStore(Long storeId) {
        return storeService.find(storeId);
    }

    public POSSystem findPosSystem(Long posId) {
        return  posService.findPOS(posId);
    }

    public void saveProduct(Product product, MetaData metaData) {
        //TODO
    }

    public void saveSpecial(Special special){

    }

    public List<Special> findSpecials(Long storeId ){
        return specialService.findSpecials(storeId);
    }
}
