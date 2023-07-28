package za.co.serengti.receipts.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Cashiers")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cashier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cashier_id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

}