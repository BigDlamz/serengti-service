package za.co.serengti.shoppers;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.Objects;

@ApplicationScoped
public class ShopperServiceImpl implements ShopperService {

    private final ShopperRepository shopperRepository;
    private final ShopperMapper convertor;

    public ShopperServiceImpl(ShopperRepository shopperRepository, ShopperMapper convertor) {
        this.shopperRepository = shopperRepository;
        this.convertor = convertor;
    }

    @Override
    public ShopperDTO find(String email) {

        Shopper shopper = shopperRepository.
                findByEmailAddress(email)
                .orElse(null);

        assert shopper != null;
        return convertor.toDTO(shopper);
    }
}
