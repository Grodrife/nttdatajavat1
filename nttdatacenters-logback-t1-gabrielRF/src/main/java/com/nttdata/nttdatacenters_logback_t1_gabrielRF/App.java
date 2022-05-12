package com.nttdata.nttdatacenters_logback_t1_gabrielRF;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Taller práctico SLF4J/LOGBACK
 *
 */
public class App {

	/** Logger */
	private static final Logger LOG = LoggerFactory.getLogger(App.class);

	/**
	 * 
	 * Método principal en el que se generan contrasenyas y en el caso de tener más de 2 vocales
	 * considerarla vulnerable
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//Declaración de variables
		char pass[] = new char[5];
		Random randomNumber = new Random();
		StringBuilder sbd = new StringBuilder();
		
		LOG.info("Inicio del método main()");
		LOG.debug("Mensaje para comprobar el nivel");
		
		//Generación de una cantidad suficiente de contrasenyas para utilizar RollingFileAppender
		for (int i = 0; i < 3000; i++) {
			//Generación de letras aleatorias para la contrasenya
			for (int j = 0; j < pass.length; j++) {
				pass[j] = generateLetter(randomNumber.nextInt(26));
			}

			sbd.append(pass);
			
			//Comprobación de vulnerabilidad de la contrasenya
			if (countVowels(pass) > 2) {
				sbd.append(" ¡¡CONTRASEÑA VULNERABLE!!");
			}
			LOG.warn("Contraseña generada {}: {}",i+1 ,sbd);
			sbd.delete(0, sbd.length());
		}
		LOG.info("Fin del método main()");
	}

	
	
	/**
	 * 
	 * Método para generar una letra aleatoriamente
	 * 
	 * @param num
	 * @return char
	 */
	private static char generateLetter(int num) {
		char letters[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
				't', 'u', 'v', 'w', 'x', 'y', 'z' };
		return letters[num];
	}

	
	/**
	 * 
	 * Método para contar el número de vocales de una palabra
	 * 
	 * @param pass
	 * @return int
	 */
	private static int countVowels(char[] pass) {

		char vowels[] = { 'a', 'e', 'i', 'o', 'u' };
		boolean vowelFound = false;
		int vowelsCounter = 0;

		//Recorrer letra por letra la contrasenya
		for (char l : pass) {
			vowelFound = false;
			//Comprobar si es una de las letras de vowels
			for (int i = 0; i < vowels.length && !vowelFound; i++) {
				if (l == vowels[i]) {
					vowelFound = true;
				}
			}
			if (vowelFound) {
				vowelsCounter++;
			}
		}

		return vowelsCounter;
	}
}
