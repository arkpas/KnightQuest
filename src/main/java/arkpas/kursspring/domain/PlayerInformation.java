package arkpas.kursspring.domain;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class PlayerInformation {

    //fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;
    private int gold = 1000;
    private boolean active;

    @OneToMany(mappedBy = "playerInformation", cascade = CascadeType.ALL)
    private Set<Knight> knights = new HashSet<>();

    //constructors

    public PlayerInformation() {}

    public PlayerInformation(String username, String password) {
        this.username = username;
        this.password = password;
        this.active = true;
    }

    //getters

    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public int getGold() { return gold; }
    public boolean isActive() {
        return active;
    }
    public Set<Knight> getKnights () {
        return knights;
    }

    //setters

    public void setId(int id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setGold(int gold) {
        this.gold = gold;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public void setKnights(Set<Knight> knights) {
        this.knights = knights;
    }

    //methods

    public void addGold(int gold) {
        this.gold += gold;
    }

    public void removeGold(int gold) {
        this.gold -= gold;
    }

}
