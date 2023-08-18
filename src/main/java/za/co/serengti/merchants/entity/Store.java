package za.co.serengti.merchants.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "stores")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Store extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    public Long storeID;

    @Column(name = "vat_registration_number")
    public String vatRegistrationNumber;

    @Column(name = "name")
    public String name;

    @Column(name = "address")
    public String address;

    @Column(name = "store_logo_url")
    public String storeLogoURL;

    @OneToMany(mappedBy = "store")
    public List<Special> specials;

}
