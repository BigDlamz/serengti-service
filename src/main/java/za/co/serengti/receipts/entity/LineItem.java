package za.co.serengti.receipts.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import za.co.serengti.merchants.entity.Product;

import javax.persistence.*;

@Entity
@Table(name = "line_items")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LineItem extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receipt_id")
    public Receipt receipt;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_id")
    public Product product;


}
