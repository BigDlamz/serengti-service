package za.co.serengti.receipt.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "receipts")
@AllArgsConstructor
@NoArgsConstructor
public class Receipt extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "receipt_id")
    public String receiptId;

    @ManyToOne
    @JoinColumn(name = "store_id")
    public Store store;

    @ManyToOne
    @JoinColumn(name = "pos_system_id")
    public POSSystem posSystem;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    public Customer customer;

    @Column(name = "timestamp")
    public LocalDateTime timestamp;

    @Column(name = "total_amount_paid")
    public BigDecimal totalAmountPaid;

    @OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL)
    public List<ReceiptItem> receiptItems;

}
