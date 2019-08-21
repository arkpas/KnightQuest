package arkpas.kursspring;

import arkpas.kursspring.components.TimeComponent;
import arkpas.kursspring.controllers.QuestController;
import arkpas.kursspring.domain.Knight;
import arkpas.kursspring.domain.PlayerInformation;
import arkpas.kursspring.domain.Quest;
import arkpas.kursspring.services.KnightService;
import arkpas.kursspring.services.QuestService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebContextTest {

    @MockBean
    KnightService knightService;
    @MockBean
    PlayerInformation playerInformation;
    @MockBean
    QuestService questService;
    @MockBean
    TimeComponent timeComponent;

    @Autowired
    QuestController questController;

    private MockMvc mockMvc;

    @Before
    public void setup () {
        //questController = new QuestController(questService, knightService, timeComponent, playerInformation);
        mockMvc = MockMvcBuilders.standaloneSetup(QuestController.class).build();
    }

    @Test
    public void testCheckQuests () throws Exception {

    /*    Quest quest = new Quest("TestQuest", 50, 0);

        Knight knight1 = new Knight("TestKnight1", 22);
        knight1.setQuest(quest);
        Knight knight2 = new Knight("TestKnight2", 23);



        List<Knight> knightsList = new ArrayList<>();
        knightsList.add(knight1);
        knightsList.add(knight2);

        when(knightService.getKnightList()).thenReturn(knightsList);*/

        mockMvc.perform(get("/checkQuests"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/knights"))
        ;

    }
    @Test
    public void contextLoads () {}
}
