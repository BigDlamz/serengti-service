package za.co.serengti.merchants.service;

import za.co.serengti.merchants.entity.POSSystem;
import za.co.serengti.merchants.repository.POSRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class POSService {

    private final POSRepository posRepository;

    public POSService(POSRepository posRepository) {
        this.posRepository = posRepository;
    }

    public POSSystem findPOS(Long posId) {
        POSSystem pos = posRepository.findById(posId);
        if (pos == null) {
            throw new IllegalStateException("This POS is not stored in our database: " + posId);
        }
        return pos;
    }
}
