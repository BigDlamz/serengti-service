package za.co.serengti.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import za.co.serengti.customers.dto.CustomerDTO;
import za.co.serengti.customers.entity.Customer;
import za.co.serengti.customers.entity.EmailCustomer;
import za.co.serengti.customers.entity.MobileCustomer;
import za.co.serengti.receipts.dto.ReceiptDTO;
import za.co.serengti.receipts.entity.Receipt;

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

        modelMapper.createTypeMap(Receipt.class, ReceiptDTO.class)
                .addMapping(Receipt::getReceiptID, ReceiptDTO::setReceiptID)
                .addMapping(Receipt::getTransactionDate, ReceiptDTO::setTimestamp)
                .addMapping(Receipt::getPosSystem, ReceiptDTO::setPosSystem)
                .addMapping(Receipt::getStore, ReceiptDTO::setStore)
                .addMapping(Receipt::getTill, ReceiptDTO::setTill)
                .addMapping(Receipt::getCashier, ReceiptDTO::setCashier)
                .addMapping(Receipt::getPromotions, ReceiptDTO::setPromotions)
                .addMapping(Receipt::getPurchasedItems, ReceiptDTO::setPurchasedItems)
                .addMapping(Receipt::getSubTotal, ReceiptDTO::setSubTotal)
                .addMapping(Receipt::getVatRate, ReceiptDTO::setVatRate)
                .addMappings(mapping -> mapping.using(ctx -> {
                    Customer customer = (Customer) ctx.getSource();
                    if (customer instanceof EmailCustomer) {
                        return modelMapper.map(customer, CustomerDTO.class);
                    } else if (customer instanceof MobileCustomer) {
                        return modelMapper.map(customer, CustomerDTO.class);
                    } else {
                        return null;
                    }
                }).map(Receipt::getCustomer, ReceiptDTO::setCustomer));

        modelMapper.createTypeMap(EmailCustomer.class, CustomerDTO.class)
                .addMapping(EmailCustomer::getCustomerID, CustomerDTO::setCustomerID)
                .addMapping(EmailCustomer::getName, CustomerDTO::setName)
                .addMapping(EmailCustomer::getEmailAddress, CustomerDTO::setEmailAddress);

        modelMapper.createTypeMap(MobileCustomer.class, CustomerDTO.class)
                .addMapping(MobileCustomer::getCustomerID, CustomerDTO::setCustomerID)
                .addMapping(MobileCustomer::getName, CustomerDTO::setName)
                .addMapping(MobileCustomer::getMobileNumber, CustomerDTO::setMobileNumber);


        return modelMapper;
    }
}
