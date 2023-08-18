package za.co.serengti.users.entity;

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
public class EmailUser extends User {


    public EmailUser(String name, String identifierType, String emailAddress) {
        super(name, identifierType);
        this.setEmailAddress(emailAddress);
    }

    @Column(name = "email_address")
    private String emailAddress;


}
