package za.co.serengti.receipts.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import za.co.serengti.merchants.entity.POSSystem;
import za.co.serengti.merchants.entity.Store;

import javax.persistence.*;

@Entity
@Table(name = "Tills")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Till {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long till_id;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @ManyToOne
    @JoinColumn(name = "pos_system_id", nullable = false)
    private POSSystem posSystem;

    @Column(name = "till_number")
    private String tillNumber;

}