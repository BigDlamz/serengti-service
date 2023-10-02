package za.co.serengti.users.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("mobile_number")
public class MobileUser extends User {

    public MobileUser(String name, String identifierType, String mobileNumber) {
        super(name, identifierType);
        this.setMobileNumber(mobileNumber);
    }

    @Column(name = "mobile_number")
    private String mobileNumber;

}