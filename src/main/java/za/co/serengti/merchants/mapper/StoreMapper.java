package za.co.serengti.merchants.mapper;

import za.co.serengti.merchants.dto.StoreDTO;
import za.co.serengti.merchants.entity.Store;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StoreMapper {

    public Store toEntity(StoreDTO dto) {
        return Store.builder()
                .storeId(dto.getStoreId())
                .name(dto.getName())
                .address(dto.getAddress())
                .vatRegistrationNumber(dto.getVatRegistrationNumber())
                .build();
    }

    public StoreDTO toDto(Store entity) {
        return StoreDTO.builder()
                .storeId(entity.getStoreId())
                .name(entity.getName())
                .address(entity.getAddress())
                .vatRegistrationNumber(entity.getVatRegistrationNumber())
                .storeLogoURL(entity.getStoreLogoURL())
                .build();
    }
}
