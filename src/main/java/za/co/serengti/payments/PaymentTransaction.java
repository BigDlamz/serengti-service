package za.co.serengti.payments;

import jakarta.persistence.*;
import za.co.serengti.receipts.Receipt;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_transaction_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "receipt_id", nullable = false)
    private Receipt receipt;

    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;

    @Column(name = "payment_amount", nullable = false)
    private BigDecimal paymentAmount;

    @Column(name = "payment_date", nullable = false)
    private LocalDateTime paymentDate;
}