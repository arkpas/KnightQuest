package arkpas.kursspring.domain;

public class Item {

    private int id;
    private String name;
    private int timeReduction;
    private int price;

    public Item (int id, String name, int timeReduction, int price) {
        this.id = id;
        this.name = name;
        this.timeReduction = timeReduction;
        this.price = price;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getTimeReduction() {
        return timeReduction;
    }
    public int getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setTimeReduction(int timeReduction) {
        this.timeReduction = timeReduction;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString () {
        return name + " (redukcja: " + timeReduction + ") (cena: " + price + ")";
    }
}
