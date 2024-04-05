package za.co.serengti.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseMessageDTO {
    private String message;
}
