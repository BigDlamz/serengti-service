package za.co.serengti.receipt.util;

import javax.enterprise.context.ApplicationScoped;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@ApplicationScoped
public class Identification {
    public enum Type {
        MOBILE,
        EMAIL,
        INVALID_IDENTIFIER
    }

    public Type determineIdentifierType(String identifier) {
        if (isMobileNumber(identifier)) {
            return Type.MOBILE;
        } else if (isEmailAddress(identifier)) {
            return Type.EMAIL;
        } else {
            return Type.INVALID_IDENTIFIER;
        }
    }

    private static boolean isMobileNumber(String identifier) {
        // South African mobile number format: +27 followed by 9 digits
        String mobileNumberPattern = "^\\+27\\d{9}$";
        Pattern pattern = Pattern.compile(mobileNumberPattern);
        Matcher matcher = pattern.matcher(identifier);
        return matcher.matches();
    }

    private static boolean isEmailAddress(String identifier) {
        String emailAddressPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(emailAddressPattern);
        Matcher matcher = pattern.matcher(identifier);
        return matcher.matches();
    }
}
