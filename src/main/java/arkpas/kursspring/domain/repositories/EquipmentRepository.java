package arkpas.kursspring.domain.repositories;

import arkpas.kursspring.domain.Equipment;
import arkpas.kursspring.domain.Item;
import arkpas.kursspring.domain.Knight;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class EquipmentRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void createEquipment (Knight knight, Item item) {
        Equipment equipment = new Equipment();
        equipment.setKnight(knight);
        equipment.setItem(item);
        entityManager.persist(equipment);
    }

    public List<Equipment> getEquipment (int knightId, int itemId) {
        return entityManager.createQuery("SELECT e FROM Equipment AS e WHERE knight_id = :knightId AND item_id = :itemId", Equipment.class)
                .setParameter("knightId", knightId)
                .setParameter("itemId", itemId)
                .getResultList();
    }

}
