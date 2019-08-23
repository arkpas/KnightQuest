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
	

	private KnightRepository knightRepository;
	private QuestRepository questRepository;
	
	private final Random rand = new Random();

	@Autowired
	public QuestService(KnightRepository knightRepository, QuestRepository questRepository) {
		this.knightRepository = knightRepository;
		this.questRepository = questRepository;
	}

	public void assignRandomQuest (int knightId) {
		List<Quest> notStartedQuests = getNotStartedQuests();
		if (notStartedQuests.isEmpty()) {
			System.out.println("Brak questow!");
		}
		else {
			int randomQuestId = rand.nextInt(notStartedQuests.size());

			Quest randomQuest = notStartedQuests.get(randomQuestId);
			Knight knight = knightRepository.getKnight(knightId);
			knight.setQuest(randomQuest);
		}
		
	}

	public List<Quest> getNotStartedQuests () {
		return questRepository.getQuests().stream().filter(quest -> !quest.isStarted()).collect(Collectors.toList());
	}

	public Quest getQuest (int id) {
		return questRepository.getQuest(id);
	}

	public void updateQuest (Quest quest) {
		questRepository.updateQuest(quest);
	}

	public void deleteQuest (Quest quest) {
		questRepository.deleteQuest(quest);
	}

	public boolean isCompleted (int id) {
		return questRepository.getQuest(id).isCompleted();
	}
}
