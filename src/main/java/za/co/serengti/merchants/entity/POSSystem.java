package za.co.serengti.merchants.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "pos_systems")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class POSSystem extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pos_system_id")
    public Long posSystemID;

    @Column(name = "name")
    public String name;

    @Column(name = "version")
    public String version;

}
