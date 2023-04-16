package za.co.serengti.receipt.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("mobile_number")
public class MobileNumberCustomer extends Customer {
    public MobileNumberCustomer() {
        super();
    }

    public MobileNumberCustomer(Long id, String name, String identifierType, String mobileNumber) {
        super(id, name, identifierType);
        this.mobileNumber = mobileNumber;
    }

    @Column(name = "mobile_number")
    public String mobileNumber;
}