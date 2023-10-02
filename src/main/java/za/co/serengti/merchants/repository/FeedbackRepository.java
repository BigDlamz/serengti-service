package za.co.serengti.merchants.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import za.co.serengti.merchants.entity.Feedback;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class FeedbackRepository implements PanacheRepository<Feedback> {

    @Transactional
    public void saveFeedback(Feedback feedback) {
        persist(feedback);
    }

    public List<Feedback> findFeedbackForStore(Long storeId) {
        return list("SELECT f FROM Feedback f JOIN Receipt r ON f.receiptId = r.receiptId WHERE r.store.storeId = ?1", storeId);
    }

    public Double findAverageRatingForStore(Long storeId) {
        Object result = find("SELECT AVG(f.starRating) FROM Feedback f JOIN Receipt r ON f.receiptId = r.receiptId WHERE r.store.storeId = ?1", storeId)
                .firstResult();
        if (result != null) {
            return ((Number) result).doubleValue();
        }
        return null;
    }
    public Feedback findFeedbackForReceipt(Long receiptId) {
        return find("receiptId", receiptId).firstResult();
    }

    public List<Feedback> findTopFeedbacksForStore(Long storeId) {
        Query query = getEntityManager().createQuery("SELECT f FROM Feedback f JOIN Receipt r ON f.receiptId = r.receiptId WHERE r.store.storeId = :storeId ORDER BY f.starRating DESC, f.feedbackDate DESC");
        query.setParameter("storeId", storeId);
        query.setMaxResults(5);
        return query.getResultList();
    }

}
