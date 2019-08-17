package arkpas.kursspring.controllers;

import arkpas.kursspring.components.TimeComponent;
import arkpas.kursspring.domain.Knight;
import arkpas.kursspring.domain.PlayerInformation;
import arkpas.kursspring.domain.Quest;
import arkpas.kursspring.services.KnightService;
import arkpas.kursspring.services.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private TimeComponent timeComponent;
    private PlayerInformation playerInformation;

    @Autowired
    public QuestController (QuestService questService, KnightService knightService, TimeComponent timeComponent, PlayerInformation playerInformation) {
        this.questService = questService;
        this.knightService = knightService;
        this.timeComponent = timeComponent;
        this.playerInformation = playerInformation;
    }

    @RequestMapping("/assignQuest")
    public String assignQuest (@RequestParam("knightId") int id, Model model) {
        Knight knight = knightService.getKnight(id);
        List<Quest> availableQuests = questService.getNotStartedQuests();

        if (availableQuests.isEmpty()) {
            System.out.println("No quests available");
            return "redirect:/knights";
        }

        model.addAttribute("knight", knight);
        model.addAttribute("availableQuests", availableQuests);
        model.addAttribute("timecomponent", timeComponent);
        model.addAttribute("playerinformation", playerInformation);
        return "assignQuest";
    }

    @RequestMapping(value = "/assignQuest", method = RequestMethod.POST)
    public String updateKnight (int knightId, int questId) {
        Knight knight = knightService.getKnight(knightId);
        Quest quest = questService.getQuest(questId);
        knight.setQuest(quest);
        knightService.updateKnight(knight);
        return "redirect:/knights";
    }

    @RequestMapping(value = "/checkQuests")
    public String checkQuests () {
        knightService.getMyGold();
        return "redirect:/knights";
    }
}