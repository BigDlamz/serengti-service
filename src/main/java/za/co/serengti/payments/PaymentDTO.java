package za.co.serengti.payments;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class PaymentDTO {

    private Long paymentId;
    private Long receiptId;
    private String paymentMethod;
    private BigDecimal amountPaid;
    private LocalDateTime paymentDate;
}
