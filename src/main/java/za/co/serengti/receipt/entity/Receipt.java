package za.co.serengti.receipt.entity;

import za.co.serengti.receipt.MonetaryAmountConverter;

import javax.money.MonetaryAmount;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "receipts")
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "receipt_id")
    private String receiptId;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne
    @JoinColumn(name = "pos_system_id")
    private POSSystem posSystem;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "total_amount_paid")
    @Convert(converter = MonetaryAmountConverter.class)
    private MonetaryAmount totalAmountPaid;

    @OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL)
    private List<ReceiptItem> receiptItems;

}
