package domain;

import arkpas.kursspring.domain.Knight;
import arkpas.kursspring.domain.Quest;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class KnightTest {

    @Test
    public void testIfQuestIsStarted () {
        Quest quest = new Quest("TestQuest", 50, 50);
        Knight knight = new Knight("TestKnight", 22);
        knight.setQuest(quest);
        assertTrue(quest.isStarted());

    }
}
