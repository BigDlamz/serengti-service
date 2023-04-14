package za.co.serengti.receipt.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("mobile_number")
public class MobileNumberCustomer extends Customer {
    @Column(name = "mobile_number")
    public String mobileNumber;
}