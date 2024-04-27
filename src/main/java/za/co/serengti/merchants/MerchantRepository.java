package za.co.serengti.merchants;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MerchantRepository implements PanacheRepository<Merchant> {
}
