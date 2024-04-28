package za.co.serengti.payments;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import za.co.serengti.receipts.*;

import java.time.LocalDateTime;
import java.util.Objects;

@ApplicationScoped
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final ReceiptService receiptService;
    private final FinanceService financeService;
    private final PaymentMapper convertor;

    @Override
    @Transactional
    public void save(PaymentDTO paymentDTO) {

        var entity = convertor.toEntity(paymentDTO);
        paymentRepository.persist(entity);

    }

    @Override
    public PaymentDTO find(Long paymentId) {

        return convertor.toDTO(paymentRepository.findById(paymentId));

    }

    public PaymentServiceImpl(ReceiptService receiptService, PaymentRepository paymentRepository, FinanceService financeService, PaymentMapper convertor) {
        this.receiptService = receiptService;
        this.paymentRepository = paymentRepository;
        this.financeService = financeService;
        this.convertor = convertor;
    }

    @Override
    @Transactional
    public void processPayment(PaymentRequest request) {

        LocalDateTime today = LocalDateTime.now();

        if (!Objects.equals(request.getTxDate().toLocalDate(), today.toLocalDate())) {
            throw new IllegalArgumentException("Transaction date must not be in the past or future");
        }

        Long receiptId = receiptService.processReceipt(request);

        var sub = financeService.getSubTotal(request.getLineItems());
        var vatAmount = financeService.getVatAmount(sub);
        var totalDue = financeService.getTotalDue(sub, vatAmount);


        save(PaymentDTO.builder()
                .receiptId(receiptId)
                .paymentMethod(PaymentMethod.PAYSHAP.name())
                .amountPaid(totalDue)
                .paymentDate(request.getTxDate())
                .build());

    }

}
