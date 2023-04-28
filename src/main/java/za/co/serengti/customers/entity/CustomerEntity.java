package za.co.serengti.customers.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "customers")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@AllArgsConstructor
@DiscriminatorColumn(name = "identifier_type", discriminatorType = DiscriminatorType.STRING)
public  class CustomerEntity extends PanacheEntityBase {
    public CustomerEntity() {}

    public CustomerEntity(String name, String identifierType) {
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
