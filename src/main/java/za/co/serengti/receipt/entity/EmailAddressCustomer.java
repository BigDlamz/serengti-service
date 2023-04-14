package za.co.serengti.receipt.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("email_address")
public class EmailAddressCustomer extends Customer {
    @Column(name = "email_address")
    public String emailAddress;}
