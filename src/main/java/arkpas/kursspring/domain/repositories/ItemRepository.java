package arkpas.kursspring.domain.repositories;

import arkpas.kursspring.domain.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


@Repository
public class ItemRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void createItem (String name, int timeReduction, int cost) {
        Item item = new Item(name, timeReduction, cost);
        entityManager.persist(item);
    }

    public Item getItem (int id) {
        return entityManager.find(Item.class, id);
    }

    public List<Item> getItems () {
        return entityManager.createQuery("SELECT item FROM Item AS item", Item.class).getResultList();
    }

}
