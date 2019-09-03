package arkpas.kursspring.services;

import arkpas.kursspring.domain.PlayerInformation;
import arkpas.kursspring.domain.Quest;
import arkpas.kursspring.domain.repositories.QuestRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QuestServiceTest {

    @Mock
    private QuestRepository questRepository;
    @Mock
    private PlayerInformationService playerInformationService;

    private QuestService questService;
    private List<Quest> questList;

    @Before
    public void setUp () {
        questService = new QuestService(questRepository, playerInformationService);
        PlayerInformation playerInformation = new PlayerInformation();
        questList = new ArrayList<>();

        when(playerInformationService.getPlayer()).thenReturn(playerInformation);
        when(questRepository.getQuests(any(PlayerInformation.class))).thenReturn(questList);

    }

    @Test
    public void getNotStartedQuestsShouldReturnSomeQuests () {

        Quest quest1 = new Quest();
        Quest quest2 = new Quest();

        questList.add(quest1);
        questList.add(quest2);

        assertEquals(2, questService.getNotStartedQuests().size());
    }

    @Test
    public void getNotStartedQuestsShouldReturnEmptyList () {
        Quest quest1 = new Quest();
        Quest quest2 = new Quest();

        quest1.setStarted(true);
        quest2.setStarted(true);

        questList.add(quest1);
        questList.add(quest2);

        assertEquals(0, questService.getNotStartedQuests().size());
    }
}
