package za.co.serengti.receipts;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
@Slf4j
public class ReceiptRepository implements PanacheRepository<Receipt> {

    public Receipt save(Receipt receipt) {
        persistAndFlush(receipt);
        return receipt;
    }

    public List<Receipt> findAllByEmailAndDate(String email, LocalDate date) {
        LocalDateTime startDateTime = date.atStartOfDay();
        LocalDateTime endDateTime = date.atTime(23, 59, 59);

        return list("SELECT r FROM Receipt r JOIN r.shopper c WHERE TYPE(c) = EmailShopper AND LOWER(c.emailAddress) = LOWER(?1) AND r.transactionDate >= ?2 AND r.transactionDate <= ?3",
                email, startDateTime, endDateTime);
    }

    public long findTotalCount(String email) {
        return count("FROM Receipt r, EmailShopper c WHERE r.shopper.shopperId = c.shopperId AND LOWER(c.emailAddress) = LOWER(?1)", email);
    }

    public long findUnread(String email) {
        return count("FROM Receipt r " +
                        "JOIN r.shopper u " +
                        "WHERE LOWER(u.emailAddress) = LOWER(?1) " +
                        "AND r.viewed = false",
                email);

    }

    @Transactional
    public boolean updateStatus(Long receiptId, Status status) throws ReceiptNotFoundException {

        boolean updated = false;

        Receipt receipt = findById(receiptId);

        if (receipt == null) {
            throw new ReceiptNotFoundException("Receipt with ID {} not found : " + receiptId);
        }

        if (status.equals(Status.READ)) {
            receipt.setViewed(true);
            save(receipt);
            updated = true;
        }

        return updated;
    }

    public BigDecimal findTotalPaid(String email) {
        String query = "SELECT SUM(r.amountPaid) FROM Receipt r WHERE TYPE(r.shopper) = EmailShopper AND LOWER(r.shopper.emailAddress) = LOWER(?1)";
        Object result = find(query, email).firstResult();
            return (BigDecimal) result;
        }

}



