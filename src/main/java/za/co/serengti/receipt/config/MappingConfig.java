package za.co.serengti.receipt.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import za.co.serengti.receipt.domain.Customer;
import za.co.serengti.receipt.entity.EmailCustomerEntity;
import za.co.serengti.receipt.entity.MobileCustomerEntity;

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

        modelMapper.createTypeMap(EmailCustomerEntity.class, Customer.class)
                .addMapping(EmailCustomerEntity::getId, Customer::setId)
                .addMapping(EmailCustomerEntity::getName, Customer::setName)
                .addMapping(EmailCustomerEntity::getEmailAddress, Customer::setEmailAddress);

        modelMapper.createTypeMap(MobileCustomerEntity.class, Customer.class)
                .addMapping(MobileCustomerEntity::getId, Customer::setId)
                .addMapping(MobileCustomerEntity::getName, Customer::setName)
                .addMapping(MobileCustomerEntity::getMobileNumber, Customer::setMobileNumber);

        return modelMapper;
    }
}
