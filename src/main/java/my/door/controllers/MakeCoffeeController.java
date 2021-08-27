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
            if(id==coffeeDAO.listMyCoffee().get(i).getId()){
                position=i;
            }
        }
        //Уменьшаю количества остатков кофе, воды и молока после каждой порции сделанного кофе.
        coffeeDAO.getResources().setCoffee(coffeeDAO.getResources().getCoffee()-coffeeDAO.listMyCoffee().get(position).getAmountCoffee());
        coffeeDAO.getResources().setWater(coffeeDAO.getResources().getWater()-coffeeDAO.listMyCoffee().get(position).getAmountWater());
        coffeeDAO.getResources().setMilk(coffeeDAO.getResources().getMilk()-coffeeDAO.listMyCoffee().get(position).getAmountMilk());

        //Создание мессаджа для объявление на странице Ready Made Coffee названия напитка.
        model.addAttribute("message", coffeeDAO.searchById(id).getName());
        return "MakeCoffee/readyMadeCoffee";
    }

}
