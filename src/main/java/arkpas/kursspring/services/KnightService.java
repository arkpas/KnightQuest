package arkpas.kursspring.services;

import arkpas.kursspring.domain.Knight;
import arkpas.kursspring.domain.PlayerInformation;
import arkpas.kursspring.domain.repositories.KnightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KnightService {
	
	@Autowired
	private KnightRepository knightRepository;
	@Autowired
	private PlayerInformation playerInformation;

	public List<Knight> getKnightList () {
		return new ArrayList<>(knightRepository.getKnights());
	}

	public void saveKnight(Knight knight) {
		knightRepository.createKnight(knight);
	}

	public Knight getKnight (int id) {
		return knightRepository.getKnight(id);
	}

	public void deleteKnight (int id) {
		knightRepository.deleteKnight(id);
	}

	public void updateKnight (Knight knight) {
		knightRepository.updateKnight(knight.getId(), knight);
	}

	private int collectRewards () {
		List<Knight> knights = getKnightList();
		int gold = knights.stream().filter(knight -> knight.getQuest()!=null).filter(knight -> knight.getQuest().isCompleted()).mapToInt(knight -> knight.getQuest().getGoldReward()).sum();
		knights.stream().filter(knight -> knight.getQuest()!=null).filter(knight -> knight.getQuest().isCompleted()).forEach(Knight::endQuest);
		return gold;
	}

	public void getMyGold () {
		playerInformation.addGold(collectRewards());
	}
}
