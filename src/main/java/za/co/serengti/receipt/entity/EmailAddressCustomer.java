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
@DiscriminatorValue("email_address")
public class EmailAddressCustomer extends Customer {
    @Column(name = "email_address")
    public String emailAddress;}
