package my.door.controllers;


import my.door.dao.CoffeeDAO;
import my.door.models.Resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/refill")
public class RefillResourcesController {

    private final CoffeeDAO coffeeDAO;


    @Autowired
    public RefillResourcesController(CoffeeDAO coffeeDAO) {
        this.coffeeDAO = coffeeDAO;
    }

    //переход на страницу пополнения ресурсов кофемашины, через coffeeDAO обращаемся к классу Resources
    //где в полях класса хранятся данные о наличие ресурсов
    //создается НОВЫЙ класс на основе класса(конструктора) coffeeDAO
    @GetMapping()
    public String refillResources(Model model) {
        model.addAttribute("allResources", coffeeDAO.getResources());
        //if ((coffeeDAO.listMyConsumables()[0] < 15) || (coffeeDAO.listMyConsumables()[1] < 100) || (coffeeDAO.listMyConsumables()[2] < 100)) {
        if (coffeeDAO.listMyConsumables()[0] < 15) {
            model.addAttribute("addCoffee", "Add min " + (15 - coffeeDAO.listMyConsumables()[0]) + ", max " + (100 - coffeeDAO.listMyConsumables()[0]) + " coffee!!!");
        }
        if (coffeeDAO.listMyConsumables()[1] < 100) {
            model.addAttribute("addWater", "Add min " + (100 - coffeeDAO.listMyConsumables()[1]) + ", max " + (100 - coffeeDAO.listMyConsumables()[1]) + " water!!!");
        }
        if (coffeeDAO.listMyConsumables()[2] < 100) {
            model.addAttribute("addMilk", "Add min " + (100 - coffeeDAO.listMyConsumables()[2]) + ", max " + (100 - coffeeDAO.listMyConsumables()[2]) + " milk!!!");
        }
        return "RefillResources/refill";
    }

    //добавление ресурсов в кофемашину(кофе, вода, молоко)
    @PostMapping("/add")
    public String addResources(@ModelAttribute("allResources") @Valid Resources myResources, BindingResult bindingResult, Model model) {
        //не работает :(((
        if (bindingResult.hasErrors()) {
            return "RefillResources/refill";
        }


        //myResources создается каждый раз НОВЫЙ, и поэтому поля addCoffee, addWater, addMilk не используються
        // в классе созданном в coffeeDAO при этом PostMapping
        //проверяется добавление ресурсов больше нуля, и <= ли свободного размера танка

        //кол-во добавляемого ресурса
        int boxAdd = myResources.getAddCoffee();
        //кол-во ресурса которое уже было в танке
        int boxBeen = coffeeDAO.getResources().getCoffee();
        //кол-во свободного пространства в баке после пополнения
        int boxVoid = 100 - boxBeen - boxAdd;
        if (boxAdd >= 0 && boxAdd <= 100 - boxBeen) {
            coffeeDAO.getResources().setCoffee(boxBeen + boxAdd);
        } else if (boxAdd >= 0 && boxAdd > 100 - boxBeen) {
            //заполняем весь объем танка
            coffeeDAO.getResources().setCoffee(100);
            model.addAttribute("lostCoffee", "You lost " + (boxAdd - (100 - boxBeen) + " coffee! Because box = 100!"));
            return "Start/choiceAction";
        }
//        else if (myResources.getAddCoffee() < 0) {
//            Model model1;
//            model1 = model.addAttribute("negativeNumber", "You introduced negative number!!!");
//            refillResources(model1);
//            //return "RefillResources/refill";
//        }

        boxAdd = myResources.getAddWater();
        boxBeen = coffeeDAO.getResources().getWater();
        if (boxAdd >= 0 && boxAdd <= 500 - boxBeen) {
            coffeeDAO.getResources().setWater(boxBeen + boxAdd);
        } else if (boxAdd >= 0 && boxAdd > 500 - boxBeen) {
            //заполняем весь объем танка
            coffeeDAO.getResources().setWater(500);
            model.addAttribute("lostWater", "You lost " + (boxAdd - (500 - boxBeen) + " water! Because box = 500!"));
            return "Start/choiceAction";
        }
//        else if (myResources.getAddWater() < 0) {
//            Model model1;
//            model1 = model.addAttribute("negativeNumber", "You introduced negative number!!!");
//            refillResources(model1);
//            //return "RefillResources/refill";
//        }

        boxAdd = myResources.getAddMilk();
        boxBeen = coffeeDAO.getResources().getMilk();
        if (boxAdd >= 0 && boxAdd <= 500 - boxBeen) {
            coffeeDAO.getResources().setMilk(boxBeen + boxAdd);
        } else if (boxAdd >= 0 && boxAdd > 500 - boxBeen) {
            //заполняем весь объем танка
            coffeeDAO.getResources().setMilk(500);
            model.addAttribute("lostMilk", "You lost " + (boxAdd - (500 - boxBeen) + " milk! Because box = 500!"));
            return "Start/choiceAction";
        }
        return "redirect:/";
    }

    //Представление рецепта вида кофе
    @GetMapping("/{id}")
    public String showRecipe(@PathVariable("id") int id, Model model) {
        //Поиск id вида кофе
        int position = 0;
        for (int i = 0; i < coffeeDAO.listMyCoffee().size(); i++) {
            if (id == coffeeDAO.listMyCoffee().get(i).getId()) {
                position = i;
            }
        }
        model.addAttribute("recipeCoffee", coffeeDAO.listMyCoffee().get(position).getAmountCoffee());
        model.addAttribute("recipeWater", coffeeDAO.listMyCoffee().get(position).getAmountWater());
        model.addAttribute("recipeMilk", coffeeDAO.listMyCoffee().get(position).getAmountMilk());
        model.addAttribute("message", "Recipe kind coffee " + coffeeDAO.listMyCoffee().get(position).getName() + ".");

        return "RefillResources/showRecipe";
    }
}
