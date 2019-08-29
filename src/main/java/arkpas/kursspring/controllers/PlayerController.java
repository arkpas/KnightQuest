package arkpas.kursspring.controllers;

import arkpas.kursspring.services.PlayerInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlayerController {

    private PlayerInformationService playerInformationService;

    @Autowired
    public PlayerController(PlayerInformationService playerInformationService) {
        this.playerInformationService = playerInformationService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegisterForm () {
        return "registerform";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser (@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        String message = playerInformationService.createPlayer(username, password);
        password = null;
        if (message != null) {
            model.addAttribute("message", message);
            return "registerform";
        }
        else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginForm () {
        return "login";
    }

    @RequestMapping(value = "/login-failure", method = RequestMethod.GET)
    public String getLoginFailureForm (Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
}
