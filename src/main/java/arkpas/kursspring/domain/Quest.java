package arkpas.kursspring.domain;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class Quest {

	private int id;
	private String description;
	private int goldReward;
	private int length; //in seconds
	private boolean started = false;
	private boolean completed = false;
	private LocalDateTime startDate;

	public Quest (String description, int goldReward, int length) {
		this.description = description;
		this.goldReward = goldReward;
		this.length = length;
	}

	public int getId() { return id; }
	public String getDescription() { return description; }
	public int getGoldReward() { return goldReward; }
	public int getLength() { return length; }
	public boolean isStarted() { return started; }
	public boolean isCompleted() {
		if (completed)
			return completed;

		if (started) {
			LocalDateTime now = LocalDateTime.now();
			completed = startDate.plusSeconds(length).isBefore(now);
		}
		return completed;

	}
	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setId (int id) { this.id = id; }
	public void setDescription(String description) { this.description = description; }
	public void setGoldReward(int goldReward) { this.goldReward = goldReward; }
	public void setLength(int length) { this.length = length; }
	public void setStarted(boolean started) {
		this.started = started;
		if (started)
			startDate = LocalDateTime.now();
	}
	
	@Override
	public String toString () {
		return description;
	}

	public void endQuest() {
		started = false;
		completed = false;
		startDate = null;
	}


}
