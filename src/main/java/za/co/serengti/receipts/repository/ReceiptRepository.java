package za.co.serengti.receipts.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import za.co.serengti.receipts.entity.Receipt;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class ReceiptRepository implements PanacheRepository<Receipt> {

    public Receipt save(Receipt receipt) {
        persistAndFlush(receipt);
        return receipt;
    }

    public List<Receipt> findAllReceiptsByCustomerEmailAndDate(String email, LocalDate date) {
        LocalDateTime startDateTime = date.atStartOfDay();
        LocalDateTime endDateTime = date.atTime(23, 59, 59);

        return list("SELECT r FROM Receipt r JOIN r.user c WHERE TYPE(c) = EmailUser AND LOWER(c.emailAddress) = LOWER(?1) AND r.transactionDate >= ?2 AND r.transactionDate <= ?3",
                email, startDateTime, endDateTime);
    }

    public long findCustomerTotalReceipts(String email) {
        return count("FROM Receipt r, EmailUser c WHERE r.user.userId = c.userId AND LOWER(c.emailAddress) = LOWER(?1)", email);
    }

    @Transactional
    public boolean markReceiptAsViewed(Long receiptId) {
        Receipt receipt = findById(receiptId);
        if (receipt != null) {
            receipt.setViewed(true); // Assuming your Receipt entity has a setViewed method
            return true;
        }
        return false;
    }
}



