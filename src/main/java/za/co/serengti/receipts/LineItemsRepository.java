package za.co.serengti.receipts;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class LineItemsRepository implements PanacheRepository<LineItem> {

    public List<LineItem> save(List<LineItem> items) {
        return  items
                .stream()
                .map(lineItem -> getEntityManager().merge(lineItem))
                .collect(Collectors.toList());
    }

}