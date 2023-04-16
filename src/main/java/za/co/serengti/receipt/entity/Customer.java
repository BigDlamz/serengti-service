package za.co.serengti.receipt.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
@Table(name = "customers")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "identifier_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Customer extends PanacheEntityBase {
    public Customer() {}

    public Customer(Long id, String name, String identifierType) {
        this.id = id;
        this.name = name;
        this.identifierType = identifierType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "name")
    protected String name;

    @Column(name = "identifier_type", insertable = false, updatable = false)
    protected String identifierType;

}

