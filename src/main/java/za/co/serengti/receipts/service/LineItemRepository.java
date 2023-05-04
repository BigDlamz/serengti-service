package za.co.serengti.receipts.service;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import za.co.serengti.receipts.entity.LineItem;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LineItemRepository implements PanacheRepository<LineItem> {
}

