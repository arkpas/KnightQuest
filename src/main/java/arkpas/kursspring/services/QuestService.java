package arkpas.kursspring.services;

import arkpas.kursspring.domain.PlayerInformation;
import arkpas.kursspring.domain.Quest;
import arkpas.kursspring.domain.QuestTemplate;
import arkpas.kursspring.domain.repositories.QuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class QuestService {

	private QuestRepository questRepository;
	private PlayerInformationService playerInformationService;
	
	private final Random rand = new Random();

	@Autowired
	public QuestService(QuestRepository questRepository, PlayerInformationService playerInformationService) {
		this.questRepository = questRepository;
		this.playerInformationService = playerInformationService;
	}


	public void createQuest () {
		PlayerInformation playerInformation = playerInformationService.getPlayer();
		List<QuestTemplate> questTemplates = questRepository.getQuestTemplates();
		int randomNumber = rand.nextInt(questTemplates.size());
		questRepository.createQuest(playerInformation, questTemplates.get(randomNumber));
	}

	public List<Quest> getNotStartedQuests () {
		PlayerInformation playerInformation = playerInformationService.getPlayer();
		return questRepository.getQuests(playerInformation).stream().filter(quest -> !quest.isStarted()).collect(Collectors.toList());
	}
	public void deleteNotStartedQuests () {
		List<Quest> notStartedQuests = this.getNotStartedQuests();
		for (Quest q : notStartedQuests) {
			this.deleteQuest(q);
		}
	}


	public void createQuestsForFreeKnights (int freeKnightsCount) {
		int newQuestsCount = freeKnightsCount * 2;
		for (int i=0; i < newQuestsCount; i++) {
			createQuest();
		}
	}

	public Quest getQuest (int id) {
		return questRepository.getQuest(id);
	}

	public void deleteQuest (Quest quest) {
		questRepository.deleteQuest(quest);
	}

	public void updateQuest (Quest quest) { questRepository.updateQuest(quest); }

}
