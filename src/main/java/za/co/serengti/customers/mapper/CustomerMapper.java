package za.co.serengti.customers.mapper;

import za.co.serengti.customers.dto.CustomerDTO;
import za.co.serengti.customers.entity.Customer;
import za.co.serengti.customers.entity.EmailCustomer;
import za.co.serengti.customers.entity.MobileCustomer;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerMapper {

    public Customer toEntity(CustomerDTO dto) {
        if (dto.getEmailAddress() != null) {
            return new EmailCustomer(dto.getName(), "email_address", dto.getEmailAddress());
        } else if (dto.getMobileNumber() != null) {
            return new MobileCustomer(dto.getName(), "mobile_number", dto.getMobileNumber());
        } else {
            throw new IllegalArgumentException("Invalid CustomerDTO: neither emailAddress nor mobileNumber is set.");
        }
    }

    public CustomerDTO toDto(Customer entity) {
        CustomerDTO dto = new CustomerDTO();
        dto.setCustomerID(entity.getCustomerID());
        dto.setName(entity.getName());

        if (entity instanceof EmailCustomer) {
            dto.setEmailAddress(((EmailCustomer) entity).getEmailAddress());
        } else if (entity instanceof MobileCustomer) {
            dto.setMobileNumber(((MobileCustomer) entity).getMobileNumber());
        }
        return dto;
    }
}
