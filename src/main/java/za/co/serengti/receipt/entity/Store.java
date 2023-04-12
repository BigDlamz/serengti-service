package za.co.serengti.receipt.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "stores")
@AllArgsConstructor
public class Store extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "name")
    public String name;

    @Column(name = "address")
    public String address;

    @Column(name = "vat_registration_number")
    public String vatRegistrationNumber;

    public Store() {

    }
}
