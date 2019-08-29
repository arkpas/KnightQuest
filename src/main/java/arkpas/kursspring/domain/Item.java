package arkpas.kursspring.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Item {

    //fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int timeReduction;
    private int price;

    @OneToMany (mappedBy = "item")
    private Set<Equipment> equipmentSet = new HashSet<>();

    //constructors

    public Item() {}

    //getters

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
    public Set<Equipment> getEquipmentSet() {
        return equipmentSet;
    }

    //setters

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
    public void setEquipmentSet(Set<Equipment> equipmentSet) {
        this.equipmentSet = equipmentSet;
    }

    //methods

    @Override
    public String toString () {
        return name + " (redukcja: " + timeReduction + "s) (cena: " + price + ")";
    }



}
