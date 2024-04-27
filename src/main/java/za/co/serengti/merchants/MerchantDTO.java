package za.co.serengti.merchants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MerchantDTO {

    private Long merchantId;
    private String name;
    private String address;
    private String vatRegistrationNo;
    private String merchantLogo;

}
