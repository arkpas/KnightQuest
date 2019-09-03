package arkpas.kursspring.domain;

import arkpas.kursspring.utils.QuestRarity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;


@Entity
public class QuestTemplate {

	//fields

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String description = "";
	private QuestRarity rarity;
	private int goldReward;
	private int expReward;
	private int length; //in seconds

	// constructors

	public QuestTemplate() {}

	public QuestTemplate(String description, QuestRarity rarity, int goldReward, int expReward, int length) {
		this.description = description;
		this.rarity = rarity;
		this.goldReward = goldReward;
		this.expReward = expReward;
		this.length = length;
	}

	//getters

	public int getId() { return id; }
	public String getDescription() { return description; }
	public QuestRarity getRarity() {
		return rarity;
	}
	public int getGoldReward() {
		return goldReward;
	}
	public int getExpReward() {
		return expReward;
	}
	public int getLength() {
		return length;
	}

	//setters

	public void setId (int id) { this.id = id; }
	public void setDescription(String description) { this.description = description; }
	public void setRarity(QuestRarity rarity) {
		this.rarity = rarity;
	}
	public void setGoldReward(int goldReward) {
		this.goldReward = goldReward;
	}
	public void setExpReward(int expReward) {
		this.expReward = expReward;
	}
	public void setLength(int length) {
		this.length = length;
	}

	//methods

	@Override
	public String toString () {
		return description;
	}

}
