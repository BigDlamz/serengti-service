package za.co.serengti.merchants.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import za.co.serengti.merchants.entity.Product;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {
}
