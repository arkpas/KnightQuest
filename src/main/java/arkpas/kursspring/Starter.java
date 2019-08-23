package arkpas.kursspring;

import arkpas.kursspring.domain.Role;
import arkpas.kursspring.domain.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class Starter implements CommandLineRunner {



	private QuestRepository questRepository;
	private KnightRepository knightRepository;
	private ItemRepository itemRepository;
	private PlayerInformationRepository playerInformationRepository;
	private RoleRepository roleRepository;

	@Autowired
	public Starter(QuestRepository questRepository, KnightRepository knightRepository, ItemRepository itemRepository, PlayerInformationRepository playerInformationRepository, RoleRepository roleRepository) {
		this.questRepository = questRepository;
		this.knightRepository = knightRepository;
		this.itemRepository = itemRepository;
		this.playerInformationRepository = playerInformationRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		questRepository.createRandomQuest();
		questRepository.createRandomQuest();

		knightRepository.createKnight("Lancelot", 22);
		knightRepository.createKnight("Percival", 19);

		itemRepository.createItem("Rękawice", 2, 100);
		itemRepository.createItem("Buty", 2, 100);
		itemRepository.createItem("Hełm", 2, 100);
		itemRepository.createItem("Zbroja", 4, 250);

		roleRepository.createRole("arek", "ADMIN");

		playerInformationRepository.createPlayer("arek", "123");




	}



}
