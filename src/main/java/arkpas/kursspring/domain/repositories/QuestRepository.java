package arkpas.kursspring.domain.repositories;

import arkpas.kursspring.domain.Quest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.*;


@Repository
public class QuestRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	private final Random rand = new Random();
	private final int minQuestLength = 10;
	private final int maxQuestLength = 20; // + minQuestLength
	private final int minQuestGoldReward = 50;
	private final int maxQuestGoldReward = 200; // + minQuestGoldReward

	@Transactional
	public void createQuest (String description, int goldReward, int length) {
		Quest quest = new Quest(description, goldReward, length);
		entityManager.persist(quest);

	}
	
	@Scheduled (fixedDelayString = "${questCreationDelay}")
	@Transactional
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
	
	public List<Quest> getQuests () { return entityManager.createQuery("SELECT q FROM Quest AS q", Quest.class).getResultList(); }

	public Quest getQuest (int id) {
		return entityManager.find(Quest.class, id);
	}

	@Transactional
	public void deleteQuest (Quest quest) {
		entityManager.remove(quest);
	}

	@Transactional
	public void updateQuest (Quest quest) {
		entityManager.merge(quest);
	}
}
