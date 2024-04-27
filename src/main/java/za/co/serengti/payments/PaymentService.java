package za.co.serengti.payments;

public interface PaymentService {

    void processPayment(PaymentRequest request);

    void save(PaymentDTO paymentDTO);

    PaymentDTO find(Long paymentId);

}
