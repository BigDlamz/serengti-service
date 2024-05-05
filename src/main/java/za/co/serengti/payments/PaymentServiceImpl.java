package za.co.serengti.payments;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import za.co.serengti.receipts.*;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.time.LocalDateTime;
import java.util.Objects;

@ApplicationScoped
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final ReceiptService receiptService;
    private final FinanceService financeService;
    private final PaymentMapper convertor;


    public PaymentServiceImpl(ReceiptService receiptService, PaymentRepository paymentRepository, FinanceService financeService, PaymentMapper convertor) {
        this.receiptService = receiptService;
        this.paymentRepository = paymentRepository;
        this.financeService = financeService;
        this.convertor = convertor;
    }

    @Override
    @Transactional
    public void save(PaymentDTO payment) {

        Payment entity = convertor.toEntity(payment);
        paymentRepository.persist(entity);

    }

    @Override
    public PaymentDTO find(Long paymentId) {

        Payment payment = paymentRepository.findById(paymentId);
        return convertor.toDTO(payment);

    }

    @Override
    public MonetaryAmount getTotalPayments(Long userId) {

        var currency = Monetary.getCurrency("ZAR");

        return Monetary.getDefaultAmountFactory()
                .setCurrency(currency)
                .setNumber(paymentRepository.getTotalPayments(userId))
                .create();

    }

    @Override
    @Transactional
    public void savePayment(PaymentRequest request) {

        var receipt = saveReceipt(request);

        var subtotal = financeService.getSubTotal(request.getLineItems());
        var vatAmount = financeService.getVatAmount(subtotal);
        var totalDue = financeService.getTotalDue(subtotal, vatAmount);

        var payment = PaymentDTO.builder()
                .receiptId(receipt.getReceiptId())
                .shopperId(receipt.getShopper().getShopperId())
                .paymentMethod(PaymentMethod.PAYSHAP.name())
                .amountPaid(totalDue)
                .paymentDate(request.getTxDate())
                .build();

        save(payment);

    }

    private ReceiptDTO saveReceipt(PaymentRequest request) {

        LocalDateTime today = LocalDateTime.now();

        if (!Objects.equals(request.getTxDate().toLocalDate(), today.toLocalDate())) {
            throw new IllegalArgumentException("Transaction date must not be in the past or future");
        }

        return receiptService.processReceipt(request);
    }

}
