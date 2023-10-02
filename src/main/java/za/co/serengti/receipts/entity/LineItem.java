package za.co.serengti.receipts.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import za.co.serengti.merchants.entity.Product;

import jakarta.persistence.*;

@Entity
@Table(name = "line_items")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LineItem extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "line_item_id")
    public Long lineItemID;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonBackReference
    @JoinColumn(name = "receipt_id")
    public Receipt receipt;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_id")
    public Product product;

    public Integer quantity;
}
