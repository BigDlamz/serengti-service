package za.co.serengti.receipts.service;

import za.co.serengti.merchants.entity.MetaData;
import za.co.serengti.receipts.dto.TillDTO;
import za.co.serengti.receipts.entity.Till;
import za.co.serengti.receipts.mapper.TillMapper;
import za.co.serengti.receipts.repository.TillRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class TillService {
    private final TillRepository tillRepository;
    private final TillMapper tillMapper;

    @Inject
    public TillService(TillRepository tillRepository, TillMapper tillMapper) {
        this.tillRepository = tillRepository;
        this.tillMapper = tillMapper;
    }

    public Till find(Long tillId) {
        return tillRepository.findById(tillId);
    }

    public Till save(Till till) {
        return tillRepository.save(till);
    }
    public TillDTO toDto(Till entity) {
        return tillMapper.toDto(entity);
    }

    public Till toEntity(TillDTO dto, MetaData meta){
        return tillMapper.toEntity(dto, meta );
    }

}
