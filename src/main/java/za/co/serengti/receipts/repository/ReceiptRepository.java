package za.co.serengti.receipts.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import za.co.serengti.receipts.entity.ReceiptEntity;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ReceiptRepository implements PanacheRepository<ReceiptEntity> {

    public ReceiptEntity merge(ReceiptEntity receipt) {
        persistAndFlush(receipt);
        return receipt;

    }
}