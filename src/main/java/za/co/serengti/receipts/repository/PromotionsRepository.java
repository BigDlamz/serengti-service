package za.co.serengti.receipts.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import za.co.serengti.receipts.entity.Promotions;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PromotionsRepository implements PanacheRepository<Promotions> {

    public Promotions save(Promotions promotions) {
        persistAndFlush(promotions);
        return promotions;
    }
}
