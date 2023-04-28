package za.co.serengti.receipts.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "receipt_items")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReceiptItemEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "receipt_id")
    public ReceiptEntity receipt;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_id")
    public ProductEntity product;

    @Column(name = "quantity")
    public Integer quantity;

}
