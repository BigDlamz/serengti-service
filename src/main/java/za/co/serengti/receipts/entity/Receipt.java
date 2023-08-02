package za.co.serengti.receipts.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.*;
import za.co.serengti.customers.entity.Customer;
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
    public Long receiptID;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "store_id")
    public Store store;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "pos_system_id")
    public POSSystem posSystem;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_id")
    public Customer customer;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "tillID")
    public Till till;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "cachierID")
    public Cashier cashier;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "promotional_message_id")
    public Promotions promotions;

    @Column(name = "timestamp")
    public LocalDateTime timestamp;

    @Column(name = "amount_before_tax")
    public BigDecimal amountBeforeTax;

    @Column(name = "amount_after_tax")
    public BigDecimal amountAfterTax;

    @OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<LineItem> purchasedItems;

}
