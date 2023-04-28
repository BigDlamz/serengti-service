package za.co.serengti.customers.entity;

import lombok.Data;
import za.co.serengti.customers.entity.CustomerEntity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@DiscriminatorValue("mobile_number")
public class MobileCustomerEntity extends CustomerEntity {
    public MobileCustomerEntity() {
        super();
    }

    public MobileCustomerEntity(String name, String identifierType, String mobileNumber) {
        super(name, identifierType);
        this.mobileNumber = mobileNumber;
    }

    @Column(name = "mobile_number")
    public String mobileNumber;
}