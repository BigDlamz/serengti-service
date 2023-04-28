package za.co.serengti.receipt.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "name")
    public String name;

    @Column(name = "sku")
    public String sku;

    @Column(name = "price")
    public BigDecimal price;

    @Column(name = "quantity")
    public Integer quantity;

    public BigDecimal getTotalPrice(int quantity) {
        return this.price.multiply(BigDecimal.valueOf(quantity));
    }

}
