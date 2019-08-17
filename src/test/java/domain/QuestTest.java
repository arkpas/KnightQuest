package domain;

import arkpas.kursspring.domain.Quest;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuestTest {

    @Test
    public void startDateTest () {
        Quest quest = new Quest("Test quest", 50, 50);
        quest.setStarted(false);
        assertNull(quest.getStartDate());
        quest.setStarted(true);
        assertNotNull(quest.getStartDate());
    }

    @Test
    public void testIfQuestIsCompleted () {
        Quest quest = new Quest("Test quest", 50, 60);
        quest.setStarted(true);
        assertFalse(quest.isCompleted());
        quest.setLength(-60);
        assertTrue(quest.isCompleted());


    }
}
