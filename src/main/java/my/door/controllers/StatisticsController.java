package my.door.controllers;

import my.door.dao.CoffeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/statistics")
public class StatisticsController {

    private final CoffeeDAO coffeeDAO;

    @Autowired
    public StatisticsController(CoffeeDAO coffeeDAO) {
        this.coffeeDAO = coffeeDAO;
    }

    //переход на страницу статистики и отображение Map statistics
    @GetMapping("/get")
    public String coffeeStatistics(Model model){
        model.addAttribute("Espresso", coffeeDAO.getStatistics().get("Espresso"));
        model.addAttribute("Cappuccino", coffeeDAO.getStatistics().get("Cappuccino"));
        model.addAttribute("Americano", coffeeDAO.getStatistics().get("Americano"));
        model.addAttribute("Latte", coffeeDAO.getStatistics().get("Latte"));
        model.addAttribute("Coffee", coffeeDAO.getStatistics().get("coffee"));
        model.addAttribute("Water", coffeeDAO.getStatistics().get("water"));
        model.addAttribute("Milk", coffeeDAO.getStatistics().get("milk"));
        model.addAttribute("CoffeeNowInMachine", coffeeDAO.getResources().getCoffee());
        model.addAttribute("WaterNowInMachine", coffeeDAO.getResources().getWater());
        model.addAttribute("MilkNowInMachine", coffeeDAO.getResources().getMilk());
        model.addAttribute("RecipeEspresso", coffeeDAO.listMyCoffee().get(0));
        model.addAttribute("RecipeCappuccino", coffeeDAO.listMyCoffee().get(1));
        model.addAttribute("RecipeAmericano", coffeeDAO.listMyCoffee().get(2));
        model.addAttribute("RecipeLatte", coffeeDAO.listMyCoffee().get(3));

        return "Statistics/myStatistics";
    }
}
