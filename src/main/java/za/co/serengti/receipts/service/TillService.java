package za.co.serengti.receipts.service;

import za.co.serengti.receipts.dto.TillDTO;
import za.co.serengti.receipts.entity.Till;
import za.co.serengti.receipts.repository.TillRepository;
import za.co.serengti.util.RecordMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class TillService {
    private final TillRepository tillRepository;
    private final RecordMapper mapper;

    @Inject
    public TillService(TillRepository tillRepository, RecordMapper mapper) {
        this.tillRepository = tillRepository;
        this.mapper = mapper;
    }

    public TillDTO find(Long tillId) {
        Till till = tillRepository.findById(tillId);
        return mapper.convert(till, TillDTO.class);
    }

    public TillDTO save(TillDTO tillDTO) {
        Till till = tillRepository.save(mapper.convert(tillDTO, Till.class));
        return mapper.convert(till, TillDTO.class);
    }

}
