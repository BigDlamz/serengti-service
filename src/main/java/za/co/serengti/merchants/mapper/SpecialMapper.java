package za.co.serengti.merchants.mapper;

import za.co.serengti.merchants.dto.SpecialDTO;
import za.co.serengti.merchants.dto.StoreDTO;
import za.co.serengti.merchants.entity.Special;
import za.co.serengti.merchants.entity.Store;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SpecialMapper {

    public static SpecialDTO toDTO(Special special) {
        if (special == null) return null;

        return SpecialDTO.builder()
                .specialId(special.getSpecialId())
                .store(StoreDTO.builder()
                        .storeId(special.getStore().getStoreId())
                        .name(special.getStore().getName())
                        .address(special.getStore().getAddress())
                        .vatRegistrationNumber(special.getStore().getVatRegistrationNumber())
                        .storeLogoURL(special.getStore().getStoreLogoURL())
                        .build())
                .product(special.getProduct())
                .name(special.getName())
                .description(special.getDescription())
                .specialImageUrl(special.getSpecialImageUrl())
                .oldPrice(special.getOldPrice())
                .newPrice(special.getNewPrice())
                .startDate(special.getStartDate())
                .endDate(special.getEndDate())
                .build();
    }

    public static Special toEntity(SpecialDTO specialDTO) {
        if (specialDTO == null) return null;

        Store store = Store.builder()
                .storeId(specialDTO.getStore().getStoreId())
                .name(specialDTO.getStore().getName())
                .address(specialDTO.getStore().getAddress())
                .vatRegistrationNumber(specialDTO.getStore().getVatRegistrationNumber())
                .storeLogoURL(specialDTO.getStore().getStoreLogoURL())
                .build();

        return Special.builder()
                .specialId(specialDTO.getSpecialId())
                .store(store)
                .product(specialDTO.getProduct())
                .name(specialDTO.getName())
                .description(specialDTO.getDescription())
                .specialImageUrl(specialDTO.getSpecialImageUrl())
                .oldPrice(specialDTO.getOldPrice())
                .newPrice(specialDTO.getNewPrice())
                .startDate(specialDTO.getStartDate())
                .endDate(specialDTO.getEndDate())
                .build();
    }
}
