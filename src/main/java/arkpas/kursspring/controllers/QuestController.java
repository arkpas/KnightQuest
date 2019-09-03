package arkpas.kursspring.controllers;

import arkpas.kursspring.components.TimeComponent;
import arkpas.kursspring.domain.Knight;
import arkpas.kursspring.domain.Quest;
import arkpas.kursspring.services.KnightService;
import arkpas.kursspring.services.PlayerInformationService;
import arkpas.kursspring.services.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class QuestController {


    private QuestService questService;
    private KnightService knightService;
    private PlayerInformationService playerInformationService;

    @Autowired
    public QuestController (QuestService questService, KnightService knightService, PlayerInformationService playerInformationService) {
        this.questService = questService;
        this.knightService = knightService;
        this.playerInformationService = playerInformationService;

    }

    @RequestMapping("/questList")
    public String getAvailableQuests (@RequestParam("knightId") int id, Model model) {
        Knight knight = knightService.getKnight(id);
        if (playerInformationService.isItCurrentPlayer(knight.getPlayerInformation())) {
            List<Quest> availableQuests = questService.getNotStartedQuests();

            model.addAttribute("knight", knight);
            model.addAttribute("availableQuests", availableQuests);
            return "assignQuest";
        }
        else {
            return "redirect:/knights";
        }
    }

    @RequestMapping(value = "/assignQuest")
    public String assignQuest (@RequestParam("knightId") int knightId, @RequestParam("questId") int questId) {
        knightService.assignQuest(knightId, questId);
        return "redirect:/knights";
    }

    @RequestMapping(value = "/refreshQuests", method = RequestMethod.POST)
    public String refreshQuests (int knightId) {
        questService.deleteNotStartedQuests();
        int freeKnightCount = knightService.getFreeKnights().size();
        questService.createQuestsForFreeKnights(freeKnightCount);
        return "redirect:/questList?knightId=" + knightId;


    }

    @RequestMapping(value = "/checkQuests")
    public String checkQuests () {
        knightService.getMyGold();
        return "redirect:/knights";
    }
}
