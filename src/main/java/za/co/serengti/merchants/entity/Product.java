package za.co.serengti.merchants.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import za.co.serengti.merchants.dto.POSSystemDTO;
import za.co.serengti.merchants.dto.StoreDTO;
import za.co.serengti.receipts.dto.PurchasedItem;
import za.co.serengti.util.RecordMapper;

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
    public Long id;

    @ManyToOne
    @JoinColumn(name = "pos_system_id", nullable = false)
    private POSSystem posSystem;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(name = "description")
    public String description;

    @Column(name = "sku")
    public String sku;

    @Column(name = "price")
    public BigDecimal price;

    @Transient
    public Integer quantity;

    public static Product of(RecordMapper mapper, POSSystemDTO posSystemDTO, StoreDTO storeDTO, PurchasedItem purchasedItem) {
        return Product.builder()
                .posSystem(mapper.convert(posSystemDTO, POSSystem.class))
                .store(mapper.convert(storeDTO, Store.class))
                .sku(purchasedItem.getSku())
                .description(purchasedItem.getDescription())
                .price(purchasedItem.getPrice())
                .build();
    }
}
