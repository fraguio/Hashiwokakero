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
package es.fraguio.hashiwokakero.board;

import static es.fraguio.hashiwokakero.util.Constants.END_BOARD_CHAR;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.fraguio.hashiwokakero.board.cell.Cell;
import es.fraguio.hashiwokakero.board.cell.Coordinate;
import es.fraguio.hashiwokakero.board.cell.IslandCell;
import es.fraguio.hashiwokakero.board.cell.WaterCell;
import es.fraguio.hashiwokakero.board.cell.enums.CellAlign;
import es.fraguio.hashiwokakero.board.validator.FileFormatException;
import es.fraguio.hashiwokakero.board.validator.FileValidator;
import es.fraguio.hashiwokakero.util.Constants;

/**
 * Factoría de tableros. Se encarga de crear un objeto {@link Board} a partir
 * del fichero de datos de entrada.
 * 
 * @author Eduardo Nogueira Fraguío
 */
public enum BoardFactory {

	INSTANCE;

	// -------------------------------------------------------- Class Variables

	private static final Character COMMENT_CHAR = Character.valueOf('#');

	// -------------------------------------------------------- Private Methods

	/**
	 * Crea el tablero a partir de una lista de {@link String} que representa
	 * las líneas de los datos de entrada.
	 * 
	 * @param lines
	 *            cada una de las líneas que forman los datos de entrada.
	 * @return instancia de {@link Board} que representa el tablero.
	 */
	private Board createBoard(List<String> lines) {

		int numLines = lines.size();
		int height = Integer.valueOf(lines.get(0)).intValue();
		int width = Integer.valueOf(lines.get(1)).intValue();
		Cell[][] cells = new Cell[height][width];
		for (int i = 2; i < numLines; i++) {

			String line = lines.get(i);
			cells[i - 2] = CellFactory.INSTANCE.createLineCells(line, i - 2,
					width, height);
		}

		return new Board(cells);
	}

	/**
	 * Carga el fichero de datos de entrada y genera una {@link List lista} de
	 * {@link String}. Cada una será una línea del fichero.
	 * 
	 * @param bufferedReader
	 *            búfer que contiene los datos del fichero de entrada.
	 * @return lista de líneas.
	 * @throws IOException
	 *             si se produce algún error de entrada/salida al fichero.
	 */
	private List<String> getLines(BufferedReader bufferedReader)
			throws IOException {

		String line;
		List<String> lines = new ArrayList<String>(Constants.DEFAULT_SIZE);

		if (!bufferedReader.ready()) {
			throw new IOException();
		}

		while ((line = bufferedReader.readLine()) != null) {
			if (line.length() > 0
					&& !Character.valueOf(line.charAt(0)).equals(
							BoardFactory.COMMENT_CHAR)) {
				lines.add(line);
			}
			if (END_BOARD_CHAR.toString().equals(line)) {
				break;
			}
		}

		bufferedReader.close();
		return lines;
	}

	// --------------------------------------------------------- Public Methods

	/**
	 * Crea una instancia del {@link Board tablero} a partir de un búfer de
	 * datos de entrada.
	 * 
	 * @param bufferedReader
	 *            búfer que contiene los datos del fichero de entrada.
	 * @return lista de líneas.
	 * @throws IOException
	 *             si se produce algún error de entrada/salida al fichero.
	 */
	public Board createBoard(BufferedReader bufferedReader) throws IOException,
			FileFormatException {
		List<String> lines = getLines(bufferedReader);
		new FileValidator(lines).doValidation();
		return createBoard(lines);
	}
}

/**
 * Factoría de celdas.
 * 
 * @author Eduardo Nogueira Fraguío
 */
enum CellFactory {

	INSTANCE;

	// -------------------------------------------------------- Class Variables

	/** Carácter asterisco */
	private static final Character ASTERISK_CHAR = Character.valueOf('*');

	// -------------------------------------------------------- Private Methods

	/**
	 * Calcula la {@link CellAlign} de una celda en función de sus coordenadas y
	 * las dimensiones del tablero.
	 * 
	 * @param coordinate
	 *            coordenadas de la posisión de la celda.
	 * @param width
	 *            ancho del tablero.
	 * @param height
	 *            alto del tablero.
	 * @return alineamiento de la celda.
	 */
	private CellAlign getCellAlign(Coordinate coordinate, int width, int height) {

		CellAlign cellAlign;
		if (0 == coordinate.getX() && 0 == coordinate.getY()) {
			cellAlign = CellAlign.LEFT_TOP;
		} else if ((width - 1) == coordinate.getX() && 0 == coordinate.getY()) {
			cellAlign = CellAlign.RIGHT_TOP;
		} else if (0 == coordinate.getX() && (height - 1) == coordinate.getY()) {
			cellAlign = CellAlign.LEFT_BOTTOM;
		} else if ((width - 1) == coordinate.getX()
				&& (height - 1) == coordinate.getY()) {
			cellAlign = CellAlign.RIGHT_BOTTOM;
		} else if (0 == coordinate.getX()) {
			cellAlign = CellAlign.LEFT;
		} else if (0 == coordinate.getY()) {
			cellAlign = CellAlign.TOP;
		} else if ((width - 1) == coordinate.getX()) {
			cellAlign = CellAlign.RIGHT;
		} else if ((height - 1) == coordinate.getY()) {
			cellAlign = CellAlign.BOTTOM;
		} else {
			cellAlign = CellAlign.MIDDLE;
		}
		return cellAlign;
	}

	// --------------------------------------------------------- Public Methods

	/**
	 * Crea una fila completa de celdas.
	 * 
	 * @param line
	 *            la línea del fichero que contiene la información.
	 * @param row
	 *            número de fila.
	 * @param width
	 *            ancho del tablero.
	 * @param height
	 *            alto del tablero.
	 */
	public Cell[] createLineCells(String line, int row, int width, int height) {

		Cell[] cells = new Cell[width];
		final int length = line.length();
		int addedCells = 0;
		for (int i = 0; i < length; i++) {
			Cell cell;
			Character character = Character.valueOf(line.charAt(i));
			if (0 == character.toString().trim().length()) {
				continue;
			}
			Coordinate coordinate = new Coordinate(addedCells++, row);
			CellAlign cellAlign = getCellAlign(coordinate, width, height);
			if (character.equals(CellFactory.ASTERISK_CHAR)) {
				cell = new WaterCell(coordinate, cellAlign);
			} else {
				cell = new IslandCell(coordinate, cellAlign, Integer.valueOf(
						character.toString()).intValue());
			}
			cells[addedCells - 1] = cell;
		}

		return cells;
	}
}