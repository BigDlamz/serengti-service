package za.co.serengti.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import za.co.serengti.customers.dto.CustomerDTO;
import za.co.serengti.customers.entity.EmailCustomer;
import za.co.serengti.customers.entity.MobileCustomer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

@ApplicationScoped
public class MappingConfig {

    @Produces
    @Singleton
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.createTypeMap(EmailCustomer.class, CustomerDTO.class)
                .addMapping(EmailCustomer::getId, CustomerDTO::setId)
                .addMapping(EmailCustomer::getName, CustomerDTO::setName)
                .addMapping(EmailCustomer::getEmailAddress, CustomerDTO::setEmailAddress);

        modelMapper.createTypeMap(MobileCustomer.class, CustomerDTO.class)
                .addMapping(MobileCustomer::getId, CustomerDTO::setId)
                .addMapping(MobileCustomer::getName, CustomerDTO::setName)
                .addMapping(MobileCustomer::getMobileNumber, CustomerDTO::setMobileNumber);

        return modelMapper;
    }
}
