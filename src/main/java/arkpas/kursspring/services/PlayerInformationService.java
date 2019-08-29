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

    public String createPlayer (String username, String password) {
        if (playerInformationRepository.getPlayer(username) != null) {
            return "Nazwa użytkownika jest już zajęta.";
        }
        else {
            playerInformationRepository.createPlayer(username, password);
        }
        return null;
    }

    public PlayerInformation getPlayer (int id) {
        return playerInformationRepository.getPlayer(id);
    }

    public PlayerInformation getPlayer (String name) {
        return playerInformationRepository.getPlayer(name);
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
