package arkpas.kursspring.controllers;

import arkpas.kursspring.components.TimeComponent;
import arkpas.kursspring.domain.Knight;
import arkpas.kursspring.domain.repositories.PlayerInformationRepository;
import arkpas.kursspring.services.KnightService;
import arkpas.kursspring.services.PlayerInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class KnightController {


	private TimeComponent timeComponent;
	private PlayerInformationService playerInformationService;
	private KnightService knightService;

	@Autowired
	public KnightController(TimeComponent timeComponent, PlayerInformationService playerInformationService, KnightService knightService) {
		this.timeComponent = timeComponent;
		this.playerInformationService = playerInformationService;
		this.knightService = knightService;
	}

	@RequestMapping("/knights")
	public String getKnights(Model model) {
		List<Knight> knightList = knightService.getKnightList();
		model.addAttribute("knights", knightList);
		model.addAttribute("timecomponent", timeComponent);
		model.addAttribute("playerinformation", playerInformationService.getPlayer());
		return "knights";
	}

	@RequestMapping(value="/knights", method = RequestMethod.POST)
	public String saveKnight(@Valid Knight knight, BindingResult result, Model model) {
		model.addAttribute("timecomponent", timeComponent);
		model.addAttribute("playerinformation", playerInformationService.getPlayer());
		if (result.hasErrors()) {
			return "knightform";
		}
		knightService.saveKnight(knight);
		return "redirect:/knights";
	}

	@RequestMapping("/newknight")
	public String createKnight(Model model) {
		model.addAttribute("knight", new Knight());
		model.addAttribute("timecomponent", timeComponent);
		model.addAttribute("playerinformation", playerInformationService.getPlayer());
		return "knightform";
	}

	@RequestMapping("/knight")
	public String getKnight(@RequestParam(value = "knightId") int id, Model model) {
		Knight knight = knightService.getKnight(id);
		model.addAttribute("knight", knight);
		model.addAttribute("timecomponent", timeComponent);
		model.addAttribute("playerinformation", playerInformationService.getPlayer());
		return "knight";
	}

	@RequestMapping("/knight/delete/{id}")
	public String deleteKnight(@PathVariable() Integer id) {
		Knight knight = knightService.getKnight(id);
		knightService.deleteKnight(knight);
		return "redirect:/knights";
	}
}
