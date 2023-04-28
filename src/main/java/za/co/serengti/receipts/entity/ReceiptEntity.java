package za.co.serengti.receipts.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.serengti.customers.entity.CustomerEntity;

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
public class ReceiptEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "store_id")
    public StoreEntity store;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "pos_system_id")
    public POSSystemEntity posSystem;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_id")
    public CustomerEntity customer;

    @Column(name = "timestamp")
    public LocalDateTime timestamp;

    @Column(name = "total_amount_paid")
    public BigDecimal totalAmountPaid;

    @OneToMany(mappedBy = "receipt", cascade = CascadeType.MERGE)
    public List<ReceiptItemEntity> receiptItems;

}
