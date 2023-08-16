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
        return list("SELECT r FROM Receipt r JOIN r.customer c WHERE TYPE(c) = EmailCustomer AND LOWER(c.emailAddress) = LOWER(?1)", email);
    }

    public long findCustomerTotalReceipts(String email) {
        return count("FROM Receipt r, EmailCustomer c WHERE r.customer.customerID = c.customerID AND LOWER(c.emailAddress) = LOWER(?1)", email);
    }
}



