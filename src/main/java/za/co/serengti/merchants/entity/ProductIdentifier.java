package za.co.serengti.merchants.entity;

import javax.persistence.*;

public class ProductIdentifier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
