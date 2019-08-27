package arkpas.kursspring.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Quest {

    //fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private QuestTemplate questTemplate;

    @OneToOne
    private PlayerInformation playerInformation;

    private int timeReduction;
    private boolean started = false;
    private boolean completed = false;
    private LocalDateTime startDate;

    //constructors

    public Quest() {
    }

    //getters

    public int getId() {
        return id;
    }
    public QuestTemplate getQuestTemplate() {
        return questTemplate;
    }
    public PlayerInformation getPlayerInformation() {
        return playerInformation;
    }
    public int getTimeReduction() {
        return timeReduction;
    }
    public boolean isStarted() { return started; }
    public LocalDateTime getStartDate() {
        return startDate;
    }


    //setters

    public void setStarted(boolean started) {
        this.started = started;
        if (started)
            startDate = LocalDateTime.now();
    }

    public void setQuestTemplate(QuestTemplate questTemplate) {
        this.questTemplate = questTemplate;
    }
    public void setPlayerInformation(PlayerInformation playerInformation) {
        this.playerInformation = playerInformation;
    }
    public void setTimeReduction(int timeReduction) {
        this.timeReduction = timeReduction;
    }

    //methods

    public boolean isCompleted() {
        if (completed)
            return true;

        if (started) {
            LocalDateTime now = LocalDateTime.now();
            completed = startDate.plusSeconds(this.getLength() - timeReduction).isBefore(now);
        }
        return completed;

    }

    public int getGoldReward() { return questTemplate.getGoldReward(); }
    public int getLength() { return questTemplate.getLength(); }
}
