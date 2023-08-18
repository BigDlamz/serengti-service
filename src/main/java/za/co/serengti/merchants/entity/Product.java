package za.co.serengti.merchants.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import za.co.serengti.util.ProductCategory;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Product extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    public Long productID;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    public String description;

    @Convert(converter = ProductCategoryConverter.class)
    @Column(name = "category")
    public ProductCategory category;

    @Column(name = "unit_price")
    public BigDecimal unitPrice;

}
