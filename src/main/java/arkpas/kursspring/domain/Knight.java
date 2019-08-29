package arkpas.kursspring.domain;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Knight {

	//fields

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull(message = "Name must not be null!")
	@Size(min = 3, message = "Name length must be at least 3 characters!")
	private String name;

	@NotNull (message = "Age must not be null!")
	@Min(value = 18, message = "Knight has to be at least 18 years old!")
	@Max(value = 50, message = "This knight is too old! Maximum age is 50!")
	private int age;

	private int level = 1;

	@OneToOne
	private Quest quest;

	@OneToMany(mappedBy = "knight")
	private Set<Equipment> equipmentSet = new HashSet<>();

	@ManyToOne
	@JoinColumn (name = "playerId")
	private PlayerInformation playerInformation;

	// constructors

	public Knight () {}

	public Knight (String name, int age) {
		this.name = name;
		this.age = age;
	}

	//getters

	public int getId() { return id; }
	public String getName () { return name; }
	public int getAge () { return age; }
	public Quest getQuest () { return quest; }
	public int getLevel () { return level; }
	public Set<Equipment> getEquipmentSet () { return equipmentSet; }
	public PlayerInformation getPlayerInformation() {
		return playerInformation;
	}

	//setters

	public void setId (int id) { this.id = id; }
	public void setName (String name) { this.name = name; }
	public void setAge (int age) { this.age = age; }
	public void setLevel (int level) { this.level = level; }
	public void setEquipmentSet(Set<Equipment> equipmentSet) {
		this.equipmentSet = equipmentSet;
	}
	public void setPlayerInformation(PlayerInformation playerInformation) {
		this.playerInformation = playerInformation;
	}

	//methods

	public int getTimeReduction () {
		return equipmentSet.stream().mapToInt(equipment -> equipment.getItem().getTimeReduction()).sum();
	}

	public void setQuest (Quest quest) {
		this.quest = quest;
		quest.setStarted(true);
	}

	public void removeQuest () {
		quest = null;
	}

	@Override
	public String toString () {
		return name + "(" + age + ")[" + quest + "]"; 
	}
}
