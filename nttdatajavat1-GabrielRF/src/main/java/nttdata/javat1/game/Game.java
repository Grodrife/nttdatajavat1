package nttdata.javat1.game;

import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;

public class Game {

	private int tries;
	private Set<Ball> balls;
	private Player player;

	public Game(int tries) {
		this.setTries(tries);
		balls = new TreeSet<Ball>();
		this.setPlayer(player);
	}

	public int getTries() {
		return tries;
	}

	public void setTries(int tries) {
		this.tries = tries;
	}

	public Set<Ball> getBalls() {
		return balls;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * Método para iniciar el juego
	 */
	public void launchAndStart() {

		Scanner sc = new Scanner(System.in);
		int playerScore = 0;
		// Repetir el juego según el número de tiradas que haya indicado el jugador
		for (int i = 0; i < this.getTries(); i++) {
			// Variables
			// Booleano para indicar el fin del juego
			boolean end = false;
			// Indicador del seguro de las zonas 2 y 3
			int saveTwo = 1;
			int saveThree = 1;
			// Indicador de zona en la que entra la bola
			int zone = 1;
			// Generador del multiplicador de la bola
			Random randomMultiplier = new Random();
			Ball ball = new Ball(i, randomMultiplier.nextFloat(5));

			while (!end) {
				switch (zone) {
				case 1:
					int option = firstZone();

					switch (option) {
					case 1:
						ball.addScore(50);
						zone = 1;
						break;

					case 2:
						ball.addScore(150);
						zone = 1;
						break;

					case 3:
						ball.addScore(100);
						zone = 1;
						break;

					case -1:
						ball.subtractScore(15);
						zone = fifthZone();
						break;

					case -2:
						zone = bothSticks();
						break;

					case -3:
						ball.subtractScore(15);
						zone = fourthZone();
						break;

					default:
						break;
					}
					break;

				case 2:
					if (saveTwo == 1) {
						zone = 1;
						saveTwo--;
					} else {
						zone = 0;
					}
					break;

				case 3:
					if (saveThree == 1) {
						zone = 1;
						saveThree--;
					} else {
						zone = 0;
					}
					break;

				case 4:
					zone = fourthZone();
					break;

				case 5:
					zone = fifthZone();
					break;

				case 0:
					end = true;
					balls.add(ball);
					playerScore += ball.getScore();
					break;
				default:
					break;
				}

			}

		}

		Player player = new Player(writeName(sc));
		this.setPlayer(player);
		this.getPlayer().addScore(playerScore);
	}

	/**
	 * Método para zona 1
	 * 
	 * @return número de la zona por la que sale la bola de la zona 1
	 */
	public int firstZone() {
		Random randomNumber = new Random();
		int number = randomNumber.nextInt(360);
		// Según el número la bola rebota o sale de la zona 1
		int res = number >= 0 && number <= 100 ? 1
				: number >= 120 && number <= 220 ? 2
						: number >= 240 && number <= 340 ? 3
								: number > 100 && number < 120 ? -1 : number > 220 && number < 240 ? -2 : -3;
		return res;
	}

	/**
	 * Método para zona de ambos sticks
	 * 
	 * @return número de la próxima zona
	 */
	public int bothSticks() {
		int res = 0;
		Random numberStick = new Random();
		if (numberStick.nextInt(2) == 1) {
			res = fourthZone();
		} else {
			res = fifthZone();
		}
		return res;
	}

	/**
	 * Método para zona 4
	 * 
	 * @return número de la próxima zona
	 */
	public int fourthZone() {
		Random num1 = new Random();
		Random num2 = new Random();
		Random randomNumber = new Random();
		int stick;
		int zone = 0;

		if (num1.nextInt(10) != num2.nextInt(10)) {
			stick = randomNumber.nextInt(10);
			zone = stick < 10 && stick >= 5 ? 1 : stick < 5 && stick > 1 ? 5 : stick == 1 ? 3 : 0;
		}

		return zone;
	}

	/**
	 * Método para zona 5
	 * 
	 * @return número de la próxima zona
	 */
	public int fifthZone() {
		Random num1 = new Random();
		Random num2 = new Random();
		Random randomNumber = new Random();
		int stick;
		int zone = 0;

		if (num1.nextInt(10) != num2.nextInt(10)) {
			stick = randomNumber.nextInt(10);
			zone = stick < 10 && stick >= 5 ? 1 : stick < 5 && stick > 1 ? 4 : stick == 1 ? 2 : 0;
		}

		return zone;
	}

	/**
	 * Método para escribir el nombre del jugador
	 * 
	 * @param Scanner sc
	 * @return String con el nombre del jugador
	 */
	public String writeName(Scanner sc) {
		String playerName;
		do {
			System.out.print("Introduzca su nombre (3 letras)\n");
			playerName = sc.nextLine();
		} while (playerName.length() != 3 || !StringUtils.isAlpha(playerName));
		return playerName;
	}

	/**
	 * Método para añadir todas las puntuaciones del jugador
	 */
	public void addTotalScore() {
		for (Ball b : balls) {
			player.addScore(b.getScore());
		}
	}

	@Override
	public String toString() {
		String res = "Partida\n\n";

		for (Ball b : balls) {
			res += "\t\t" + b.toString() + "\n";
		}
		return res;
	}

}
