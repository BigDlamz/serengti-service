package za.co.serengti.shoppers.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @JsonIgnore
    private Long userId;
    private String name;
    @JsonInclude(JsonInclude.Include. NON_NULL)
    private String emailAddress;
    @JsonInclude(JsonInclude.Include. NON_NULL)
    private String mobileNumber;
}
