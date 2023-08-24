package za.co.serengti.merchants.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import za.co.serengti.merchants.entity.Feedback;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class FeedbackRepository implements PanacheRepository<Feedback> {

    @Transactional
    public void storeFeedback(Feedback feedback) {
        persist(feedback);
    }

    public List<Feedback> getFeedbackForStore(Long storeId) {
        return list("SELECT f FROM Feedback f JOIN Receipt r ON f.receiptId = r.receiptId WHERE r.storeId = ?1", storeId);
    }

    public Double getAverageRatingForStore(Long storeId) {
        Object result = find("SELECT AVG(f.starRating) FROM Feedback f JOIN Receipt r ON f.receiptId = r.receiptId WHERE r.storeId = ?1", storeId)
                .firstResult();
        if (result != null) {
            return ((Number) result).doubleValue();
        }
        return null;
    }
}
