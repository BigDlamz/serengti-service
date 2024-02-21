package za.co.serengti.shoppers.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "shoppers")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorColumn(name = "identifier_type", discriminatorType = DiscriminatorType.STRING)
public class Shopper extends PanacheEntityBase {

    public Shopper(String name, String identifierType) {
        this.name = name;
        this.identifierType = identifierType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shopper_id")
    protected Long shopperId;

    @Column(name = "name")
    protected String name;

    @Column(name = "identifier_type", insertable = false, updatable = false)
    protected String identifierType;

}

