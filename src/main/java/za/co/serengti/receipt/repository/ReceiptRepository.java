package za.co.serengti.receipt.repository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import za.co.serengti.receipt.entity.Receipt;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ReceiptRepository implements PanacheRepositoryBase<Receipt, Long> {

    @Override
    public void persist(Receipt receipt) {
        PanacheRepositoryBase.super.persist(receipt);
    }
    @Override
    public Receipt findById(Long id) {
        return PanacheRepositoryBase.super.findById(id);
    }

    @Override
    public PanacheQuery<Receipt> findAll() {
        return PanacheRepositoryBase.super.findAll();
    }
}
