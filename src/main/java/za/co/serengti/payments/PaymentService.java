package za.co.serengti.payments;

import javax.money.MonetaryAmount;

public interface PaymentService {

    MonetaryAmount getTotalPayments(Long userId);

    void savePayment(PaymentRequest request);

    void save(PaymentDTO paymentDTO);

    PaymentDTO find(Long paymentId);


}
