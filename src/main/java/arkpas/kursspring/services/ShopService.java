package arkpas.kursspring.services;

import arkpas.kursspring.domain.Item;
import arkpas.kursspring.domain.Knight;
import arkpas.kursspring.domain.PlayerInformation;
import arkpas.kursspring.domain.repositories.PlayerInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

    private KnightService knightService;
    private ItemService itemService;
    private PlayerInformationService playerInformationService;
    private EquipmentService equipmentService;

    @Autowired
    public ShopService(KnightService knightService, ItemService itemService, PlayerInformationService playerInformationService, EquipmentService equipmentService) {
        this.knightService = knightService;
        this.itemService = itemService;
        this.playerInformationService = playerInformationService;
        this.equipmentService = equipmentService;
    }

    public String buyItem (int knightId, int itemId) {

        Item item = itemService.getItem(itemId);
        Knight knight = knightService.getKnight(knightId);

        if (knight == null || item == null)
            return "Brak podanego rycerza lub przedmiotu.";
        if (!playerInformationService.isItCurrentPlayer(knight.getPlayerInformation()))
            return "Ten rycerz nie należy do Ciebie!";
        if (!playerInformationService.hasEnoughGold(item.getPrice()))
            return "Masz za mało złota!";
        if (equipmentService.hasItemInEquipment(knight, item))
            return "Masz juz ten przedmiot!";

        PlayerInformation playerInformation = playerInformationService.getPlayer();
        playerInformation.removeGold(item.getPrice());
        equipmentService.addEquipment(knight, item);
        return "Zakupiono przedmiot " + item.getName();



    }
}
