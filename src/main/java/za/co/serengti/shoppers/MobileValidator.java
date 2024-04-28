package za.co.serengti.shoppers;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

@ApplicationScoped
public class MobileValidator {

    private MobileValidator() {
    }

    /**
     * South African mobile number format: +27 followed by 9 digits
     */

    public boolean isValid(String mobileNo) {

        String mobileNumberPattern = "^\\+27\\d{9}$";
        Pattern pattern = Pattern.compile(mobileNumberPattern);
        Matcher matcher = pattern.matcher(mobileNo);
        return matcher.matches();

    }

}
