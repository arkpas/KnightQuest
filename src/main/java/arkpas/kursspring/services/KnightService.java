package arkpas.kursspring.services;

import arkpas.kursspring.domain.Knight;
import arkpas.kursspring.domain.PlayerInformation;
import arkpas.kursspring.domain.Quest;
import arkpas.kursspring.domain.repositories.KnightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KnightService {
	

	private KnightRepository knightRepository;
	private PlayerInformationService playerInformationService;
	private QuestService questService;

	@Autowired
	public KnightService(KnightRepository knightRepository, PlayerInformationService playerInformationService, QuestService questService) {
		this.knightRepository = knightRepository;
		this.playerInformationService = playerInformationService;
		this.questService = questService;
	}

	public List<Knight> getKnightList () {
		PlayerInformation playerInformation = playerInformationService.getPlayer();
		return knightRepository.getKnights(playerInformation.getId());
	}

	public void saveKnight(Knight knight) {
		PlayerInformation playerInformation = playerInformationService.getPlayer();

		knight.setPlayerInformation(playerInformation);
		knightRepository.createKnight(knight);
	}

	public Knight getKnight (int id) {
		return knightRepository.getKnight(id);
	}

	public void updateKnight (Knight knight) {
		knightRepository.updateKnight(knight);
	}

	public void deleteKnight (Knight knight) {
		if (playerInformationService.isItCurrentPlayer(knight.getPlayerInformation()))
			knightRepository.deleteKnight(knight);
	}

	private int collectRewards () {
		List<Knight> knights = this.getKnightList();
		int gold = knights.stream().filter(knight -> knight.getQuest()!=null).filter(knight -> knight.getQuest().isCompleted()).mapToInt(knight -> knight.getQuest().getGoldReward()).sum();
		knights.stream().filter(knight -> knight.getQuest()!=null).filter(knight -> knight.getQuest().isCompleted()).forEach(this::endQuest);
		return gold;
	}

	public void getMyGold () {
		PlayerInformation playerInformation = playerInformationService.getPlayer();
		playerInformation.addGold(this.collectRewards());
		playerInformationService.updatePlayer(playerInformation);
	}

	private void endQuest (Knight knight) {
		Quest quest = knight.getQuest();
		knight.removeQuest();
		knight.addExperience(quest.getExpReward());

		questService.deleteQuest(quest);
		this.updateKnight(knight);
	}

	public List<Knight> getFreeKnights () {
		PlayerInformation playerInformation = playerInformationService.getPlayer();
		List<Knight> freeKnights = knightRepository.getKnights(playerInformation.getId());
		freeKnights = freeKnights.stream().filter(knight -> knight.getQuest() == null).collect(Collectors.toList());
		return freeKnights;
	}

	public void assignQuest (int knightId, int questId) {
		Knight knight = this.getKnight(knightId);

		if (playerInformationService.isItCurrentPlayer(knight.getPlayerInformation()) && knight.getQuest() == null) {
			Quest quest = questService.getQuest(questId);

			quest.setTimeReduction(knight.getTimeReduction());
			knight.setQuest(quest);

			this.updateKnight(knight);
			questService.updateQuest(quest);
		}
	}
}
