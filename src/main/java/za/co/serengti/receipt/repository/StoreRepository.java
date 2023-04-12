package za.co.serengti.receipt.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import za.co.serengti.receipt.entity.Store;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StoreRepository implements PanacheRepository<Store> {
}
