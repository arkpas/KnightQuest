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


		roleRepository.createRole("admin", "ADMIN");
		playerInformationRepository.createPlayer("admin", "123");

		questRepository.createQuestTemplate("Wyczyść zbroję", QuestRarity.COMMON, 50, 1, 10);
		questRepository.createQuestTemplate("Nakarm konie", QuestRarity.COMMON, 50, 1,10);
		questRepository.createQuestTemplate("Kup eliksir", QuestRarity.COMMON, 50, 1, 10);

		questRepository.createQuestTemplate("Eskortuj kupca", QuestRarity.RARE, 75, 2, 20);
		questRepository.createQuestTemplate("Ubij dzika", QuestRarity.RARE, 75, 2, 20);
		questRepository.createQuestTemplate("Trenuj giermka", QuestRarity.RARE, 75, 2, 20);

		questRepository.createQuestTemplate("Uchroń wioskę przed bandytami", QuestRarity.EPIC, 150, 5, 40);
		questRepository.createQuestTemplate("Znajdź i ukarz dezerterów", QuestRarity.EPIC, 150, 5, 40);
		questRepository.createQuestTemplate("Eksploruj lochy", QuestRarity.EPIC, 150, 5, 40);

		itemService.createItem("Rękawice", 2, 100);
		itemService.createItem("Buty", 2, 100);
		itemService.createItem("Hełm", 2, 100);
		itemService.createItem("Zbroja", 4, 250);

	}



}
