package za.co.serengti.receipt.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("email_address")
public class EmailAddressCustomer extends Customer {

    public EmailAddressCustomer() {
        super();
    }

    public EmailAddressCustomer(Long id, String name, String identifierType, String emailAddress) {
        super(id, name, identifierType);
        this.emailAddress = emailAddress;
    }

    @Column(name = "email_address")
    public String emailAddress;
}
