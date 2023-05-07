package za.co.serengti.customers.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    @JsonIgnore
    private Long id;
    private String name;
    private String emailAddress;
    private String mobileNumber;
}
