package za.co.serengti.receipts;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "tills")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Till {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "till_id")
    private Long tillId;

    @Column(name = "till_number")
    private Long tillNumber;


}