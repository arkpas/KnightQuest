package arkpas.kursspring.domain;

import javax.persistence.*;

@Entity
public class Equipment {

    //fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "knight_id")
    private Knight knight;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private Item item;

    //constructors

    public Equipment () {}

    //getters

    public int getId() {
        return id;
    }
    public Knight getKnight() {
        return knight;
    }
    public Item getItem() {
        return item;
    }


    //setters

    public void setId (int id) { this.id = id; }
    public void setKnight(Knight knight) {
        this.knight = knight;
    }
    public void setItem(Item item) {
        this.item = item;
    }
}
