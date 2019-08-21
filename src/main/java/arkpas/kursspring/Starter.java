package arkpas.kursspring;

import arkpas.kursspring.domain.repositories.ItemRepository;
import arkpas.kursspring.domain.repositories.KnightRepository;
import arkpas.kursspring.domain.repositories.QuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class Starter implements CommandLineRunner {


	@Autowired
	QuestRepository questRepository;

	@Autowired
	KnightRepository knightRepository;

	@Autowired
	ItemRepository itemRepository;

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

	}



}
