package za.co.serengti.receipts;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TillServiceImpl implements TillService {

    private final TillRepository tillRepository;
    private final TillMapper convertor;

    @Inject
    public TillServiceImpl(TillRepository tillRepository, TillMapper convertor) {
        this.tillRepository = tillRepository;
        this.convertor = convertor;
    }

    @Override
    public Till find(Long tillId) {

        return tillRepository.findById(tillId);

    }

    @Override
    @Transactional
    public TillDTO save(TillDTO till) {

        Till entity = convertor.toEntity(till);
        Till savedEntity = tillRepository.save(entity);
        return convertor.toDto(savedEntity);

    }

}
