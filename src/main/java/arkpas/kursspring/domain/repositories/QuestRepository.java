package arkpas.kursspring.domain.repositories;

import arkpas.kursspring.domain.PlayerInformation;
import arkpas.kursspring.domain.Quest;
import arkpas.kursspring.domain.QuestTemplate;
import arkpas.kursspring.utils.QuestRarity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


@Repository
public class QuestRepository {

	@PersistenceContext
	EntityManager entityManager;


	@Transactional
	public void createQuest (PlayerInformation playerInformation) {
		Quest quest = new Quest();
		List<QuestTemplate> templates = this.getQuestTemplates();
		quest.setQuestTemplate(templates.get(0));
		quest.setPlayerInformation(playerInformation);
		entityManager.persist(quest);
	}
	@Transactional
	public void createQuestTemplate (String description, QuestRarity rarity, int reward, int length) {
		QuestTemplate questTemplate = new QuestTemplate(description, rarity, reward, length);
		entityManager.persist(questTemplate);
	}

	public List<Quest> getQuests (PlayerInformation playerInformation) {
		return entityManager.createQuery("SELECT q FROM Quest AS q WHERE player_information_id = :playerId", Quest.class)
				.setParameter("playerId", playerInformation.getId())
				.getResultList();
	}

	public List<QuestTemplate> getQuestTemplates () { return entityManager.createQuery("SELECT q FROM QuestTemplate AS q", QuestTemplate.class).getResultList(); }

	public Quest getQuest (int id) {
		return entityManager.find(Quest.class, id);
	}

	@Transactional
	public void deleteQuest (Quest quest) {
		entityManager.remove(quest);
	}

	@Transactional
	public void updateQuest (Quest quest) { entityManager.merge(quest); }

}
