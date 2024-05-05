package za.co.serengti.payments;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PaymentMapper {

    public Payment toEntity(PaymentDTO dto) {

        return Payment.builder()
                .paymentId(dto.getPaymentId())
                .shopperId(dto.getShopperId())
                .receiptId(dto.getReceiptId())
                .paymentMethod(dto.getPaymentMethod())
                .amountPaid(dto.getAmountPaid())
                .paymentDate(dto.getPaymentDate())
                .build();
    }

    public PaymentDTO toDTO(Payment entity) {

        return PaymentDTO.builder()
                .paymentId(entity.getPaymentId())
                .shopperId(entity.getShopperId())
                .receiptId(entity.getReceiptId())
                .paymentMethod(entity.getPaymentMethod())
                .amountPaid(entity.getAmountPaid())
                .paymentDate(entity.getPaymentDate())
                .build();
    }
}
