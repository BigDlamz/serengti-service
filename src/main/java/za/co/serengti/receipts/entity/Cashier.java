package za.co.serengti.receipts.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cashiers")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Cashier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cashier_id")
    private Long cashierId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

}