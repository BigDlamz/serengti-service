package za.co.serengti.receipts.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import za.co.serengti.receipts.entity.LineItem;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LineItemsRepository implements PanacheRepository<LineItem> {

}
