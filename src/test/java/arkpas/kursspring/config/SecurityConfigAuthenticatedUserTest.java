package arkpas.kursspring.config;

import arkpas.kursspring.components.TimeComponent;
import arkpas.kursspring.domain.PlayerInformation;
import arkpas.kursspring.services.PlayerInformationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigAuthenticatedUserTest {

        @Autowired
        PlayerInformationService playerInformationService;

        @MockBean
        TimeComponent timeComponent;

        @Autowired
        private MockMvc mockMvc;

        private PlayerInformation playerInformation = null;

        @Test
        public void test () {
        }

        @Before
        public void setUp () {
            if (playerInformation == null) {
                String name = "TestUser";
                String password = "test";
                playerInformationService.createPlayer(name, password);
                playerInformation = playerInformationService.getPlayer(name);
            }
        }

        @Test
        public void requestFromAuthenticatedUserShouldBeOk () throws Exception {

            mockMvc.perform(
                    get("/knights")
                            .with(user(playerInformation.getUsername()).authorities(() -> "USER")))
                    .andExpect(status().isOk());
        }

        @Test
        public void requestFromUserShouldBeRejected () throws Exception {
            mockMvc.perform(
                    get("/h2-console")
                            .with(user(playerInformation.getUsername()).authorities(() -> "USER")))
                    .andExpect(status().isForbidden());
        }


    }

