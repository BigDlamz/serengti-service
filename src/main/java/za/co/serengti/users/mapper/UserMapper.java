package za.co.serengti.users.mapper;

import za.co.serengti.users.dto.UserDTO;
import za.co.serengti.users.entity.User;
import za.co.serengti.users.entity.EmailUser;
import za.co.serengti.users.entity.MobileUser;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserMapper {

    public User toEntity(UserDTO dto) {
        if (dto.getEmailAddress() != null) {
            return new EmailUser(dto.getName(), "email_address", dto.getEmailAddress());
        } else if (dto.getMobileNumber() != null) {
            return new MobileUser(dto.getName(), "mobile_number", dto.getMobileNumber());
        } else {
            throw new IllegalArgumentException("Invalid CustomerDTO: neither emailAddress nor mobileNumber is set.");
        }
    }

    public UserDTO toDto(User entity) {
        UserDTO dto = new UserDTO();
        dto.setUserId(entity.getUserId());
        dto.setName(entity.getName());

        if (entity instanceof EmailUser) {
            dto.setEmailAddress(((EmailUser) entity).getEmailAddress());
        } else if (entity instanceof MobileUser) {
            dto.setMobileNumber(((MobileUser) entity).getMobileNumber());
        }
        return dto;
    }
}
