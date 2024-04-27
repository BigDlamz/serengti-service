package za.co.serengti.shoppers;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ShopperMapper {

    public Shopper toEntity(ShopperDTO dto) {

        return Shopper.builder()
                .shopperId(dto.getShopperId())
                .name(dto.getName())
                .emailAddress(dto.getEmail())
                .mobileNumber(dto.getMobileNumber())
                .build();
    }

    public ShopperDTO toDTO(Shopper entity) {

        return ShopperDTO.builder()
                .shopperId(entity.getShopperId())
                .name(entity.getName())
                .email(entity.getEmailAddress())
                .mobileNumber(entity.getMobileNumber())
                .build();
    }
}
