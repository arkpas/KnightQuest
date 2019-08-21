package arkpas.kursspring.domain;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Knight {

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

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "equipement",
			joinColumns = @JoinColumn (name = "knightId"),
			inverseJoinColumns = @JoinColumn (name = "itemId")
	)
	private Set<Item> items = new HashSet<>();


	public Knight () {}

	public Knight (String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public void setQuest (Quest quest) {
		this.quest = quest;
		quest.setStarted(true);
	}
	public void setName (String name) { this.name = name; }
	public void setAge (int age) { this.age = age; }
	public void setLevel (int level) { this.level = level; }
	public void setId (int id) { this.id = id; }
	public boolean addItem (Item item) { return items.add(item); }
	
	public String getName () { return name; }
	public int getAge () { return age; }
	public Quest getQuest () { return quest; }
	public int getLevel () { return level; }
	public int getId() { return id; }
	public Set<Item> getItems () { return items; }
	public int getTimeReduction () {
		return items.stream().mapToInt(Item::getTimeReduction).sum();
	}

	public void removeQuest () {
		quest = null;
	}

	@Override
	public String toString () {
		return name + "(" + age + ")[" + quest + "]"; 
	}
}
