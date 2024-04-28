package za.co.serengti.merchants;

import lombok.*;

import jakarta.persistence.*;
import java.util.List;

@Entity

@Table(name = "merchants")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Merchant  {

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
