package za.co.serengti.receipt.entity;

import javax.persistence.*;

@Entity
@Table(name = "pos_systems")
public class POSSystem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "vendor")
    private String vendor;

}
