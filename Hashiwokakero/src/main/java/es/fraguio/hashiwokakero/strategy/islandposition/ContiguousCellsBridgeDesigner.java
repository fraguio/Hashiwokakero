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
package es.fraguio.hashiwokakero.strategy.islandposition;

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
 * Implementación de {@link IBridgeDesigner} que trabaja en conjunto con las
 * estrategias de selección {@link MiddleCellsSelector},
 * {@link CornersCellsSelector}, {@link LateralCellsSelector}
 * para construir la estrategia de solución
 * {@link IslandPositionSolutionStrategy}
 * 
 * @see #getBridgeDesigns(Board, IslandCell)
 * @author Eduardo Nogueira Fraguío
 */
public enum ContiguousCellsBridgeDesigner implements IBridgeDesigner {

	INSTANCE;
	
	// --------------------------------------------------------- Public Methods

	/**
	 * Aplica el algoritmo definido en
	 * {@link IBridgeDesigner#getBridgeDesigns(Board, IslandCell)} y comprueba
	 * que la isla de destino no {@link IslandCell#isCompleted() no esté
	 * completa}, para añadir a la lista de retorno una instancia de
	 * {@link BridgeDesign}
	 * 
	 * @param board
	 *            tablero de juego.
	 * @param source
	 *            isla de origen.
	 * @return lista de {@link BridgeDesign diseños} que se pueden construir de
	 *         manera inequívoca.
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
							direction, true));
				}
			} catch (CellException e) {

			} catch (OutOfBoardException e) {

			}
		}
		return bridgeDesigns;
	}
}