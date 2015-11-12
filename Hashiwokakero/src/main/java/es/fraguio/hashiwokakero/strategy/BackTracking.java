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
package es.fraguio.hashiwokakero.strategy;

import java.util.ArrayList;
import java.util.List;

import es.fraguio.hashiwokakero.board.Board;
import es.fraguio.hashiwokakero.board.OutOfBoardException;
import es.fraguio.hashiwokakero.board.cell.Cell;
import es.fraguio.hashiwokakero.board.cell.IslandCell;
import es.fraguio.hashiwokakero.board.cell.enums.Direction;
import es.fraguio.hashiwokakero.board.cell.exception.CellException;
import es.fraguio.hashiwokakero.board.cell.exception.CrossedBridgesException;
import es.fraguio.hashiwokakero.board.cell.exception.DoubleBridgeException;
import es.fraguio.hashiwokakero.board.cell.exception.NoMoreBridgesException;
import es.fraguio.hashiwokakero.util.Constants;
import es.fraguio.hashiwokakero.util.Util;

/**
 * Implementación del algoritmo de <a
 * href="http://es.wikipedia.org/wiki/Vuelta_Atr%C3%A1s">"vuelta atrás"</a> para
 * solucionar el puzzle Hashiwokakero.
 * 
 * @author Eduardo Nogueira Fraguío
 */
public class BackTracking {

	// -------------------------------------------------------- Class Variables

	/** Carácter cero */
	private static final char ZERO_CHAR = '0';

	private static final String ONE_STRING = "1";

	private static final String TWO_STRING = "2";

	// ---------------------------------------------------- Instance Properties

	private Board board;

	private boolean backTracking = false;

	// ----------------------------------------------------------- Constructors

	/**
	 * Constructor del algoritmo.
	 */
	public BackTracking() {
		super();
	}

	// -------------------------------------------------------- Private Methods

	/**
	 * Convierte una palabra de 2 bits en base 3 en una lista de
	 * {@link BridgeDesign} según se detalla {@link #solve(int, int) aquí}.
	 * 
	 * @param source
	 *            isla de origen. *
	 * @param binary
	 *            palabra de 2 bits en base 3.
	 * @return lista de {@link BridgeDesign}
	 * @throws CrossedBridgesException
	 *             si se cruzan dos puentes.
	 * @throws DoubleBridgeException
	 *             cuando ya existe un puente doble y, por tanto, no se pueden
	 *             trazar más en esa dirección
	 * @throws NoMoreBridgesException
	 *             cuando la isla de origen está completa.
	 * @throws OutOfBoardException
	 *             cuando se intenta alcanzar una celda que estaría fuera del
	 *             tablero de juego.
	 */
	private List<BridgeDesign> binary2BridgeDesign(IslandCell source,
			String binary) throws CrossedBridgesException,
			DoubleBridgeException, NoMoreBridgesException, OutOfBoardException {

		List<BridgeDesign> bridgeDesigns = new ArrayList<BridgeDesign>();

		for (int i = 0; i < Constants.INT_2; i++) {
			BridgeDesign bridgeDesign = getBridgeDesign(source, Character
					.valueOf(binary.charAt(i)).toString(),
					Direction.values()[i]);
			if (bridgeDesign != null) {
				bridgeDesigns.add(bridgeDesign);
			}
		}

		return bridgeDesigns;
	}

	/**
	 * Construye un {@link BridgeDesign} a partir de un bit según se detalla
	 * {@link #solve(int, int) aquí}.
	 * 
	 * @param source
	 *            isla origen
	 * @param bit
	 *            bit que determina el tipo de {@link BridgeDesign} que se
	 *            devuelve.
	 * @param direction
	 *            dirección en la que se trata de construir el puente.
	 * @return instancia de {@link BridgeDesign}
	 * @throws CrossedBridgesException
	 *             si se cruzan dos puentes.
	 * @throws DoubleBridgeException
	 *             cuando ya existe un puente doble y, por tanto, no se pueden
	 *             trazar más en esa dirección
	 * @throws NoMoreBridgesException
	 *             cuando la isla de origen está completa.
	 * @throws OutOfBoardException
	 *             cuando se intenta alcanzar una celda que estaría fuera del
	 *             tablero de juego.
	 */
	private BridgeDesign getBridgeDesign(IslandCell source, String bit,
			Direction direction) throws CrossedBridgesException,
			DoubleBridgeException, NoMoreBridgesException, OutOfBoardException {

		BridgeDesign bridgeDesign = null;
		if (bit.equals(BackTracking.ONE_STRING)) {
			bridgeDesign = new BridgeDesign(source,
					this.board.getNextIslandCell(source, direction), direction,
					false);
		} else if (bit.equals(BackTracking.TWO_STRING)) {
			bridgeDesign = new BridgeDesign(source,
					this.board.getNextIslandCell(source, direction), direction,
					true);
		}
		return bridgeDesign;
	}

	/**
	 * Determina el número de puentes que se trazan en función de una palabra de
	 * 2 bits en base 3 tal y como se detalla {@link #solve(int, int) aquí}.
	 * 
	 * @param binary
	 *            palabra de 2 bits en base 3.
	 * @return número de puentes.
	 */
	private int getNumBridges(String binary) {
		int total = 0;
		for (int i = 0; i < binary.length(); i++) {
			total += Integer.valueOf(Character.valueOf(binary.charAt(i))
					.toString());
		}
		return total;
	}

	/**
	 * Llama al método {@link #solve(int, int)} para la celda siguiente.
	 * 
	 * @param x
	 *            coordenda x de la celda.
	 * @param y
	 *            coordenda y de la celda.
	 */
	private void next(int x, int y) {

		if (y < this.board.getWidth() - 1)
			solve(x, y + Constants.INT_1);
		else
			solve(x + Constants.INT_1, 0);
	}

	/**
	 * Método recursivo que es invocado por cada celda. Si la celda no es una
	 * isla o, siendo una isla, ya está {@link IslandCell#isCompleted()
	 * completa} se cede el control a la celda siguiente. <br/>
	 * Cada vez que una isla tiene el control, itera sobre un valor entero que
	 * va de 1 a 8, expresado por una palabra de 2 bits en base 3, cuyo
	 * significado se detalla a continuación:
	 * <ul>
	 * <li>00 - No se traza ningún puente</li>
	 * <li>01 - Un puente simple en dirección {@link Direction#EAST}</li>
	 * <li>02 - Un puente doble en dirección {@link Direction#EAST}</li>
	 * <li>10 - Un puente simple en dirección {@link Direction#SOUTH}</li>
	 * <li>11 - Un puente simple en dirección {@link Direction#SOUTH} y otro
	 * simple en dirección {@link Direction#EAST}</li>
	 * <li>12 - Un puente simple en dirección {@link Direction#SOUTH} y uno
	 * doble en dirección {@link Direction#EAST}</li>
	 * <li>20 - Un puente doble en dirección {@link Direction#SOUTH}</li>
	 * <li>21 - Un puente doble en dirección {@link Direction#SOUTH} y uno
	 * simple en dirección {@link Direction#EAST}</li>
	 * <li>22 - Un puente doble en dirección {@link Direction#SOUTH} y otro
	 * doble en dirección {@link Direction#EAST}</li>
	 * </ul>
	 * Intenta trazar los puentes generados según la tabla anterior. Si lo
	 * consigue invoca {@link #next(int, int)} y cede el control a la celda
	 * siguiente. En caso contrario devuelve el control a la celda que lo llamó,
	 * dejando el tablero en el estado anterior a la llamada actual y
	 * continuando la iteración entre valores de 1 a 8 que previamente había
	 * iniciado.
	 * 
	 * @param x
	 *            coordenda x de la celda.
	 * @param y
	 *            coordenda y de la celda.
	 */
	private void solve(int x, int y) {

		if (x > this.board.getHeight() - 1) {
			throw new RuntimeException("Solucion encontrada en ");
		}

		Board clone = null;
		Cell cell = this.board.getCell(x, y);

		if (!(cell instanceof IslandCell) || ((IslandCell) cell).isCompleted()) {
			next(x, y);
		} else {

			IslandCell islandCell = (IslandCell) cell;

			for (int num = 1; num < Constants.INT_9; num++) {

				if (backTracking) {
					this.board = new Board(clone.getCells());
					backTracking = false;
					islandCell = (IslandCell) this.board.getCell(islandCell
							.getCoordinate().getX(), islandCell.getCoordinate()
							.getY());
				}

				String binary = Util.leftPad(Util.toTerciaryString(num),
						Constants.INT_2, BackTracking.ZERO_CHAR);

				if (getNumBridges(binary) == islandCell.getUnbuiltBridges()) {
					List<BridgeDesign> bridgeDesigns = new ArrayList<BridgeDesign>();
					try {
						bridgeDesigns = binary2BridgeDesign(islandCell, binary);
					} catch (CellException e) {
						continue;
					} catch (OutOfBoardException e) {
						continue;
					}
					if (!bridgeDesigns.isEmpty()) {
						clone = this.board.clone();
						for (BridgeDesign bridgeDesign : bridgeDesigns) {
							this.board.createBridge(bridgeDesign);
						}

						next(x, y);

					}
				}
			}
			backTracking = true;
		}
	}

	/**
	 * Saca por pantalla una representación gráfica del tablero.
	 */
	private void log() {
		System.out.println(this.board.toString());
	}

	// --------------------------------------------------------- Public Methods

	/**
	 * Aplica el algoritmo de <a
	 * href="http://es.wikipedia.org/wiki/Vuelta_Atr%C3%A1s">"vuelta atrás"</a>
	 * para la resolución del problema.
	 * 
	 * @param board
	 *            tablero de juego sobre el que se aplica el algoritmo con la
	 *            finalidad de resolverlo.
	 */
	public void apply(Board board) {

		long init = System.currentTimeMillis();

		int x = 0;
		int y = 0;

		this.board = board;
		try {
			solve(x, y);
			System.out
					.println(new StringBuilder("El tablero no tiene solucion. ")
							.append(System.currentTimeMillis() - init).append(
									" milisegundos."));
		} catch (RuntimeException e) {
			log();
			System.out.println(new StringBuilder().append(e.getMessage())
					.append(System.currentTimeMillis() - init)
					.append(" milisegundos."));
		}
	}
}