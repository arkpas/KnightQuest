package services;

import arkpas.kursspring.domain.QuestTemplate;
import arkpas.kursspring.domain.repositories.QuestRepository;
import arkpas.kursspring.services.QuestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QuestServiceTest {

    @Mock
    QuestRepository questRepositoryMock;


    @Test
    public void testGettingNotStartedQuests () {

//        QuestTemplate sampleQuest1 = new QuestTemplate("TestQuest1", 50, 50);
//        QuestTemplate sampleQuest2 = new QuestTemplate("TestQuest2", 50, 50);
//        QuestTemplate sampleQuest3 = new QuestTemplate("TestQuest3", 50, 50);
//
//        List<QuestTemplate> sampleQuestList = new ArrayList<>();
//
//        sampleQuestList.add(sampleQuest1);
//        sampleQuestList.add(sampleQuest2);
//        sampleQuestList.add(sampleQuest3);
//
//        sampleQuest2.setStarted(true);
//
//        when(questRepositoryMock.getQuests()).thenReturn(sampleQuestList);
//
//        QuestService questService = new QuestService(null, questRepositoryMock);
//        List<QuestTemplate> notStartedQuests = questService.getNotStartedQuests();
//
//        assertEquals(2, notStartedQuests.size());
//
//        assertThat(notStartedQuests,containsInAnyOrder(sampleQuest1, sampleQuest3));
//        // OR
//        assertEquals(sampleQuest1, notStartedQuests.get(0));
//        assertEquals(sampleQuest3, notStartedQuests.get(1));

    }
}
