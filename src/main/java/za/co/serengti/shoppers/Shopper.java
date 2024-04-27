package za.co.serengti.shoppers;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.*;

import jakarta.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "shoppers")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Shopper extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shopper_id")
    protected Long shopperId;

    @Column(name = "name")
    protected String name;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "mobile_number")
    private String mobileNumber;

}

