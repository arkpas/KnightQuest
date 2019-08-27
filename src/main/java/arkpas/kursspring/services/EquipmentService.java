package arkpas.kursspring.services;

import arkpas.kursspring.domain.Equipment;
import arkpas.kursspring.domain.Item;
import arkpas.kursspring.domain.Knight;
import arkpas.kursspring.domain.repositories.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class EquipmentService {

    private EquipmentRepository equipmentRepository;

    @Autowired
    public EquipmentService (EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public void addEquipment (Knight knight, Item item) {
        equipmentRepository.createEquipment(knight, item);
    }

    public boolean hasItemInEquipment (Knight knight, Item item) {
        List<Equipment> equipmentList= equipmentRepository.getEquipment(knight.getId(), item.getId());
        return !equipmentList.isEmpty();
    }

}
