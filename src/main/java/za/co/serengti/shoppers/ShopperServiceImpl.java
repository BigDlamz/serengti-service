package za.co.serengti.shoppers;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ShopperServiceImpl implements ShopperService {

    private final ShopperRepository repository;
    private final ShopperMapper convertor;

    public ShopperServiceImpl( ShopperRepository repository, ShopperMapper convertor) {
        this.repository = repository;
        this.convertor = convertor;
    }

    @Override
    public ShopperDTO find(String email) {

        Shopper shopper = repository
                .findByEmailAddress(email)
                .orElse(null);

        assert shopper != null;
        return convertor.toDTO(shopper);
    }

}
