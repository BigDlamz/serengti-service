package za.co.serengti.merchants;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MerchantMapper {

    public Merchant toEntity(MerchantDTO dto) {

        return Merchant.builder()
                .merchantId(dto.getMerchantId())
                .name(dto.getName())
                .address(dto.getAddress())
                .vatRegistrationNo(dto.getVatRegistrationNo())
                .storeLogoURL(dto.getMerchantLogo())
                .build();

    }

    public MerchantDTO toDTO(Merchant entity) {

        return MerchantDTO.builder()
                .merchantId(entity.merchantId)
                .name(entity.getName())
                .address(entity.getAddress())
                .vatRegistrationNo(entity.getVatRegistrationNo())
                .merchantLogo(entity.getStoreLogoURL())
                .build();

    }
}
