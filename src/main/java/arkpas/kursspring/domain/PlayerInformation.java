package arkpas.kursspring.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PlayerInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;
    private int gold = 1000;
    private boolean active;

    PlayerInformation() {}

    public PlayerInformation(String username, String password) {
        this.username = username;
        this.password = password;
        this.active = true;
    }

    public int getGold() { return gold; }

    public void addGold(int gold) { this.gold += gold; }

    public void removeGold(int gold) { this.gold -= gold; }
}
