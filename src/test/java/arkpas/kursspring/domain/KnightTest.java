package arkpas.kursspring.domain;


import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class KnightTest {

    @Test
    public void getTimeReductionTest () {

        Knight knight = new Knight();
        Set<Equipment> equipmentSet = new HashSet<>();

        Item item1 = new Item();
        item1.setTimeReduction(5);
        Item item2 = new Item();
        item2.setTimeReduction(6);

        Equipment equipment1 = new Equipment();
        equipment1.setItem(item1);
        Equipment equipment2 = new Equipment();
        equipment2.setItem(item2);

        equipmentSet.add(equipment1);
        equipmentSet.add(equipment2);


        knight.setEquipmentSet(equipmentSet);
        assertEquals(11, knight.getTimeReduction());

    }

    @Test
    public void questShouldBeMarkedAsStarted () {
        Quest quest = new Quest();
        Knight knight = new Knight();

        knight.setQuest(quest);
        assertTrue(quest.isStarted());
    }

    @Test
    public void questShouldBeNull () {
        Quest quest = new Quest();
        Knight knight = new Knight();

        knight.setQuest(quest);
        knight.removeQuest();
        assertTrue(knight.getQuest() == null);

    }

    @Test
    public void knightShouldGainOneLevel () {
        Knight knight = new Knight();

        knight.addExperience(Knight.EXP_TABLE[1]);
        assertEquals(2, knight.getLevel());
        assertEquals(0, knight.getExperience());
        assertEquals(Knight.EXP_TABLE[2], knight.getExperienceRequired());

    }

}
