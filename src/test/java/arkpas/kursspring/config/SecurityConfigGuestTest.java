package arkpas.kursspring.config;

import arkpas.kursspring.components.TimeComponent;
import arkpas.kursspring.services.PlayerInformationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigGuestTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test () {
    }

    @Test
    public void requestFromNotAuthenticatedUserShouldBeOk () throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/css"))
                .andExpect(status().isOk());
    }

    @Test
    public void notAuthenticatedUserShouldBeRedirected () throws Exception {
        mockMvc.perform(get("/knights"))
                .andExpect(status().is3xxRedirection());
    }

}



