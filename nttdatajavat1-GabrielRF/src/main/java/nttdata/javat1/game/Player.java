package nttdata.javat1.game;

import java.util.Objects;

public class Player implements Comparable<Player> {

	private String name;
	private int totalScore;

	public Player() {
		this.setName("");
		this.setTotalScore(0);
	}

	public Player(String name) {
		this.setName(name);
		this.setTotalScore(0);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public void addScore(int score) {
		this.setTotalScore(this.getTotalScore() + score);
	}

	@Override
	public String toString() {
		return "Jugador\n\tnombre --> " + name + "\tPuntuación total --> " + totalScore;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public int compareTo(Player o) {
		return o.getTotalScore() - this.getTotalScore();
	}

}
