/*
 * Copyright 2010-2015 Eduardo Nogueira Fragu�o.
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
package es.fraguio.hashiwokakero.strategy.bridgesnumber;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import es.fraguio.hashiwokakero.board.Board;
import es.fraguio.hashiwokakero.board.OutOfBoardException;
import es.fraguio.hashiwokakero.board.cell.IslandCell;
import es.fraguio.hashiwokakero.board.cell.enums.Direction;
import es.fraguio.hashiwokakero.board.cell.exception.CellException;
import es.fraguio.hashiwokakero.strategy.BridgeDesign;
import es.fraguio.hashiwokakero.strategy.IBridgeDesigner;
import es.fraguio.hashiwokakero.util.Constants;

/**
 * Implementaci�n de {@link IBridgeDesigner} que trabaja en conjunto con la
 * estrategia de selecci�n {@link UnbuiltBridgesNumberSelector} para construir
 * la estrategia de soluci�n {@link BridgesNumberSolutionStrategy}
 * 
 * @see #getBridgeDesigns(Board, IslandCell)
 * @author Eduardo Nogueira Fragu�o
 */
public enum BridgesNumberBridgeDesigner implements IBridgeDesigner {

	INSTANCE;

	// --------------------------------------------------------- Public Methods

	/**
	 * Aplica el algoritmo definido en
	 * {@link IBridgeDesigner#getBridgeDesigns(Board, IslandCell)} pero,
	 * teniendo en cuenta que una instancia de {@link BridgeDesign} nos indica
	 * como trazar un puente simple o doble en una direcci�n determinada, s�lo
	 * devuelve la lista con instancias de {@link BridgeDesign} en los
	 * siguientes casos:
	 * <ul>
	 * <li>Si el n�mero de elementos de la lista de retorno es = 1 y el n�mero
	 * de puentes que faltan por trazar en la isla de origen tambi�n es = 1</li>
	 * <li>Si el n�mero de elementos de la lista de retorno es = 1 y el n�mero
	 * de puentes que faltan por trazar en la isla de origen tambi�n es = 2</li>
	 * <li>Si el n�mero de elementos de la lista de retorno es = 2 y el n�mero
	 * de puentes que faltan por trazar en la isla de origen tambi�n es = 4</li>
	 * <li>Si el n�mero de elementos de la lista de retorno es = 3 y el n�mero
	 * de puentes que faltan por trazar en la isla de origen tambi�n es = 5</li>
	 * <li>Si el n�mero de elementos de la lista de retorno es = 4 y el n�mero
	 * de puentes que faltan por trazar en la isla de origen tambi�n es = 8</li>
	 * </ul>
	 * En el resto de casos la lista de elementos que devuelve el m�todo est�
	 * vac�a, porque nadie nos asegura que podamos trazar los puentes de manera
	 * inequ�voca.
	 * 
	 * @param board
	 *            tablero de juego.
	 * @param source
	 *            isla de origen.
	 * @return lista de {@link BridgeDesign dise�os} que se pueden construir de
	 *         manera inequ�voca.
	 */
	public List<BridgeDesign> getBridgeDesigns(Board board, IslandCell source) {

		List<BridgeDesign> bridgeDesigns = new ArrayList<BridgeDesign>(
				Constants.DEFAULT_SIZE);
		Direction[] directions = source.getPossibleDirections();
		for (Direction direction : directions) {
			IslandCell target;
			try {
				target = board.getNextIslandCell(source, direction);
				if (!target.isCompleted()) {
					bridgeDesigns.add(new BridgeDesign(source, target,
							direction,
							(source.getUnbuiltBridges() == BigDecimal.ONE
									.intValue()) ? false : true));
				}
			} catch (CellException e) {

			} catch (OutOfBoardException e) {

			}
		}

		switch (source.getUnbuiltBridges()) {
		case 1:
			if (BigDecimal.ONE.intValue() != bridgeDesigns.size()) {
				bridgeDesigns = new ArrayList<BridgeDesign>(
						Constants.DEFAULT_SIZE);
			}
			break;
		case 2:
			if (BigDecimal.ONE.intValue() != bridgeDesigns.size()) {
				bridgeDesigns = new ArrayList<BridgeDesign>(
						Constants.DEFAULT_SIZE);
			}
			break;
		case 4:
			if (Constants.INT_2 != bridgeDesigns.size()) {
				bridgeDesigns = new ArrayList<BridgeDesign>(
						Constants.DEFAULT_SIZE);
			}
			break;
		case 6:
			if (Constants.INT_3 != bridgeDesigns.size()) {
				bridgeDesigns = new ArrayList<BridgeDesign>(
						Constants.DEFAULT_SIZE);
			}
			break;
		case 8:
			if (Constants.INT_4 != bridgeDesigns.size()) {
				bridgeDesigns = new ArrayList<BridgeDesign>(
						Constants.DEFAULT_SIZE);
			}
			break;
		default:
			bridgeDesigns = new ArrayList<BridgeDesign>(Constants.DEFAULT_SIZE);
		}
		return bridgeDesigns;
	}
}