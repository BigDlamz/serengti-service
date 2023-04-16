package za.co.serengti.receipt.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("mobile_number")
public class MobileNumberCustomer extends Customer {
    @Column(name = "mobile_number")
    public String mobileNumber;
}