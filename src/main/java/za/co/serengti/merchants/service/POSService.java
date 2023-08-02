package za.co.serengti.merchants.service;

import za.co.serengti.merchants.dto.POSSystemDTO;
import za.co.serengti.merchants.entity.POSSystem;
import za.co.serengti.merchants.repository.POSRepository;
import za.co.serengti.util.RecordMapper;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class POSService {

    private final POSRepository posRepository;
    private final RecordMapper mapper;

    public POSService(POSRepository posRepository, RecordMapper mapper) {
        this.posRepository = posRepository;
        this.mapper = mapper;
    }

    public POSSystem findPOS(Long posId) {
        POSSystem pos = posRepository.findById(posId);
        if (pos == null) {
            throw new IllegalStateException("This POS is not stored in our database: " + posId);
        }
        return pos;
    }
}
