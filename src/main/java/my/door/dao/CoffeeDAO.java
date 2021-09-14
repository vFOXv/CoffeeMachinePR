package my.door.dao;

import my.door.models.KindCoffee;
import my.door.models.Resources;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component
public class CoffeeDAO {
    private static int COFFEE_COUNT;
    private ArrayList<KindCoffee> listCoffee;
    private Resources resources;
    private HashMap<String, Integer> statistics;

    public CoffeeDAO() {
        this.listCoffee = new ArrayList<>();
        listCoffee.add(new KindCoffee(++COFFEE_COUNT, "Espresso", 8, 30, 0));
        listCoffee.add(new KindCoffee(++COFFEE_COUNT, "Cappuccino", 15, 100, 50));
        listCoffee.add(new KindCoffee(++COFFEE_COUNT, "Americano", 8, 90, 0));
        listCoffee.add(new KindCoffee(++COFFEE_COUNT, "Latte", 15, 100, 100));

        resources = new Resources(10, 200, 200);

        statistics = new HashMap();
        statistics.put("Espresso",0);
        statistics.put("Cappuccino",0);
        statistics.put("Americano",0);
        statistics.put("Latte",0);
        statistics.put("coffee",0);
        statistics.put("water",0);
        statistics.put("milk",0);
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    //получение списка видов кофе
    public ArrayList<KindCoffee> listMyCoffee() {
        return listCoffee;
    }

    //массив со значениями остатков ресурсов кофемашины
    public int[] listMyConsumables() {
        int[] refill = {resources.getCoffee(), resources.getWater(), resources.getMilk()};
        return refill;
    }

    //поиск вида кофе по id
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

    //получение Map со статистикой
    public HashMap getStatistics() {
        return statistics;
    }
}
