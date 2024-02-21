package za.co.serengti.shoppers.mapper;

import za.co.serengti.shoppers.dto.UserDTO;
import za.co.serengti.shoppers.entity.Shopper;
import za.co.serengti.shoppers.entity.EmailShopper;
import za.co.serengti.shoppers.entity.MobileShopper;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ShopperMapper {

    public Shopper toEntity(UserDTO dto) {
        if (dto.getEmailAddress() != null) {
            return new EmailShopper(dto.getName(), "email_address", dto.getEmailAddress());
        } else if (dto.getMobileNumber() != null) {
            return new MobileShopper(dto.getName(), "mobile_number", dto.getMobileNumber());
        } else {
            throw new IllegalArgumentException("Invalid CustomerDTO: neither emailAddress nor mobileNumber is set.");
        }
    }

    public UserDTO toDto(Shopper entity) {
        UserDTO dto = new UserDTO();
        dto.setUserId(entity.getShopperId());
        dto.setName(entity.getName());

        if (entity instanceof EmailShopper) {
            dto.setEmailAddress(((EmailShopper) entity).getEmailAddress());
        } else if (entity instanceof MobileShopper) {
            dto.setMobileNumber(((MobileShopper) entity).getMobileNumber());
        }
        return dto;
    }
}
