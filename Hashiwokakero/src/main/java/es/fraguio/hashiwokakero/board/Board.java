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

import static es.fraguio.hashiwokakero.util.Constants.DEFAULT_SIZE;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import es.fraguio.hashiwokakero.board.cell.BridgeCell;
import es.fraguio.hashiwokakero.board.cell.Cell;
import es.fraguio.hashiwokakero.board.cell.Coordinate;
import es.fraguio.hashiwokakero.board.cell.IslandCell;
import es.fraguio.hashiwokakero.board.cell.enums.Axis;
import es.fraguio.hashiwokakero.board.cell.enums.Direction;
import es.fraguio.hashiwokakero.board.cell.exception.CrossedBridgesException;
import es.fraguio.hashiwokakero.board.cell.exception.DoubleBridgeException;
import es.fraguio.hashiwokakero.board.cell.exception.NoMoreBridgesException;
import es.fraguio.hashiwokakero.strategy.BridgeDesign;

/**
 * Tablero en donde reside el grafo.
 * 
 * @author Eduardo Nogueira Fraguío
 */
public class Board {

	// ---------------------------------------------------- Instance Properties

	/** Celdas del tablero representadas por un array de 2 dimensiones. */
	private Cell[][] cells;

	/** Altura del tablero */
	private int height = 0;

	/** Anchura del tablero */
	private int width = 0;

	// ----------------------------------------------------------- Constructors

	/**
	 * Construye un tablero a partir de las celdas que recibe como argumento.
	 * 
	 * @param cells
	 *            celdas que constituyen el tablero.
	 */
	public Board(Cell[][] cells) {
		super();
		this.cells = cells;
		this.height = cells.length;
		this.width = cells[0].length;
	}

	// -------------------------------------------------------- Private Methods

	/**
	 * Determina el eje sobre el que se traza el puente en función de la
	 * dirección de trazado.
	 * 
	 * @param direction
	 *            dirección de trazado del puente.
	 */
	private Axis getAxis(Direction direction) {
		Axis axis;
		if (Direction.NORTH.equals(direction)
				|| Direction.SOUTH.equals(direction)) {
			axis = Axis.NORTH_SOUTH;
		} else {
			axis = Axis.EAST_WEST;
		}
		return axis;
	}

	/**
	 * Comprueba si se puede trazar un puente por encima de la celda.
	 * 
	 * @param cell
	 *            celda objeto de la comprobación.
	 * @param direction
	 *            dirección en la que se pretende trazar el puente.
	 * @return <code>true</code> si es posible trazar un puente por encima de
	 *         esta celda, dicho de otro modo, si la celda se puede convertir en
	 *         una instancia de {@link BridgeCell}
	 * @throws CrossedBridgesException
	 *             si la celda ya es de tipo {@link BridgeCell} y su
	 *             {@link Axis eje de trazado} es contrario al que se pretende.
	 * @throws DoubleBridgeException
	 *             se produce cuando la celda ya contiene un puente doble. Por
	 *             lo tanto no se pueden "añadir" más.
	 * @throws NoMoreBridgesException
	 *             se produce si la celda está completa.
	 * @see IslandCell#isCompleted()
	 */
	public boolean isBridgeable(Cell cell, Direction direction)
			throws CrossedBridgesException, DoubleBridgeException,
			NoMoreBridgesException {

		boolean isBridgeable = true;
		Axis axis = getAxis(direction);
		if (cell instanceof BridgeCell) {

			BridgeCell bridgeCell = (BridgeCell) cell;
			if (bridgeCell.isDoubleBridge()) {
				throw new DoubleBridgeException();
			}

			if (!axis.equals(bridgeCell.getAxis())) {
				throw new CrossedBridgesException();
			}

		} else if (cell instanceof IslandCell
				&& ((IslandCell) cell).isCompleted()) {
			throw new NoMoreBridgesException();
		}
		return isBridgeable;
	}

	// --------------------------------------------------------- Public Methods

	/**
	 * Clona el tablero de juego.
	 * 
	 * @return clon del tablero.
	 */
	@Override
	public Board clone() {
		Cell[][] clone = new Cell[this.height][this.width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				clone[i][j] = this.cells[i][j].clone();
			}
		}
		return new Board(clone);
	}

	/**
	 * Crea un puente entre dos islas.
	 * 
	 * @param bridgeDesign
	 *            aporta la información necesaria, dirección, origen, destino
	 *            ...., para trazar un nuevo puente.
	 */
	public void createBridge(BridgeDesign bridgeDesign) {

		final int numBridges = bridgeDesign.getNumBridges();
		for (int i = 0; i < numBridges; i++) {

			List<Cell> bridgeableCells = getCells(bridgeDesign.getSource(),
					bridgeDesign.getTarget(), bridgeDesign.getDirection());

			Axis axis = getAxis(bridgeDesign.getDirection());
			for (Cell cell : bridgeableCells) {

				if (cell instanceof BridgeCell) {
					BridgeCell bridgeCell = (BridgeCell) cell;
					bridgeCell.setDoubleBridge(true);
				} else {
					cell = new BridgeCell(cell.getCoordinate(),
							cell.getCellAlign(), axis);
				}

				setCell(cell);
			}

			bridgeDesign.getSource().addBridge();
			bridgeDesign.getTarget().addBridge();
		}
	}

	/**
	 * Devuelve una celda en función de sus coordenadas.
	 * 
	 * @param x
	 *            coordenada x de la celda.
	 * @param y
	 *            coordenada y de la celda.
	 */
	public Cell getCell(int x, int y) {
		return cells[y][x];
	}

	/**
	 * Devuelve todas las celdas que existen entre dos islas.
	 * 
	 * @param source
	 *            isla de origen.
	 * @param target
	 *            isla de destino.
	 * @param direction
	 *            dirección de la búsqueda.
	 * @return lista de celdas entre dos islas.
	 */
	public List<Cell> getCells(IslandCell source, IslandCell target,
			Direction direction) {

		List<Cell> cells = new ArrayList<Cell>(DEFAULT_SIZE);
		Cell nextCell = source;
		try {
			do {
				nextCell = getNextCell(nextCell, direction);
				cells.add(nextCell);
			} while (!nextCell.equals(target));
			cells.remove(nextCell);
		} catch (OutOfBoardException oobe) {
			oobe.printStackTrace();
		}
		return cells;
	}

	/**
	 * Devuelve la siguiente celda del tablero en una dirección indicada.
	 * 
	 * @param cell
	 *            celda actual de la que se busca su vecina.
	 * @param direction
	 *            la dirección en la que se busca.
	 * @return la siguiente celda a una celda dada.
	 * @throws OutOfBoardException
	 *             si se intenta acceder a una posición fuera del tablero.
	 */
	public Cell getNextCell(Cell cell, Direction direction)
			throws OutOfBoardException {

		Coordinate coordinate = null;
		switch (direction) {
		case NORTH:
			if (cell.getCoordinate().getY() > 0) {
				coordinate = new Coordinate(cell.getCoordinate().getX(), cell
						.getCoordinate().getY() - 1);
			}
			break;
		case SOUTH:
			if (cell.getCoordinate().getY() != (height - 1)) {
				coordinate = new Coordinate(cell.getCoordinate().getX(), cell
						.getCoordinate().getY() + 1);
			}
			break;
		case EAST:
			if (cell.getCoordinate().getX() != (width - 1)) {
				coordinate = new Coordinate(cell.getCoordinate().getX() + 1,
						cell.getCoordinate().getY());
			}
			break;
		case WEST:
			if (cell.getCoordinate().getX() > 0) {
				coordinate = new Coordinate(cell.getCoordinate().getX() - 1,
						cell.getCoordinate().getY());
			}
		}

		if (coordinate == null) {
			throw new OutOfBoardException();
		}

		return cells[coordinate.getY()][coordinate.getX()];
	}

	/**
	 * Devuelve la siguiente isla del tablero en una dirección indicada.
	 * 
	 * @param cell
	 *            celda actual para la que se busca la siguiente isla.
	 * @param direction
	 *            la dirección en la que se busca.
	 * @return la siguiente isla a una celda dada.
	 * @throws OutOfBoardException
	 *             si se intenta acceder a una posición fuera del tablero.
	 * @throws CrossedBridgesException
	 *             si la celda ya es de tipo {@link BridgeCell} y su
	 *             {@link Axis eje de trazado} es contrario al que se pretende.
	 * @throws DoubleBridgeException
	 *             se produce cuando la celda ya contiene un puente doble. Por
	 *             lo tanto no se pueden "añadir" más.
	 * @throws NoMoreBridgesException
	 *             se produce si la celda está completa.
	 * @see IslandCell#isCompleted()
	 * @see #isBridgeable(Cell, Direction)
	 */
	public IslandCell getNextIslandCell(Cell cell, Direction direction)
			throws OutOfBoardException, CrossedBridgesException,
			DoubleBridgeException, NoMoreBridgesException {

		Cell nextCell = null;

		do {
			nextCell = getNextCell((nextCell == null) ? cell : nextCell,
					direction);
			isBridgeable(nextCell, direction);
		} while ((nextCell instanceof IslandCell) == false);

		return (IslandCell) nextCell;
	}

	/**
	 * Coloca una nueva celda en una posición del tablero.
	 * 
	 * @param cell
	 *            la celda que se coloca en el tablero.
	 */
	public void setCell(Cell cell) {
		Coordinate coordinate = cell.getCoordinate();
		cells[coordinate.getY()][coordinate.getX()] = cell;
	}

	/**
	 * Devuelve un {@link Iterator iterador} sobre todas las islas del tablero
	 * que están {@link IslandCell#isCompleted() incompletas}
	 * 
	 * @return instancia de {@link Iterator}
	 */
	public Iterator<IslandCell> incompleteIslandCellIterator() {
		return new IncompleteIslandCellIterator(
				new IslandCellIterator(new BoardIterator(cells)));
	}

	/**
	 * Devuelve un {@link Iterator iterador} sobre todas las islas del tablero.
	 * 
	 * @return instancia de {@link Iterator}
	 */
	public Iterator<IslandCell> islandCellIterator() {
		return new IslandCellIterator(new BoardIterator(cells));
	}

	/**
	 * Devuelve un {@link Iterator iterador} sobre todas las celdas del tablero.
	 * 
	 * @return instancia de {@link Iterator}
	 */
	public Iterator<Cell> iterator() {
		return new BoardIterator(cells);
	}

	/**
	 * Crea una representación gráfica del estado actual del tablero, invocando
	 * los métodos {@link Cell#toString()} de todas las celdas del tablero.
	 * 
	 * @return representación gráfica del estado actual del tablero.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				sb.append(this.cells[i][j].renderToString());
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	// --------------------------------------------------- Properties Accessors

	/**
	 * @return the cells
	 */
	public Cell[][] getCells() {
		return cells;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}
}