package nttdata.javat1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import nttdata.javat1.game.Game;
import nttdata.javat1.game.Player;

/**
 * 
 * 1º Taller práctico de Java
 *
 */
public class T1MainGabrielRF {

	/**
	 * Método principal/main en el que, en función de las partidas deseadas, lanzar
	 * método launchAndStart
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		List<Game> games = new ArrayList<Game>();
		Set<Player> players = new TreeSet<Player>();
		Scanner sc = new Scanner(System.in);
		boolean repeat;

		do {
			int tries = numTries(sc);

			Game game = new Game(tries);
			game.launchAndStart();
			games.add(game);

			repeat = repeatGame(sc);
		} while (repeat);

		printPlayerScore(games);
		showRanking(games, players, sc);
		sc.close();
	}

	/**
	 * Método para introducir el número de tiradas del jugador
	 * 
	 * @param Scanner sc
	 * @return int
	 */
	public static int numTries(Scanner sc) {

		int tries;
		System.out.println("¿Cuántas partidas quiere jugar?");
		tries = sc.nextInt();
		while (tries <= 0) {
			System.out.println("Introduzca un número mayor que 0");
			tries = sc.nextInt();
		}
		return tries;

	}

	/**
	 * Método para preguntar si el jugador desea volver a jugar
	 * 
	 * @param Scanner sc
	 * @return boolean
	 */
	public static boolean repeatGame(Scanner sc) {

		int option;

		do {
			System.out.println("¿Quieres volver a jugar? (SI = 1  // NO = 0)");
			option = sc.nextInt();
		} while (option > 1 || option < 0);
		return option == 1 ? true : false;

	}

	/**
	 * Método para mostrar las puntuaciones finales
	 * 
	 * @param List<Game> games
	 */
	public static void printPlayerScore(List<Game> games) {
		System.out.println("\n\nLa puntuación final ha sido: \n\n");
		for (int i = 0; i < games.size(); i++) {
			System.out.println("\t" + (i + 1) + "ª" + games.get(i).toString() + "\n");
		}
	}

	/**
	 * Método para mostrar el ranking final de puntuación
	 * 
	 * @param List<Game>  games
	 * @param Set<Player> players
	 * @param Scanner     sc
	 */
	public static void showRanking(List<Game> games, Set<Player> players, Scanner sc) {

		boolean ranking = knowRanking(sc);
		if (ranking) {
			for (Game g : games) {
				players.add(g.getPlayer());
			}
			printRanking(players);
		}

	}

	/**
	 * Método para preguntar si el jugador desea conocer el ranking final
	 * 
	 * @param Scanner sc
	 * @return boolean
	 */
	public static boolean knowRanking(Scanner sc) {

		int num;
		do {
			System.out.println("¿Quieres conocer el ranking final? (SI = 1 // NO = 0)");
			num = sc.nextInt();
		} while (num > 1 || num < 0);

		return num == 1 ? true : false;
	}

	/**
	 * Método para escribir el ranking final
	 * 
	 * @param Set<Player> players
	 */
	public static void printRanking(Set<Player> players) {
		System.out.println("\n\tRanking final\n");
		for (Player p : players) {
			System.out.println("\t\tJugador --> " + p.getName() + " || " + p.getTotalScore() + "\n");
		}
	}
}
