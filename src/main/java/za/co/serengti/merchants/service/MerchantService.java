package za.co.serengti.merchants.service;

import za.co.serengti.merchants.dto.POSSystemDTO;
import za.co.serengti.merchants.dto.StoreDTO;
import za.co.serengti.merchants.entity.POSSystem;
import za.co.serengti.merchants.entity.Store;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MerchantService {

    private final POSService posService;
    private final StoreService storeService;

    @Inject
    public MerchantService(POSService posService, StoreService storeService) {
        this.posService = posService;
        this.storeService = storeService;
    }

    public Store findStore(Long storeId) {
     return storeService.find(storeId);
    }

    public POSSystem findPosSystem(Long posId) {
        return  posService.findPOS(posId);
    }

    //TODO: merchants should be able to register their POS System on our database
    //TODO: merchants should be able to register their stores
    //TODO: merchants should be able to register their products
}
