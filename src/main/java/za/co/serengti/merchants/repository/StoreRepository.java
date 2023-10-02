package za.co.serengti.merchants.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import za.co.serengti.merchants.entity.Store;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StoreRepository implements PanacheRepository<Store> {
}
