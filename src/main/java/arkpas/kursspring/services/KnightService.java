package arkpas.kursspring.services;

import arkpas.kursspring.domain.Knight;
import arkpas.kursspring.domain.PlayerInformation;
import arkpas.kursspring.domain.Quest;
import arkpas.kursspring.domain.repositories.KnightRepository;
import arkpas.kursspring.domain.repositories.PlayerInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnightService {
	

	private KnightRepository knightRepository;
	private PlayerInformationRepository playerInformationRepository;
	private QuestService questService;

	private int playerId = 1;

	@Autowired
	public KnightService(KnightRepository knightRepository, PlayerInformationRepository playerInformationRepository, QuestService questService) {
		this.knightRepository = knightRepository;
		this.playerInformationRepository = playerInformationRepository;
		this.questService = questService;
	}

	public List<Knight> getKnightList () {
		return knightRepository.getKnights();
	}

	public void saveKnight(Knight knight) {
		knightRepository.createKnight(knight);
	}

	public Knight getKnight (int id) {
		return knightRepository.getKnight(id);
	}

	public void deleteKnight (Knight knight) {
		knightRepository.deleteKnight(knight);
	}

	public void updateKnight (Knight knight) {
		knightRepository.updateKnight(knight);
	}

	private int collectRewards () {
		List<Knight> knights = getKnightList();
		int gold = knights.stream().filter(knight -> knight.getQuest()!=null).filter(knight -> knight.getQuest().isCompleted()).mapToInt(knight -> knight.getQuest().getGoldReward()).sum();
		knights.stream().filter(knight -> knight.getQuest()!=null).filter(knight -> knight.getQuest().isCompleted()).forEach(this::endQuest);
		return gold;
	}

	public void getMyGold () {
		PlayerInformation playerInformation = playerInformationRepository.getPlayer(playerId);
		playerInformation.addGold(this.collectRewards());
		playerInformationRepository.updatePlayer(playerInformation);
	}

	private void endQuest (Knight knight) {
		Quest quest = knight.getQuest();
		knight.removeQuest();
		questService.deleteQuest(quest);
		this.updateKnight(knight);
	}

	public void assignQuest (int knightId, int questId) {
		Knight knight = this.getKnight(knightId);
		Quest quest = questService.getQuest(questId);
		quest.setLength(quest.getLength() - knight.getTimeReduction());
		knight.setQuest(quest);
		this.updateKnight(knight);
		questService.updateQuest(quest);
	}
}
