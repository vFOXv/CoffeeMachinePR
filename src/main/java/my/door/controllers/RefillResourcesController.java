package my.door.controllers;

import jakarta.validation.Valid;
import my.door.dao.CoffeeDAO;
import my.door.models.Resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/refill")
public class RefillResourcesController {

    private final CoffeeDAO coffeeDAO;


    @Autowired
    public RefillResourcesController(CoffeeDAO coffeeDAO) {
        this.coffeeDAO = coffeeDAO;
    }

    //переход на страницу пополнения ресурсов кофемашины, через coffeeDAO обращаемся к кслассу Resources
    //где в полях класса храняться данные о наличее ресурсов
    //Создаеться НОВЫЙ класс на основе класса(конструктора) coffeeDAO
    @GetMapping()
    public String refillResources(Model model) {
        model.addAttribute("allResources",coffeeDAO.getResources());
        System.out.println("GET");
        System.out.println(coffeeDAO.getResources());
        return "RefillResources/refill";
    }

    //добавление резурсов в кофемашину(кофе, вода, молоко)
    @PostMapping("/add")
    public String addResources(@ModelAttribute("myResources") @Valid Resources myResources, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "RefillResources/refill";
        }

        //myResources создаеться каждый раз НОВЫЙ, и поэтому поля addCoffee, addWater, addMilk не используються
        // в классе созданом в coffeeDAO при этом PostMapping
        coffeeDAO.getResources().setCoffee(coffeeDAO.getResources().getCoffee() + myResources.getAddCoffee());
        coffeeDAO.getResources().setWater(coffeeDAO.getResources().getWater() + myResources.getAddWater());
        coffeeDAO.getResources().setMilk(coffeeDAO.getResources().getMilk() + myResources.getAddMilk());

        return "redirect:/";
    }
}
