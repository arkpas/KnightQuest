package arkpas.kursspring.controllers;

import arkpas.kursspring.services.KnightService;
import arkpas.kursspring.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ShopController {

    @Autowired
    ShopService shopService;

    @Autowired
    KnightService knightService;

    @RequestMapping("/shop/{knightId}")
    public String goToShop (@PathVariable("knightId") int knightId, Model model) {
        model.addAttribute("knightId", knightId);
        model.addAttribute("itemList", shopService.getItems());
        model.asMap().values().stream().forEach(object -> System.out.println(object));
        return "shop";
    }
    @RequestMapping("/buyItem/{knightId}/{itemId}")
    public String buyItem (@PathVariable("knightId") int knightId, @PathVariable("itemId") int itemId, RedirectAttributes redirectAttributes) {
        String message = shopService.buyItem(knightId, itemId);
        redirectAttributes.addFlashAttribute("message", message);

        return "redirect:/shop/" + knightId;
    }
}
