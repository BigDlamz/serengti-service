package za.co.serengti.receipts.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import za.co.serengti.receipts.entity.Receipt;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ReceiptRepository implements PanacheRepository<Receipt> {

    public Long save(Receipt receipt) {
        persistAndFlush(receipt);
        return receipt.getReceipt_id();
    }
}
