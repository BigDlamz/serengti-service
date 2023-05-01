package za.co.serengti.receipts.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.serengti.customers.entity.Customer;
import za.co.serengti.merchants.entity.POSSystem;
import za.co.serengti.merchants.entity.Store;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "receipts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Receipt extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "store_id")
    public Store store;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "pos_system_id")
    public POSSystem posSystem;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_id")
    public Customer customer;

    @Column(name = "timestamp")
    public LocalDateTime timestamp;

    @Column(name = "total_amount_paid")
    public BigDecimal totalAmountPaid;

    @OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<LineItem> receiptItems;

}
