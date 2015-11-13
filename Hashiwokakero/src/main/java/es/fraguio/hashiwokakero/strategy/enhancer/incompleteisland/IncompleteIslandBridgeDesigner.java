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
package es.fraguio.hashiwokakero.strategy.enhancer.incompleteisland;

import java.util.ArrayList;
import java.util.List;

import es.fraguio.hashiwokakero.board.Board;
import es.fraguio.hashiwokakero.board.OutOfBoardException;
import es.fraguio.hashiwokakero.board.cell.IslandCell;
import es.fraguio.hashiwokakero.board.cell.enums.Direction;
import es.fraguio.hashiwokakero.board.cell.exception.CellException;
import es.fraguio.hashiwokakero.strategy.BridgeDesign;
import es.fraguio.hashiwokakero.strategy.enhancer.IBridgeDesigner;
import es.fraguio.hashiwokakero.util.Constants;

/**
 * Implementaci�n de {@link IBridgeDesigner} que trabaja en conjunto con la
 * estrategia de selecci�n {@link IncompleteIslandSelector} para
 * construir la estrategia de soluci�n
 * {@link SourceEqualsTargetsSolutionStrategy}
 * 
 * @see #getBridgeDesigns(Board, IslandCell)
 * @author Eduardo Nogueira Fragu�o
 */
public enum IncompleteIslandBridgeDesigner implements IBridgeDesigner {

	INSTANCE;

	// --------------------------------------------------------- Public Methods

	/**
	 * Aplica el algoritmo definido en
	 * {@link IBridgeDesigner#getBridgeDesigns(Board, IslandCell)} pero,
	 * teniendo en cuenta que una instancia de {@link BridgeDesign} nos indica
	 * como trazar un puente simple o doble en una direcci�n determinada, s�lo
	 * devuelve la lista con instancias de {@link BridgeDesign} en los
	 * siguientes casos en los que el n�mero total de puentes que se van a
	 * trazar, la suma de {@link BridgeDesign#getNumBridges()} de todos los
	 * elementos de la lista que devuelve el m�todo, es igual al n�mero total de
	 * puentes que faltan por construir en la isla de origen
	 * {@link IslandCell#getUnbuiltBridges()}. En el resto de casos la lista de
	 * elementos que devuelve el m�todo est� vac�a, porque nadie nos asegura que
	 * podamos trazar los puentes de manera inequ�voca.
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
		int totalBridges = 0;
		for (Direction direction : directions) {
			IslandCell target;
			try {
				target = board.getNextIslandCell(source, direction);
				if (!target.isCompleted()) {
					final boolean isDouble = (target.getUnbuiltBridges() > 1) ? true
							: false;
					BridgeDesign bridgeDesign = new BridgeDesign(source,
							target, direction, isDouble);
					bridgeDesigns.add(bridgeDesign);
					totalBridges += bridgeDesign.getNumBridges();
				}
			} catch (CellException e) {

			} catch (OutOfBoardException e) {

			}
		}

		if (totalBridges != source.getUnbuiltBridges()) {
			bridgeDesigns = new ArrayList<BridgeDesign>(Constants.DEFAULT_SIZE);
		}
		return bridgeDesigns;
	}
}