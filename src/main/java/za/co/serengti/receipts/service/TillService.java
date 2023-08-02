package za.co.serengti.receipts.service;

import za.co.serengti.receipts.entity.Till;
import za.co.serengti.receipts.repository.TillRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class TillService {
    private final TillRepository tillRepository;

    @Inject
    public TillService(TillRepository tillRepository) {
        this.tillRepository = tillRepository;
    }

    public Till find(Long tillId) {
        return tillRepository.findById(tillId);
    }

    public Till save(Till till) {
        return tillRepository.save(till);
    }

}
