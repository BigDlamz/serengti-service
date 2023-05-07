package za.co.serengti.customers.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("email_address")
public class EmailCustomer extends Customer {


    public EmailCustomer(String name, String identifierType, String emailAddress) {
        super(name, identifierType);
        this.setEmailAddress(emailAddress);
    }

    @Column(name = "email_address")
    private String emailAddress;


}
