package za.co.serengti.merchants.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "product_identifiers")
public class ProductIdentifier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_identifier_id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @ManyToOne
    @JoinColumn(name = "pos_system_id", nullable = false)
    private POSSystem posSystem;

    @Column(name = "ean13_code")
    private String ean13Code;

    @Column(name = "universal_product_code")
    private String universalProductCode;

    @Column(name = "sku")
    private String sku;

}
