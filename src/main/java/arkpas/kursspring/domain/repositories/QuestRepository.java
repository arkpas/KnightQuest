package arkpas.kursspring.domain.repositories;

import arkpas.kursspring.domain.Quest;
import arkpas.kursspring.utils.Ids;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;


@Repository
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class QuestRepository {

	Map<Integer, Quest> quests = new HashMap<>();
	
	private final Random rand = new Random();
	private final int minQuestLength = 10;
	private final int maxQuestLength = 20; // + minQuestLength
	private final int minQuestGoldReward = 50;
	private final int maxQuestGoldReward = 200; // + minQuestGoldReward


	public void createQuest (String description, int goldReward, int length) {
		Quest quest = new Quest(description, goldReward, length);
		quest.setId(Ids.getNextId(quests.keySet()));
		quests.put(quest.getId(), quest);
	}
	
	@Scheduled (fixedDelayString = "${questCreationDelay}")
	public void createRandomQuest () {
		List<String> descriptions = new ArrayList<>();
		
		descriptions.add("Uratuj ksiezniczke");
		descriptions.add("Zabij smoka");
		descriptions.add("Wez udzial w turnieju");
		descriptions.add("Zaatakuj wrogi zamek");
		
		String description = descriptions.get(rand.nextInt(descriptions.size()));
		int randomGoldReward = rand.nextInt(maxQuestGoldReward) + minQuestGoldReward;
		int randomLength = rand.nextInt(maxQuestLength) + minQuestLength;

		createQuest(description, randomGoldReward, randomLength);
		
	}
	
	public Collection<Quest> getQuests () { return quests.values(); }
	
	public void deleteQuest (int id) {
		quests.remove(id);
	}

	public Quest getQuest (int id) {
		return quests.get(id);
	}

 	@PostConstruct
	public void postConstruct () {
		createRandomQuest();
		createRandomQuest();
	}
	
	@Override
	public String toString () {
		return quests.values().stream().map(Quest::toString).collect(Collectors.joining("\n"));
	}
}
