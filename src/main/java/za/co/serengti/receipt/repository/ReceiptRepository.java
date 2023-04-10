package za.co.serengti.receipt.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import za.co.serengti.receipt.entity.Receipt;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ReceiptRepository implements PanacheRepository<Receipt> {

    @Override
    public Receipt findById(Long receiptID) {
        return PanacheRepository.super.findById(receiptID);
    }
}
