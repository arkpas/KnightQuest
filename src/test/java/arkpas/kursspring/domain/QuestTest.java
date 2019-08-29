package arkpas.kursspring.domain;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;


public class QuestTest {

    @Test
    public void testSettingQuestStartedToFalse () {
        Quest quest = new Quest();

        quest.setStarted(false);
        assertEquals(null, quest.getStartDate());
    }

    @Test
    public void testSettingQuestStartedToTrue () {
        Quest quest = new Quest();

        quest.setStarted(true);
        assertTrue(quest.getStartDate() != null);
    }

    @Test
    public void notStartedQuestShouldNotBeCompleted () {
        Quest quest = new Quest();
        QuestTemplate questTemplate = new QuestTemplate();
        questTemplate.setLength(0);

        quest.setQuestTemplate(questTemplate);
        assertFalse(quest.isCompleted());
    }

    @Test
    public void startedQuestShouldNotBeCompleted () {
        Quest quest = new Quest();

        QuestTemplate questTemplate = new QuestTemplate();
        questTemplate.setDescription("Test quest");
        questTemplate.setLength(999);

        quest.setQuestTemplate(questTemplate);
        quest.setStarted(true);

        assertFalse(quest.isCompleted());
    }

    @Test
    public void startedQuestShouldBeCompleted () {
        Quest quest = new Quest();

        QuestTemplate questTemplate = new QuestTemplate();
        questTemplate.setDescription("Test quest");
        questTemplate.setLength(-1);

        quest.setQuestTemplate(questTemplate);
        quest.setStarted(true);

        assertTrue(quest.isCompleted());
    }
}
