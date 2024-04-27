package za.co.serengti.merchants;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class SpecialService {

    private final SpecialRepository specialRepository;
    private final SpecialMapper converter;

    @Inject
    public SpecialService(SpecialRepository specialRepository, SpecialMapper converter) {
        this.specialRepository = specialRepository;
        this.converter = converter;
    }

    public List<SpecialDTO> retrieveSpecials(Long merchantId ){

        return specialRepository.findByStoreId(merchantId)
                .stream()
                .map(converter::toDTO)
                .toList();

    }
}
