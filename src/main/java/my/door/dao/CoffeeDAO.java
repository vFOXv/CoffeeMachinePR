package my.door.dao;

import my.door.models.KindCoffee;
import my.door.models.Resources;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CoffeeDAO {
    private static int COFFEE_COUNT;
    private ArrayList<KindCoffee> listCoffee;
    private Resources resources;

    public CoffeeDAO() {
        this.listCoffee = new ArrayList<>();
        listCoffee.add(new KindCoffee(++COFFEE_COUNT, "Espresso", 8, 30, 0));
        listCoffee.add(new KindCoffee(++COFFEE_COUNT, "Cappuccino", 15, 100, 50));
        listCoffee.add(new KindCoffee(++COFFEE_COUNT, "Americano", 8, 90, 0));
        listCoffee.add(new KindCoffee(++COFFEE_COUNT, "Latte", 15, 100, 100));

        resources = new Resources(10,200,200);
    }

//    public int getCoffee() {
//        return resources.getCoffee();
//    }
//
//    public void setCoffee(int coffee) {
//        resources.setCoffee(coffee);
//    }
//
//    public int getWater() {
//        return resources.getWater();
//    }
//
//    public void setWater(int water) {
//        resources.setWater(water);
//    }
//
//    public int getMilk() {
//        return resources.getMilk();
//    }
//
//    public void setMilk(int milk) {
//        resources.setMilk(milk);
//    }


    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public ArrayList<KindCoffee> listMyCoffee() {
        return listCoffee;
    }

    public int[] listMyConsumables() {
        int[]refill = {resources.getCoffee(), resources.getWater(), resources.getMilk()};
        return refill;
    }

    public KindCoffee searchById(int id) {
        for (int i = 0; i <= listCoffee.size(); i++) {
            if (id == listCoffee.get(i).getId()) {
                return listCoffee.get(i);
            } else if (id > listCoffee.size()) {
                return null;
            }
        }
        return null;
    }
}
