package za.co.serengti.shoppers.entity;

import lombok.*;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("email_address")
public class EmailShopper extends Shopper {


    public EmailShopper(String name, String identifierType, String emailAddress) {
        super(name, identifierType);
        this.setEmailAddress(emailAddress);
    }

    @Column(name = "email_address")
    private String emailAddress;


}
