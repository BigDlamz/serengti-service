import jakarta.ws.rs.ApplicationPath;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import jakarta.ws.rs.core.Application;
import za.co.serengti.merchants.MerchantResource;
import za.co.serengti.payments.PaymentsResource;
import za.co.serengti.receipts.ReceiptResource;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
@OpenAPIDefinition(info = @Info(title = "Serengti-Service", version = "1.0", description = "The API for managing receipts, products, and specials"))
public class Serengeti extends Application {

    @Override
    public Set<Class<?>> getClasses() {

        Set<Class<?>> classes = new HashSet<>();
        classes.add(PaymentsResource.class);
        classes.add(ReceiptResource.class);
        classes.add(MerchantResource.class);
        return classes;
    }
}
