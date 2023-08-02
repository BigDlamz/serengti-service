package za.co.serengti.merchants.service;

import za.co.serengti.merchants.entity.Store;
import za.co.serengti.merchants.repository.StoreRepository;
import za.co.serengti.util.RecordMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class StoreService {

    private final StoreRepository storeRepository;
    private final RecordMapper mapper;

    @Inject
    public StoreService(StoreRepository storeRepository, RecordMapper mapper) {
        this.storeRepository = storeRepository;
        this.mapper = mapper;
    }

    public Store find(Long storeId) {
        return storeRepository.findById(storeId);
    }

}
