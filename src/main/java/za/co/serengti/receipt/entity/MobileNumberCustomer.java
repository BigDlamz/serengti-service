package za.co.serengti.receipt.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@DiscriminatorValue("mobile_number")
public class MobileNumberCustomer extends Customer {
    public MobileNumberCustomer() {
        super();
    }

    public MobileNumberCustomer(String name, String identifierType, String mobileNumber) {
        super(name, identifierType);
        this.mobileNumber = mobileNumber;
    }

    @Column(name = "mobile_number")
    public String mobileNumber;
}