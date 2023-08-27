package za.co.serengti.merchants.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreDTO {
    private Long storeId;
    private String name;
    private String address;
    private String vatRegistrationNumber;
    private String storeLogoURL;

}
