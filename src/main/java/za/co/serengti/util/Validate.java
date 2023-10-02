package za.co.serengti.util;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.BadRequestException;
import java.util.Objects;

@ApplicationScoped
public class Validate {

    public void notNull(Object obj, String name) {
        if(Objects.isNull(obj)) {
            throw new BadRequestException(name + " cannot be null");
        }
    }
}
