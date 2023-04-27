package za.co.serengti.receipt.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import za.co.serengti.receipt.dto.CustomerDTO;
import za.co.serengti.receipt.entity.EmailAddressCustomer;
import za.co.serengti.receipt.entity.MobileNumberCustomer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

@ApplicationScoped
public class ModelMapperConfig {

    @Produces
    @Singleton
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.createTypeMap(EmailAddressCustomer.class, CustomerDTO.class)
                .addMapping(EmailAddressCustomer::getId, CustomerDTO::setId)
                .addMapping(EmailAddressCustomer::getName, CustomerDTO::setName)
                .addMapping(EmailAddressCustomer::getEmailAddress, CustomerDTO::setEmailAddress);

        modelMapper.createTypeMap(MobileNumberCustomer.class, CustomerDTO.class)
                .addMapping(MobileNumberCustomer::getId, CustomerDTO::setId)
                .addMapping(MobileNumberCustomer::getName, CustomerDTO::setName)
                .addMapping(MobileNumberCustomer::getMobileNumber, CustomerDTO::setMobileNumber);

        return modelMapper;
    }
}
