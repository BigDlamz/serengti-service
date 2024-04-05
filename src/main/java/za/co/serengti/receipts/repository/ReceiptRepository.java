package za.co.serengti.receipts.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import za.co.serengti.receipts.entity.Receipt;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
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

        return list("SELECT r FROM Receipt r JOIN r.shopper c WHERE TYPE(c) = EmailShopper AND LOWER(c.emailAddress) = LOWER(?1) AND r.transactionDate >= ?2 AND r.transactionDate <= ?3",
                email, startDateTime, endDateTime);
    }

    public long findTotalShopperReceiptsCount(String email) {
        return count("FROM Receipt r, EmailShopper c WHERE r.shopper.shopperId = c.shopperId AND LOWER(c.emailAddress) = LOWER(?1)", email);
    }

    public long findUnreadReceiptsByEmail(String email) {
        return count("FROM Receipt r " +
                        "JOIN r.shopper u " +
                        "WHERE LOWER(u.emailAddress) = LOWER(?1) " +
                        "AND r.viewed = false",
                email);

    }

    @Transactional
    public boolean updateMethodAsViewed(Long receiptId) {
        Receipt receipt = findById(receiptId);
        if (receipt != null) {
            receipt.setViewed(true);
            return true;
        }
        return false;
    }

    public BigDecimal findTotalAmountPaidByEmail(String email) {
        String query = "SELECT SUM(r.amountPaid) FROM Receipt r WHERE TYPE(r.shopper) = EmailShopper AND LOWER(r.shopper.emailAddress) = LOWER(?1)";
        Object result = find(query, email).firstResult();
            return (BigDecimal) result;
        }

}



