package arkpas.kursspring.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerInformationTest {

    @Test
    public void testAddingGold () {
        PlayerInformation playerInformation = new PlayerInformation();
        playerInformation.setGold(0);
        playerInformation.addGold(100);
        assertEquals(100, playerInformation.getGold());
    }

    @Test
    public void testSubstractingGold () {
        PlayerInformation playerInformation = new PlayerInformation();
        playerInformation.setGold(100);
        playerInformation.removeGold(100);
        assertEquals(0, playerInformation.getGold());
    }
}
