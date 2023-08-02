package za.co.serengti.receipts.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import za.co.serengti.merchants.entity.POSSystem;
import za.co.serengti.merchants.entity.Store;

import javax.persistence.*;

@Entity
@Table(name = "tills")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Till {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "till_id")
    private Long tillId;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @ManyToOne
    @JoinColumn(name = "pos_system_id", nullable = false)
    private POSSystem posSystem;

    @Column(name = "till_number")
    private String tillNumber;

}