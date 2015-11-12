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
package es.fraguio.hashiwokakero.board.validator;

import static es.fraguio.hashiwokakero.util.Constants.END_BOARD_CHAR;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Realiza la validación de los datos del fichero de entrada.
 * 
 * @author Eduardo Nogueira Fraguío
 */
public class FileValidator {

	// -------------------------------------------------------- Class Variables

	private static final int HEIGHT = 0;

	private static final int WIDTH = 1;

	// ---------------------------------------------------- Instance Properties

	private List<String> lines;

	private int[] boardDimension = new int[2];

	// ----------------------------------------------------------- Constructors

	/**
	 * Constructor
	 * 
	 * @param lines
	 *            líneas del fichero de entrada de datos.
	 */
	public FileValidator(List<String> lines) {
		this.lines = lines;
	}

	// -------------------------------------------------------- Private Methods

	/**
	 * Comprueba que exista un símbolo '+' que indique que los datos de entrada
	 * han finalizado. Todo el contenido del fichero que venga a continuación se
	 * ignora.
	 * 
	 * @throws FileFormatException
	 *             si el símbolo '+' no se encuentra en el fichero.
	 */
	private void validateEndLine() throws FileFormatException {
		final String endLine = this.lines.get(this.lines.size() - 1);
		if (!END_BOARD_CHAR.toString().equals(endLine)) {
			throw new FileFormatException(new StringBuilder(
					"El fichero de datos de entrada debe contener ").append(
					"un caracter '+' que indique el final del tablero. ")
					.toString());
		}
		this.lines.remove(this.lines.size() - 1);
	}

	/**
	 * Comprueba los datos que forman el tablero de juego.
	 * <ul>
	 * <li>Que el número de filas que contiene coincida con las declaradas en la
	 * cabecera.</li>
	 * <li>Que el número de columnas que contiene coincida con las declaradas en
	 * la cabecera.</li>
	 * <li>Qué sólo se hayan introducido caracteres válidos [1-8 * " "]</li>
	 * </ul>
	 * 
	 * @throws FileFormatException
	 *             si los datos que forman el tablero no son correctos.
	 */
	private void validateBoard() throws FileFormatException {
		if (boardDimension[HEIGHT] != (this.lines.size() - 2)) {
			throw new FileFormatException(String.format(
					new StringBuilder("El numero de filas del tablero ")
							.append("no coincide con el numero declarado. ")
							.append("%1$s != %2$s ").toString(),
					this.lines.size() - 2, boardDimension[HEIGHT]));
		}

		Pattern validChars = Pattern.compile("[1-8*\" \"]+");
		Pattern whiteSpacesPattern = Pattern.compile("[\" \"]+");

		for (int i = 2; i < this.lines.size(); i++) {
			Matcher matcher = validChars.matcher(this.lines.get(i));
			if (!matcher.matches()) {
				throw new FileFormatException(String.format(
						new StringBuilder("La fila %1$s del tablero ")
								.append("tiene un caracter invalido. Solo ")
								.append("se permiten numeros de 1 a 8, ")
								.append("asteriscos o espacios en blanco")
								.toString(), i - 1));
			}

			final int numColumns = whiteSpacesPattern
					.matcher(this.lines.get(i)).replaceAll("").length();

			if (boardDimension[WIDTH] != numColumns) {
				throw new FileFormatException(
						String.format(
								new StringBuilder(
										"El numero de columnas del tablero ")
										.append("en la fila %1$s ")
										.append("no coincide con el numero declarado. ")
										.append("%2$s != %3$s ").toString(),
								i - 1, numColumns, boardDimension[WIDTH]));
			}
		}
	}

	/**
	 * Valida la cabecera del fichero de datos que está formada por las dos
	 * primeras líneas significativas (que no estén comentadas con # o vacías ).
	 * Cada una de estas filas debe contener un número que indica,
	 * respectivamente, el número de filas y columnas que contiene el tablero.
	 * 
	 * @throws FileFormatException
	 *             si la cabecera no es correcta.
	 */
	private void validateHeaders() throws FileFormatException {
		final String[] ordinal = new String[] { "primera", "segunda" };
		final String[] headers = new String[] { "filas", "columnas" };

		int i;
		for (i = 0; i < 2; i++) {
			String line = this.lines.get(i).trim();
			try {
				boardDimension[i] = Integer.valueOf(line).intValue();
			} catch (NumberFormatException e) {
				throw new FileFormatException(String.format(
						new StringBuilder(
								"La %1$s linea significativa del fichero ")
								.append("de entrada debe ser un ")
								.append("valor numerico que indique el ")
								.append("numero de %2$s. ").toString(),
						ordinal[i], headers[i]));
			}
		}
	}

	// --------------------------------------------------------- Public Methods

	/**
	 * Realiza la validación del fichero de datos de entrada delegando la tarea
	 * en los métodos {@link #validateBoard()}, {@link #validateEndLine()} y
	 * {@link #validateHeaders()}.
	 * 
	 * @throws FileFormatException
	 *             si los datos que forman el tablero no son correctos.
	 */
	public void doValidation() throws FileFormatException {

		validateEndLine();
		validateHeaders();
		validateBoard();
	}
}