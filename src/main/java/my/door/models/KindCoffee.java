package my.door.models;

public class KindCoffee {

    private int id;
    private String name;
    private int amountCoffee;
    private int amountWater;
    private int amountMilk;

    public KindCoffee() {    }

    public KindCoffee(int id, String name, int amountCoffee, int amountWater, int amountMilk) {
        this.id = id;
        this.name = name;
        this.amountCoffee = amountCoffee;
        this.amountWater = amountWater;
        this.amountMilk = amountMilk;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmountCoffee() {
        return amountCoffee;
    }

    public void setAmountCoffee(int amountCoffee) {
        this.amountCoffee = amountCoffee;
    }

    public int getAmountWater() {
        return amountWater;
    }

    public void setAmountWater(int amountWater) {
        this.amountWater = amountWater;
    }

    public int getAmountMilk() {
        return amountMilk;
    }

    public void setAmountMilk(int amountMilk) {
        this.amountMilk = amountMilk;
    }

    @Override
    public String toString() {
        return "KindCoffee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amountCoffee=" + amountCoffee +
                ", amountWater=" + amountWater +
                ", amountMilk=" + amountMilk +
                '}' + "\n";
    }
}
