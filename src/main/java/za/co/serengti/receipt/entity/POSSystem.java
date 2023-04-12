package za.co.serengti.receipt.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "pos_systems")
@AllArgsConstructor
public class POSSystem extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "name")
    public String name;

    @Column(name = "vendor")
    public String vendor;

    public POSSystem() {

    }
}
