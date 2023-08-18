package za.co.serengti.users.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorColumn(name = "identifier_type", discriminatorType = DiscriminatorType.STRING)
public class User extends PanacheEntityBase {

    public User(String name, String identifierType) {
        this.name = name;
        this.identifierType = identifierType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    protected Long userId;

    @Column(name = "name")
    protected String name;

    @Column(name = "identifier_type", insertable = false, updatable = false)
    protected String identifierType;

}

