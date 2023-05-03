package za.co.serengti.merchants.service;

import za.co.serengti.merchants.dto.POSSystemDTO;
import za.co.serengti.merchants.dto.StoreDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MerchantManager {

    private final POSService posService;
    private final StoreService storeService;

    @Inject
    public MerchantManager(POSService posService, StoreService storeService) {
        this.posService = posService;
        this.storeService = storeService;
    }

    public StoreDTO findStore(Long storeId) {
     return storeService.find(storeId);
    }

    public POSSystemDTO findPosSystem(Long posId) {
        return  posService.findPOS(posId);
    }

    //TODO: merchants should be able to register their POS System on our database
    //TODO: merchants should be able to register their stores
    //TODO: merchants should be able to register their products
}
