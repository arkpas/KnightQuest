package arkpas.kursspring.services;

import arkpas.kursspring.domain.Equipment;
import arkpas.kursspring.domain.Item;
import arkpas.kursspring.domain.Knight;
import arkpas.kursspring.domain.repositories.EquipmentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EquipmentServiceTest {

    @Mock
    private EquipmentRepository equipmentRepository;

    @Test
    public void hasItemInEquipmentShouldReturnFalse () {

        EquipmentService equipmentService = new EquipmentService(equipmentRepository);

        when(equipmentRepository.getEquipment(anyInt(), anyInt())).thenReturn(new ArrayList<>());

        Knight knight = new Knight();
        knight.setId(0);

        Item item = new Item();
        item.setId(0);

        assertFalse(equipmentService.hasItemInEquipment(knight, item));
    }

    @Test
    public void hasItemInEquipmentShouldReturnTrue () {
        List<Equipment> list = new ArrayList<>();
        Equipment equipment = new Equipment();
        list.add(equipment);

        EquipmentService equipmentService = new EquipmentService(equipmentRepository);
        when(equipmentRepository.getEquipment(anyInt(), anyInt())).thenReturn(list);

        Knight knight = new Knight();
        knight.setId(0);

        Item item = new Item();
        item.setId(0);

        assertTrue(equipmentService.hasItemInEquipment(knight, item));
    }
}
