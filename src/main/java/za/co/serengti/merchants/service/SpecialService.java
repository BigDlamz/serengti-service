package za.co.serengti.merchants.service;

import za.co.serengti.merchants.entity.Special;
import za.co.serengti.merchants.repository.SpecialRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class SpecialService {

    private final SpecialRepository specialRepository;

    @Inject
    public SpecialService(SpecialRepository specialRepository) {
        this.specialRepository = specialRepository;
    }

    public List<Special> findSpecials(Long storeId ){
        return specialRepository.findByStoreId(storeId);
    }
}
