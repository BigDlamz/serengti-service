package za.co.serengti.merchants;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class FeedbackRepository implements PanacheRepository<Feedback> {

    @Transactional
    public void save(Feedback feedback) {
        persist(feedback);
    }

    public List<Feedback> findAll(Long merchantId) {

        return list("""
                SELECT f FROM Feedback f \
                JOIN Receipt r ON f.receiptId = r.receiptId \
                WHERE r.merchant.merchantId = ?1""", merchantId);

    }

    public Double findAverageRating(Long merchantId) {

        Object result = find("""
                SELECT AVG(f.rating) FROM Feedback f\s
                JOIN Receipt r ON f.receiptId = r.receiptId
                WHERE r.merchant.merchantId = ?1""", merchantId)
                .firstResult();

        if (result != null) {
            return ((Number) result).doubleValue();
        }
        return null;
    }
    public Feedback isRated(Long receiptId) {

        return find("receiptId", receiptId)
                .firstResult();
    }

    public List<Feedback> findLatestFeedback(Long merchantId) {

        Query query = getEntityManager().createQuery("""
                SELECT f FROM Feedback f \
                JOIN Receipt r ON f.receiptId = r.receiptId \
                WHERE r.merchant.merchantId = :merchantId \
                ORDER BY f.rating DESC, f.feedbackDate DESC""");

        query.setParameter("merchantId", merchantId);
        query.setMaxResults(5);

        return query.getResultList();
    }

}
