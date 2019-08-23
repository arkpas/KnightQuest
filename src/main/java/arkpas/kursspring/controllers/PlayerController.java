package arkpas.kursspring.controllers;

import arkpas.kursspring.domain.PlayerInformation;
import arkpas.kursspring.domain.repositories.PlayerInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlayerController {

    private PlayerInformationRepository playerInformationRepository;

    @Autowired
    public PlayerController(PlayerInformationRepository playerInformationRepository) {
        this.playerInformationRepository = playerInformationRepository;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegisterForm () {
        return "registerform";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser (@RequestParam("username") String username, @RequestParam("password") String password) {
        playerInformationRepository.createPlayer(username, password);
        password = null;
        return "redirect:/login";
    }
}
