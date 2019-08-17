package arkpas.kursspring.services;

import arkpas.kursspring.domain.Knight;
import arkpas.kursspring.domain.Quest;
import arkpas.kursspring.domain.repositories.KnightRepository;
import arkpas.kursspring.domain.repositories.QuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class QuestService {
	

	KnightRepository knights;
	QuestRepository quests;
	
	private final Random rand = new Random();

	public void assignRandomQuest (int knightId) {
		List<Quest> notStartedQuests = getNotStartedQuests();
		if (notStartedQuests.isEmpty()) {
			System.out.println("Brak questow!");
		}
		else {
			int randomQuestId = rand.nextInt(notStartedQuests.size());

			Quest randomQuest = notStartedQuests.get(randomQuestId);
			Knight knight = knights.getKnight(knightId);
			knight.setQuest(randomQuest);
		}
		
	}

	public List<Quest> getNotStartedQuests () {
		return quests.getQuests().stream().filter(quest -> !quest.isStarted()).collect(Collectors.toList());
	}

	public Quest getQuest (int id) {
		return quests.getQuest(id);
	}

	@Autowired
	public void setQuestRepository (QuestRepository questRepository) {
		this.quests = questRepository;
	}

	@Autowired
	public void setKnightRepository (KnightRepository knightRepository) {
		this.knights = knightRepository;
	}

	public boolean isCompleted (int id) {
		return quests.getQuest(id).isCompleted();
	}
}
