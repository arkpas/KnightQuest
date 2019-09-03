package arkpas.kursspring.services;

import arkpas.kursspring.domain.Knight;
import arkpas.kursspring.domain.PlayerInformation;
import arkpas.kursspring.domain.Quest;
import arkpas.kursspring.domain.QuestTemplate;
import arkpas.kursspring.domain.repositories.KnightRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class KnightServiceTest {

    @Mock
    private KnightRepository knightRepository;
    @Mock
    private PlayerInformationService playerInformationService;
    @Mock
    private QuestService questService;

    private KnightService knightService;
    private PlayerInformation playerInformation;
    private List<Knight> list;
    private Knight knight1;
    private Knight knight2;


    @Before
    public void setUp () {
        knightService = new KnightService(knightRepository, playerInformationService, questService);

        playerInformation = new PlayerInformation();
        playerInformation.setGold(0);

        list = new ArrayList<>();
        knight1 = new Knight();
        knight2 = new Knight();

        list.add(knight1);
        list.add(knight2);

        when(playerInformationService.getPlayer()).thenReturn(playerInformation);
        when(playerInformationService.isItCurrentPlayer(any())).thenReturn(true);
        when(knightService.getKnightList()).thenReturn(list);
        doNothing().when(playerInformationService).updatePlayer(any(PlayerInformation.class));
        doNothing().when(questService).deleteQuest(any(Quest.class));

    }
    @Test
    public void getGoldMethodShouldWorkWithNullQuests () {

        knightService.getMyGold();

        Assert.assertEquals(0, playerInformation.getGold());

    }

    @Test
    public void getGoldMethodShouldAddGoldToPlayer () {

        Quest quest1 = new Quest();
        Quest quest2 = new Quest();

        QuestTemplate questTemplate = new QuestTemplate();
        questTemplate.setGoldReward(10);
        questTemplate.setLength(-1);

        quest1.setQuestTemplate(questTemplate);
        quest2.setQuestTemplate(questTemplate);
        quest1.setStarted(true);
        quest2.setStarted(true);

        knight1.setQuest(quest1);
        knight2.setQuest(quest2);

        knightService.getMyGold();

        Assert.assertEquals(20, playerInformation.getGold());
    }

    @Test
    public void afterGetGoldMethodKnightShouldNotHaveQuest () {

        Quest quest1 = new Quest();

        QuestTemplate questTemplate = new QuestTemplate();
        questTemplate.setGoldReward(10);
        questTemplate.setLength(-1);

        quest1.setQuestTemplate(questTemplate);
        quest1.setStarted(true);

        knight1.setQuest(quest1);

        assertNotNull(knight1.getQuest());
        knightService.getMyGold();
        assertNull(knight1.getQuest());

    }

    @Test
    public void questShouldBeAssignedToKnight () {

        Quest quest1 = new Quest();
        quest1.setTimeReduction(5);

        when(knightRepository.getKnight(anyInt())).thenReturn(knight1);
        when(questService.getQuest(anyInt())).thenReturn(quest1);

        knightService.assignQuest(0,0);

        assertEquals(0, quest1.getTimeReduction()); //because our knight1 has 0 reduction, it should be changed from 5 (which we set above) to 0
        assertNotNull(knight1.getQuest());



    }
}
