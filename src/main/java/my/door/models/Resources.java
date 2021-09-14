package my.door.models;

import javax.validation.constraints.Min;

public class Resources {

    private int coffeeInMachine;
    private int waterInMachine;
    private int milkInMachine;

    @Min(value=0, message = "Min amount = 0!!!")
    private int addCoffee;
    @Min(value=0, message = "Min amount = 0!!!")
    private int addWater;
    @Min(value=0, message = "Min amount = 0!!!")
    private int addMilk;


    public Resources() {    }

    public Resources(int coffee, int water, int milk) {
        this.coffeeInMachine = coffee;
        this.waterInMachine = water;
        this.milkInMachine = milk;
    }

    public int getCoffee() {
        return coffeeInMachine;
    }

    public void setCoffee(int coffee) {
        this.coffeeInMachine = coffee;
    }

    public int getWater() {
        return waterInMachine;
    }

    public void setWater(int water) {
        this.waterInMachine = water;
    }

    public int getMilk() {
        return milkInMachine;
    }

    public void setMilk(int milk) {
        this.milkInMachine = milk;
    }

    public int getAddCoffee() {
        return addCoffee;
    }

    public void setAddCoffee(int addCoffee) {
        this.addCoffee = addCoffee;
    }

    public int getAddWater() {
        return addWater;
    }

    public void setAddWater(int addWater) {
        this.addWater = addWater;
    }

    public int getAddMilk() {
        return addMilk;
    }

    public void setAddMilk(int addMilk) {
        this.addMilk = addMilk;
    }

    @Override
    public String toString() {
        return "Resources{" +
                "coffeeInMachine=" + coffeeInMachine +
                ", waterInMachine=" + waterInMachine +
                ", milkInMachine=" + milkInMachine +
                ", addCoffee=" + addCoffee +
                ", addWater=" + addWater +
                ", addMilk=" + addMilk +
                '}'+"\n";
    }
}
