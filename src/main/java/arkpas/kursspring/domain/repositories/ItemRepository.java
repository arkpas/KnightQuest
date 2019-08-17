package arkpas.kursspring.domain.repositories;

import arkpas.kursspring.domain.Item;
import arkpas.kursspring.utils.Ids;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ItemRepository {

    Map<Integer, Item> items = new HashMap<>();

    private void createItem (String name, int timeReduction, int cost) {
        int id = Ids.getNextId(items.keySet());
        Item item = new Item(id, name, timeReduction, cost);
        items.put(item.getId(), item);
    }

    @PostConstruct
    public void createItems () {
        createItem("Rękawice", 1, 100);
        createItem("Buty", 1, 100);
        createItem("Hełm", 1, 100);
        createItem("Zbroja", 2, 200);
    }

    public Item getItem (int id) {
        return items.get(id);
    }

    public Collection<Item> getItems () {
        return items.values();
    }

}
