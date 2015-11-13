/*
 * Copyright 2010-2015 Eduardo Nogueira Fraguío.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package es.fraguio.hashiwokakero;

import static es.fraguio.hashiwokakero.util.Constants.RETURN;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import es.fraguio.hashiwokakero.board.Board;
import es.fraguio.hashiwokakero.board.BoardFactory;
import es.fraguio.hashiwokakero.board.validator.FileFormatException;
import es.fraguio.hashiwokakero.strategy.BackTracking;
import es.fraguio.hashiwokakero.strategy.enhancer.SolutionStrategyChain;
import es.fraguio.hashiwokakero.strategy.enhancer.bridgesnumber.BridgesNumberSolutionStrategy;
import es.fraguio.hashiwokakero.strategy.enhancer.incompleteisland.SourceEqualsTargetsSolutionStrategy;
import es.fraguio.hashiwokakero.strategy.enhancer.islandposition.IslandPositionSolutionStrategy;

/**
 * Clase principal. Recibe los argumentos de entrada y crea las estrategias de
 * solución y optimización.
 * 
 * @author Eduardo Nogueira Fraguío
 */
public class Hashiwokakero {

	// -------------------------------------------------------- Private Methods

	/**
	 * Valida los arumentos de entrada al programa.
	 * 
	 * @throws IllegalArgumentException
	 *             si los argumentos de entrada no son correctos.
	 */
	private static void validateArgs(String[] args)
			throws IllegalArgumentException {

		if (args.length == 0) {
			throw new IllegalArgumentException(Help.getSyntax());
		} else if (args.length == 1) {
			if (null == args[0] || args[0].equals("-h")
					|| args[0].equalsIgnoreCase("")) {
				throw new IllegalArgumentException(Help.getHelp());
			}
		} else if (args.length == 2) {
			if (!(args[1].equals("-o"))) {
				throw new IllegalArgumentException(Help.getSyntax());
			}
		}
	}

	// --------------------------------------------------------- Public Methods

	/**
	 * Método de entrada que lanza la ejecución del programa. Construye una
	 * instancia de {@link BufferedReader} con los datos de entrada, que envía
	 * al método {@link BoardFactory#createBoard(BufferedReader)} que crea el
	 * tablero del juego. <br />
	 * Crea la instancia de {@link BufferedReader} a partir de un
	 * {@link InputStreamReader}, en el caso en que haya datos en la entrada
	 * estándar {@link System#in} que puedan haber sido enviados por
	 * redireccionamiento o a partir de un {@link FileReader} que se crea a
	 * partir del nombre del fichero que se recibe como argumento de entrada.
	 * 
	 * @param args
	 *            argumentos de entrada a la aplicación.
	 * @throws IOException
	 *             si se produce algún error de entrada de datos.
	 */
	public static void main(String[] args) throws IOException {

		// Para probar en un IDE descomentar alguna de las líneas que siguen
		// args = new String[] { "boards/board-6.txt" };
		// args = new String[] { "boards/board-6.txt", "-o" };

		Board board = null;

		// System.in.read();

		try {
			if (System.in.available() == 0) {
				validateArgs(args);

				URL urlBoard = ClassLoader.getSystemResource(args[0]);
				if (null == urlBoard) {
					throw new FileNotFoundException(String.format(
							"El sistema no puede encontrar el fichero '%1$s'",
							args[0]));
				}
				board = BoardFactory.INSTANCE.createBoard(new BufferedReader(
						new FileReader(new File(ClassLoader.getSystemResource(
								args[0]).getFile()))));
			} else {
				// Para depurar en un IDE como por ejemplo Eclipse.
				// Descomentando el System.in.read(); de arriba, el IDE espera a
				// que se introduzcan datos en la consola, por ejemplo, copiando
				// y pegando el contenido de alguno de los tableros
				// (resources/boards/*.txt)
				board = BoardFactory.INSTANCE.createBoard(new BufferedReader(
						new InputStreamReader(System.in)));
			}
		} catch (FileNotFoundException fnfe) {
			System.err.println(fnfe.getMessage());
			System.exit(1);
		} catch (FileFormatException ive) {
			System.err.println(ive);
			System.exit(1);
		} catch (IllegalArgumentException iae) {
			System.err.println(iae.getMessage());
			System.exit(1);
		}

		if (args.length == 2 && "-o".equals(args[1])) {
			// Se ejecutan las estrategias de optimización.
			new IslandPositionSolutionStrategy().apply(board);
			new SolutionStrategyChain(
					new BridgesNumberSolutionStrategy(),
					new SourceEqualsTargetsSolutionStrategy()).apply(board);
		}

		new BackTracking().apply(board);
		System.exit(0);
	}
}

/**
 * Clase que contiene los mensajes de créditos y sintaxis que se sacan por
 * consola.
 * 
 * @author Eduardo Nogueira Fraguío
 */
class Help {

	// -------------------------------------------------------- Class Variables

	private static final String CREDITS;
	private static final String SYNTAX;

	// ----------------------------------------------------------------- static

	static {
		CREDITS = "2010-2015 Eduardo Nogueira Fraguío.";
		SYNTAX = new StringBuilder("java hashiwokakero [-h] fichero [-o]\n\n")
				.append("-h: modo ayuda. Muestra la sintaxis y los creditos.\n")
				.append("fichero: el nombre del archivo que contiene los ")
				.append("datos de entrada.\n")
				.append("-o: aplica algoritmos de optimización ")
				.append("en la búsqueda de la solución.").toString();
	}

	// --------------------------------------------------------- Public Methods

	/**
	 * Devuelve los créditos del programa.
	 * 
	 * @return {@link String}
	 */
	public static String getCredits() {
		return CREDITS;
	}

	/**
	 * Devuelve los créditos y la sintaxis de ejecución del programa.
	 * 
	 * @return {@link String}
	 */
	public static String getHelp() {
		return new StringBuilder(getSyntax()).append(RETURN).append(RETURN)
				.append(getCredits()).toString();
	}

	/**
	 * Devuelve los créditos y la sintaxis de ejecución del programa.
	 * 
	 * @return {@link String}
	 */
	public static String getSyntax() {
		return SYNTAX;
	}
}