package arkpas.kursspring.services;

import arkpas.kursspring.domain.Item;
import arkpas.kursspring.domain.Knight;
import arkpas.kursspring.domain.PlayerInformation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShopServiceTest {

    @Mock
    private KnightService knightService;
    @Mock
    private ItemService itemService;
    @Mock
    private PlayerInformationService playerInformationService;
    @Mock
    private EquipmentService equipmentService;

    private ShopService shopService;

    @Before
    public void setUp () {
        shopService = new ShopService(knightService, itemService, playerInformationService, equipmentService);
    }

    @Test
    public void buyItemWithNonExistentKnightAndItem () {
        assertEquals("Brak podanego rycerza lub przedmiotu.", shopService.buyItem(99,99));
    }

    @Test
    public void buyItemForNotYourKnight () {
        Knight knight = new Knight();
        Item item = new Item();

        when(itemService.getItem(anyInt())).thenReturn(item);
        when(knightService.getKnight(anyInt())).thenReturn(knight);
        when(playerInformationService.isItCurrentPlayer(any())).thenReturn(false);

        assertEquals("Ten rycerz nie należy do Ciebie!", shopService.buyItem(99,99));
    }

    @Test
    public void buyItemWithNotEnoughGold () {
        Knight knight = new Knight();
        Item item = new Item();
        item.setPrice(100);

        when(itemService.getItem(anyInt())).thenReturn(item);
        when(knightService.getKnight(anyInt())).thenReturn(knight);
        when(playerInformationService.isItCurrentPlayer(any())).thenReturn(true);

        assertEquals("Masz za mało złota!", shopService.buyItem(99,99));
    }

    @Test
    public void buyDuplicateItem() {

        Knight knight = new Knight();
        Item item = new Item();
        item.setPrice(0);


        when(itemService.getItem(anyInt())).thenReturn(item);
        when(knightService.getKnight(anyInt())).thenReturn(knight);
        when(playerInformationService.isItCurrentPlayer(any())).thenReturn(true);
        when(playerInformationService.hasEnoughGold(anyInt())).thenReturn(true);
        when(equipmentService.hasItemInEquipment(any(), any())).thenReturn(true);

        assertEquals("Masz juz ten przedmiot!", shopService.buyItem(99,99));
    }

    @Test
    public void buyItemShouldSuccess() {

        Knight knight = new Knight();
        Item item = new Item();
        item.setPrice(0);
        item.setName("test");
        PlayerInformation playerInformation = new PlayerInformation();
        when(playerInformationService.getPlayer()).thenReturn(playerInformation);
        when(itemService.getItem(anyInt())).thenReturn(item);
        when(knightService.getKnight(anyInt())).thenReturn(knight);
        when(playerInformationService.isItCurrentPlayer(any())).thenReturn(true);
        when(playerInformationService.hasEnoughGold(anyInt())).thenReturn(true);
        when(equipmentService.hasItemInEquipment(any(), any())).thenReturn(false);

        assertEquals("Zakupiono przedmiot " + item.getName(), shopService.buyItem(99,99));
    }


}
