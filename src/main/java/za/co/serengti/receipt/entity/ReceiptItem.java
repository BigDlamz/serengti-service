package za.co.serengti.receipt.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "receipt_items")
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptItem extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "receipt_id")
    public Receipt receipt;

    @ManyToOne
    @JoinColumn(name = "product_id")
    public Product product;

    @Column(name = "quantity")
    public Integer quantity;

}
