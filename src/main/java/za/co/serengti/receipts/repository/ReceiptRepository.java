package za.co.serengti.receipts.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import za.co.serengti.receipts.entity.Receipt;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ReceiptRepository implements PanacheRepository<Receipt> {

    public Receipt save(Receipt receipt) {
        persistAndFlush(receipt);
        return receipt;
    }

    public List<Receipt> findAllByCustomerEmail(String email) {
        return list("SELECT r FROM Receipt r JOIN r.user c WHERE TYPE(c) = EmailUser AND LOWER(c.emailAddress) = LOWER(?1)", email);
    }

    public long findCustomerTotalReceipts(String email) {
        return count("FROM Receipt r, EmailUser c WHERE r.user.userId = c.userId AND LOWER(c.emailAddress) = LOWER(?1)", email);
    }
}



