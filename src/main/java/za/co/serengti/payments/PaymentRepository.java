package za.co.serengti.payments;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigDecimal;

@ApplicationScoped
public class PaymentRepository implements PanacheRepository<Payment> {

    public BigDecimal getTotalPayments(Long userId) {
        Object result = find("SELECT SUM(amountPaid) FROM Payment WHERE shopperId = :userId",
                Parameters.with("userId", userId))
                .firstResult();
        if (result == null) {
            return BigDecimal.ZERO;
        } else {
            return (BigDecimal) result;
        }
    }

}
