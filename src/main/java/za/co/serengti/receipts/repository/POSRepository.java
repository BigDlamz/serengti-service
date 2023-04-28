package za.co.serengti.receipts.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import za.co.serengti.receipts.entity.POSSystemEntity;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class POSRepository implements PanacheRepository<POSSystemEntity> {
}
