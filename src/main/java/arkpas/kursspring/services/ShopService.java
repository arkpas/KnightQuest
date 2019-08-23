package arkpas.kursspring.services;

import arkpas.kursspring.domain.Item;
import arkpas.kursspring.domain.Knight;
import arkpas.kursspring.domain.PlayerInformation;
import arkpas.kursspring.domain.repositories.ItemRepository;
import arkpas.kursspring.domain.repositories.PlayerInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

    private KnightService knightService;
    private ItemRepository itemRepository;
    private PlayerInformationRepository playerInformationRepository;

    private int playerId = 1;

    @Autowired
    public ShopService(KnightService knightService, ItemRepository itemRepository, PlayerInformationRepository playerInformationRepository) {
        this.knightService = knightService;
        this.itemRepository = itemRepository;
        this.playerInformationRepository = playerInformationRepository;
    }

    public List<Item> getItems () {
        return itemRepository.getItems();
    }

    public String buyItem (int knightId, int itemId) {
        Item item = itemRepository.getItem(itemId);
        Knight knight = knightService.getKnight(knightId);
        PlayerInformation playerInformation = playerInformationRepository.getPlayer(playerId);
        String message = "";
        if (knight != null && item != null) {
            if (item.getPrice() <= playerInformation.getGold()) {
                if (knight.addItem(item)) {
                    playerInformation.removeGold(item.getPrice());
                    message = "Zakupiono przedmiot " + item.getName() + ".";
                    knightService.updateKnight(knight);
                }
                else { message = "Masz juz ten przedmiot!"; }
            }
            else { message = "Masz za mało złota!"; }
        }

        return message;

    }
}
