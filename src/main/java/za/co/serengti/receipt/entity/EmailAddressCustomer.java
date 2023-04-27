package za.co.serengti.receipt.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@DiscriminatorValue("email_address")
public class EmailAddressCustomer extends Customer {

    public EmailAddressCustomer() {
        super();
    }

    public EmailAddressCustomer(String name, String identifierType, String emailAddress) {
        super(name, identifierType);
        this.emailAddress = emailAddress;
    }

    @Column(name = "email_address")
    public String emailAddress;
}
