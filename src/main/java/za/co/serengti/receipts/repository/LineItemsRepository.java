package za.co.serengti.receipts.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import za.co.serengti.receipts.entity.LineItem;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LineItemsRepository implements PanacheRepository<LineItem> {

    public LineItem save(LineItem receipt) {
        persistAndFlush(receipt);
        return receipt;
    }
}
