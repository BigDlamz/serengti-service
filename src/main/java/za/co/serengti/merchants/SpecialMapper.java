package za.co.serengti.merchants;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SpecialMapper {

    public SpecialDTO toDTO(Special special) {

        return SpecialDTO.builder()
                .productName(special.getProductName())
                .productDescription(special.getProductDescription())
                .imageUrl(special.getImageUrl())
                .oldPrice(special.getOldPrice())
                .newPrice(special.getNewPrice())
                .startDate(special.getStartDate())
                .endDate(special.getEndDate())
                .build();

    }

    public static Special toEntity(SpecialDTO specialDTO) {

        return Special.builder()
                .productName(specialDTO.getProductName())
                .productDescription(specialDTO.getProductDescription())
                .imageUrl(specialDTO.getImageUrl())
                .oldPrice(specialDTO.getOldPrice())
                .newPrice(specialDTO.getNewPrice())
                .startDate(specialDTO.getStartDate())
                .endDate(specialDTO.getEndDate())
                .build();

    }
}
