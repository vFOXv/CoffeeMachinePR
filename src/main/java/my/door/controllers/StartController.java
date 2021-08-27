package my.door.controllers;

import my.door.dao.CoffeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartController {
    private final CoffeeDAO coffeeDAO;

    @Autowired
    public StartController(CoffeeDAO coffeeDAO) {
        this.coffeeDAO = coffeeDAO;
    }

    //Стартавая страница со списком действий
    @GetMapping("/")
    public String index(){
        return "Start/choiceAction";
    }
}
