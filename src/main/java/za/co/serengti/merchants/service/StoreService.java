package za.co.serengti.merchants.service;

import za.co.serengti.merchants.entity.Store;
import za.co.serengti.merchants.repository.StoreRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class StoreService {

    private final StoreRepository storeRepository;

    @Inject
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;

    }

    public Store find(Long storeId) {
        return storeRepository.findById(storeId);
    }

}
