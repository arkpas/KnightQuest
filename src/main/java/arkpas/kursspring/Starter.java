package arkpas.kursspring;

import arkpas.kursspring.domain.Item;
import arkpas.kursspring.domain.PlayerInformation;
import arkpas.kursspring.domain.repositories.*;
import arkpas.kursspring.services.ItemService;
import arkpas.kursspring.services.KnightService;
import arkpas.kursspring.utils.QuestRarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
public class Starter implements CommandLineRunner {



	private QuestRepository questRepository;
	private KnightService knightService;
	private ItemService itemService;
	private PlayerInformationRepository playerInformationRepository;
	private RoleRepository roleRepository;

	@Autowired
	public Starter(QuestRepository questRepository, KnightService knightService, ItemService itemService, PlayerInformationRepository playerInformationRepository, RoleRepository roleRepository) {
		this.questRepository = questRepository;
		this.knightService = knightService;
		this.itemService = itemService;
		this.playerInformationRepository = playerInformationRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public void run(String... args) throws Exception {


		roleRepository.createRole("arek", "ADMIN");
		playerInformationRepository.createPlayer("arek", "123");

		questRepository.createQuestTemplate("Uratuj księżniczkę", QuestRarity.COMMON, 100, 5);
		questRepository.createQuestTemplate("Zabij smoka", QuestRarity.COMMON, 100, 5);
		questRepository.createQuestTemplate("Kup eliksir", QuestRarity.COMMON, 100, 5);

		itemService.createItem("Rękawice", 2, 100);
		itemService.createItem("Buty", 2, 100);
		itemService.createItem("Hełm", 2, 100);
		itemService.createItem("Zbroja", 4, 250);

	}



}
