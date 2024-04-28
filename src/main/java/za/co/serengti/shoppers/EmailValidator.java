package za.co.serengti.shoppers;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ApplicationScoped

public class EmailValidator {

    private EmailValidator() {
    }

    public static boolean isValid(String email) {

        String emailAddressPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(emailAddressPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

    }

}
