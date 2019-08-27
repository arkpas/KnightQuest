package arkpas.kursspring;

import arkpas.kursspring.components.TimeComponent;
import arkpas.kursspring.controllers.QuestController;
import arkpas.kursspring.domain.repositories.PlayerInformationRepository;
import arkpas.kursspring.services.KnightService;
import arkpas.kursspring.services.QuestService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebContextTest {

    @Test
    public void contextLoads () {}
}
