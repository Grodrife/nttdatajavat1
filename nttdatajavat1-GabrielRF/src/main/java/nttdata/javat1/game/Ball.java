package nttdata.javat1.game;

import java.util.Objects;

public class Ball implements Comparable<Ball> {

	private int id;
	private int score;
	private float multiplier;

	public Ball() {
		setId(0);
		setScore(0);
		setMultiplier(0);
	}

	public Ball(int id, float multiplier) {
		setId(id);
		setScore(0);
		setMultiplier(multiplier);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public float getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(float multiplier) {
		this.multiplier = multiplier;
	}

	/**
	 * Método para sumar la puntuación obtenida según el multiplicador de la bola
	 * 
	 * @param int score
	 */
	public void addScore(int score) {
		this.score += (score * this.getMultiplier());
	}

	/**
	 * Método para restar la puntuación obtenida según el multiplicador de la bola
	 * 
	 * @param int score
	 */
	public void subtractScore(int score) {
		if (this.score - (score * this.getMultiplier()) >= 0) {
			this.score -= (score * this.getMultiplier());
		}
	}

	@Override
	public String toString() {
		return "Puntuación --> " + score + " || Multiplicador --> " + multiplier + "\n";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ball other = (Ball) obj;
		return id == other.id;
	}

	@Override
	public int compareTo(Ball o) {
		return o.getScore() - this.getScore();
	}

}
