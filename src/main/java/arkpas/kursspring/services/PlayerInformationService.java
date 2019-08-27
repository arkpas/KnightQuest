package arkpas.kursspring.services;

import arkpas.kursspring.domain.PlayerInformation;
import arkpas.kursspring.domain.repositories.PlayerInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PlayerInformationService {

    private PlayerInformationRepository playerInformationRepository;

    @Autowired
    public PlayerInformationService(PlayerInformationRepository playerInformationRepository) {
        this.playerInformationRepository = playerInformationRepository;
    }

    public void createPlayer (String username, String password) {
        playerInformationRepository.createPlayer(username, password);
    }

    public PlayerInformation getPlayer () {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return playerInformationRepository.getPlayer(name);
    }

    public boolean hasEnoughGold (int price) {
        PlayerInformation player = this.getPlayer();
        return price <= player.getGold();
    }

    public boolean isItCurrentPlayer (PlayerInformation player) {
        PlayerInformation currentPlayer = this.getPlayer();
        return player.getId() == currentPlayer.getId();
    }

    public void updatePlayer (PlayerInformation playerInformation) {
        playerInformationRepository.updatePlayer(playerInformation);
    }

}
