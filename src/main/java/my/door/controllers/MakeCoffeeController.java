package my.door.controllers;

import my.door.dao.CoffeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/make")
public class MakeCoffeeController {

    private final CoffeeDAO coffeeDAO;

    @Autowired
    public MakeCoffeeController(CoffeeDAO coffeeDAO) {
        this.coffeeDAO = coffeeDAO;
    }


    //проверка на достаточность ресурсов для изготовления порции кофе
    @GetMapping("/makeCoffee")
    public String makeCoffee(Model model) {
        if ((coffeeDAO.listMyConsumables()[0] < 15) || (coffeeDAO.listMyConsumables()[1] < 100) || (coffeeDAO.listMyConsumables()[2] < 100)) {
            System.out.println(coffeeDAO.getStatistics().get("coffee"));
            model.addAttribute("allResource", coffeeDAO.listMyConsumables());
            return "redirect:/refill";
        }
        model.addAttribute("allCoffee", coffeeDAO.listMyCoffee());
        return "MakeCoffee/makeCoffee";
    }

    //уменьшение ресурсов кофемашины при изготовленни порции кофе и предача названия вида изготовленого кофе на странице HTML
    @GetMapping("/readyMadeCoffee/{id}")
    public String readyMadeCoffee(@PathVariable("id") int id, Model model) {
        //Поиск id вида кофе
        int position = 0;
        for (int i = 0; i < coffeeDAO.listMyCoffee().size(); i++) {
            if (id == coffeeDAO.listMyCoffee().get(i).getId()) {
                position = i;
            }
        }
        //Уменьшаю количества остатков кофе, воды и молока после каждой порции сделанного кофе.
        coffeeDAO.getResources().setCoffee(coffeeDAO.getResources().getCoffee() - coffeeDAO.listMyCoffee().get(position).getAmountCoffee());
        coffeeDAO.getResources().setWater(coffeeDAO.getResources().getWater() - coffeeDAO.listMyCoffee().get(position).getAmountWater());
        coffeeDAO.getResources().setMilk(coffeeDAO.getResources().getMilk() - coffeeDAO.listMyCoffee().get(position).getAmountMilk());

        //учет в Map statistics количества сделаных видов кофя и расход ресурсов.
        //увеличение на единицу количества произведенных кофе каждого вида
        coffeeDAO.getStatistics().replace(coffeeDAO.listMyCoffee().get(position).getName(), (int) coffeeDAO.getStatistics().get(coffeeDAO.listMyCoffee().get(position).getName()) + 1);
        //увеличение кол-ва израсходоного кофе с каждой порции приготовленого кофе
        coffeeDAO.getStatistics().replace("coffee", (int) coffeeDAO.getStatistics().get("coffee") + (int) coffeeDAO.listMyCoffee().get(position).getAmountCoffee());
        //увеличение кол-ва израсходоного воды с каждой порции приготовленого кофе
        coffeeDAO.getStatistics().replace("water", (int) coffeeDAO.getStatistics().get("water") + (int) coffeeDAO.listMyCoffee().get(position).getAmountWater());
        //увеличение кол-ва израсходоного молока с каждой порции приготовленого кофе
        coffeeDAO.getStatistics().replace("milk", (int) coffeeDAO.getStatistics().get("milk") + (int) coffeeDAO.listMyCoffee().get(position).getAmountMilk());
        //

        //Создание мессаджа для объявление на странице Ready Made Coffee названия напитка.
        model.addAttribute("message", coffeeDAO.searchById(id).getName());
        return "MakeCoffee/readyMadeCoffee";
    }

}
