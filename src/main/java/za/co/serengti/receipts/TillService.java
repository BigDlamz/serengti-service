package za.co.serengti.receipts;

import jakarta.transaction.Transactional;

public interface TillService {
    Till find(Long tillId);

    @Transactional
    TillDTO save(TillDTO till);
}
