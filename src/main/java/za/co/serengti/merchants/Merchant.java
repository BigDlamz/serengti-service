package za.co.serengti.merchants;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "merchants")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Merchant extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "merchant_id")
    public Long merchantId;

    @Column(name = "vat_registration_no")
    public String vatRegistrationNo;

    @Column(name = "name")
    public String name;

    @Column(name = "address")
    public String address;

    @Column(name = "store_logo_url")
    public String storeLogoURL;

    @OneToMany(mappedBy = "merchant")
    public List<Special> specials;

}
