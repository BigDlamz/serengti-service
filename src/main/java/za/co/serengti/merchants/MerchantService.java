package za.co.serengti.merchants;

import za.co.serengti.receipts.entity.POSSystemEntity;
import za.co.serengti.receipts.entity.StoreEntity;
import za.co.serengti.receipts.repository.POSRepository;
import za.co.serengti.receipts.repository.StoreRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MerchantService {

    private final POSRepository posRepository;
    private final StoreRepository storeRepo;

    @Inject
    public MerchantService(POSRepository posRepository, StoreRepository storeRepo) {
        this.posRepository = posRepository;
        this.storeRepo = storeRepo;
    }

    public StoreEntity findStore(Long storeId) {
        return storeRepo.findById(storeId);
    }

    public POSSystemEntity findPOS(Long posId) {
        return posRepository.findById(posId);
    }
}
