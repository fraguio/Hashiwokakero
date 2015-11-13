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
package es.fraguio.hashiwokakero.strategy.enhancer.islandposition;

import static es.fraguio.hashiwokakero.util.Constants.INT_6;
import es.fraguio.hashiwokakero.board.cell.IslandCell;
import es.fraguio.hashiwokakero.board.cell.enums.CellAlign;
import es.fraguio.hashiwokakero.strategy.enhancer.ISourceIslandSelector;

/**
 * Selecciona aquellas islas que est�n en un lateral del tablero y su n�mero
 * total de puentes sea 6. Sabremos con toda seguridad que trazando puentes
 * dobles en todas las direcciones posibles, �stos estar�n bien trazados.
 * 
 * @see #isMatchingCriteria(IslandCell)
 * 
 * @author Eduardo Nogueira Fragu�o
 */
public enum LateralCellsSelector implements ISourceIslandSelector {

	INSTANCE;

	// ---------------------------------------------------- Instance Properties

	/**
	 * Selecciona aquellas islas que est�n en un lateral del tablero y su n�mero
	 * total de puentes sea 6. Sabremos con toda seguridad que trazando puentes
	 * dobles en todas las direcciones posibles, �stos estar�n bien trazados.
	 * 
	 * @param islandCell
	 *            isla que es objeto de la estrategia de selecci�n.
	 * 
	 * @return <code>true</code> si cumple la estrategia de selecci�n.
	 */
	public boolean isMatchingCriteria(IslandCell islandCell) {
		boolean isMatchingCriteria = false;
		CellAlign islandCellAlign = islandCell.getCellAlign();
		if (CellAlign.TOP.equals(islandCellAlign)
				|| CellAlign.LEFT.equals(islandCellAlign)
				|| CellAlign.RIGHT.equals(islandCellAlign)
				|| CellAlign.BOTTOM.equals(islandCellAlign)) {

			if (islandCell.getTotalBridges() == INT_6) {
				isMatchingCriteria = true;
			}

		}
		return isMatchingCriteria;
	}
}