package za.co.serengti.merchants.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import za.co.serengti.merchants.entity.MetaData;

@ApplicationScoped
public class MerchantService {

    private final StoreService storeService;
    private final POSService posService;

    @Inject
    public MerchantService(POSService posService, StoreService storeService) {
        this.posService = posService;
        this.storeService = storeService;

    }

    public void subscribe(MetaData metaData) {
        //TODO subscribe and register as a merchant providing your store details and point of sale details
    }

    public MetaData retrieveMetaData(long posId, long storeId){
        return MetaData.
                builder()
                .posSystem(posService.findPOS(posId))
                .store(storeService.find(storeId))
                .build();
    }

}
