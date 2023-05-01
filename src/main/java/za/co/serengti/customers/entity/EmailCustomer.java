package za.co.serengti.customers.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@DiscriminatorValue("email_address")
public class EmailCustomer extends Customer {

    public EmailCustomer() {
        super();
    }

    public EmailCustomer(String name, String identifierType, String emailAddress) {
        super(name, identifierType);
        this.emailAddress = emailAddress;
    }

    @Column(name = "email_address")
    public String emailAddress;
}
