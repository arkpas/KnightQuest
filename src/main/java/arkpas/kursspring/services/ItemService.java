package arkpas.kursspring.services;

import arkpas.kursspring.domain.Item;
import arkpas.kursspring.domain.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private ItemRepository itemRepository;

    @Autowired
    public ItemService (ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item getItem (int id) {
        return itemRepository.getItem(id);
    }

    public List<Item> getItems () {
        return itemRepository.getItems();
    }

    public void createItem (Item item) {
        createItem(item.getName(), item.getTimeReduction(), item.getPrice());
    }

    public void createItem (String name, int timeReduction, int price) {
        Item item = new Item();
        item.setName(name);
        item.setTimeReduction(timeReduction);
        item.setPrice(price);

        itemRepository.createItem(item);
    }

    public void updateItem (Item item) {
        itemRepository.updateItem(item);
    }

    public void deleteItem (Item item) {
        itemRepository.deleteItem(item);
    }
}
