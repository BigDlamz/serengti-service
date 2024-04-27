package za.co.serengti.shoppers;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@ApplicationScoped
public class Validator {

    private static boolean isMobileNumber(String mobileNo) {

        /**
         *
         * South African mobile number format: +27 followed by 9 digits
         *
         * */
        String mobileNumberPattern = "^\\+27\\d{9}$";
        Pattern pattern = Pattern.compile(mobileNumberPattern);
        Matcher matcher = pattern.matcher(mobileNo);
        return matcher.matches();

    }

    private static boolean isEmailAddress(String email) {

        String emailAddressPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(emailAddressPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

    }
}
