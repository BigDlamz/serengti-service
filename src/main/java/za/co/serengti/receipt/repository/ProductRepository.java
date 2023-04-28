package za.co.serengti.receipt.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import za.co.serengti.receipt.entity.ProductEntity;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<ProductEntity> {
}
