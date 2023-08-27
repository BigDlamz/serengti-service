package za.co.serengti.receipts.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.*;
import za.co.serengti.users.entity.User;
import za.co.serengti.merchants.entity.POSSystem;
import za.co.serengti.merchants.entity.Store;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "receipts")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Receipt extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "receipt_id")
    public Long receiptId;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "store_id")
    public Store store;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "pos_system_id")
    public POSSystem posSystem;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    public User user;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "till_Id")
    public Till till;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "cashier_Id")
    public Cashier cashier;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "promotion_id")
    public Promotions promotions;

    @Column(name = "transaction_date")
    public LocalDateTime transactionDate;

    @Column(name = "subtotal")
    public BigDecimal subTotal;

    @Column(name = "vat_rate")
    public BigDecimal vatRate;

    @Column(name = "vat_amount")
    public BigDecimal vatAmount;

    @Column(name = "discount_amount")
    public BigDecimal discountAmount;

    @Column(name = "total_due_after_tax")
    public BigDecimal totalDueAfterTax;

    @Column(name = "amount_paid")
    public BigDecimal amountPaid;

    @Column(name = "change_due")
    public BigDecimal changeDue;

    @Column(name = "viewed", nullable = false)
    public Boolean viewed;

    @OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    public List<LineItem> lineItems;

}
