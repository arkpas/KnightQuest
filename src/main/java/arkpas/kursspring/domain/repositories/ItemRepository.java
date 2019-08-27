package arkpas.kursspring.domain.repositories;

import arkpas.kursspring.domain.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Repository
public class ItemRepository {

    @PersistenceContext
    EntityManager entityManager;


    public Item getItem (int id) {
        return entityManager.find(Item.class, id);
    }

    public List<Item> getItems () {
        return entityManager.createQuery("SELECT item FROM Item AS item", Item.class).getResultList();
    }

    @Transactional
    public void updateItem (Item item) {
        entityManager.merge(item);
    }

    @Transactional
    public void deleteItem (Item item) {
        entityManager.remove(item);
    }

    @Transactional
    public void createItem (Item item) {
        entityManager.persist(item);
    }



}
