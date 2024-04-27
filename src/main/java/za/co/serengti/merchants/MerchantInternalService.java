package za.co.serengti.merchants;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MerchantInternalService {

    private final MerchantRepository merchantRepository;
    private final MerchantMapper convertor;

    public MerchantInternalService(MerchantRepository merchantRepository, MerchantMapper convertor) {
        this.merchantRepository = merchantRepository;
        this.convertor = convertor;
    }

    public MerchantDTO find(Long merchantId) {

        return convertor.toDTO(merchantRepository.findById(merchantId));

    }
}
