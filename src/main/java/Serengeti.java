import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import jakarta.ws.rs.core.Application;

@OpenAPIDefinition(info = @Info(title = "Serengti-Service", version = "1.0", description = "The API for managing receipts, products, and specials"))
public class Serengeti extends Application {
}
