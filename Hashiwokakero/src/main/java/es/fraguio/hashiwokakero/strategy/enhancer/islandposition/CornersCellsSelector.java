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
package es.fraguio.hashiwokakero.strategy.enhancer.islandposition;

import static es.fraguio.hashiwokakero.util.Constants.INT_4;
import es.fraguio.hashiwokakero.board.cell.IslandCell;
import es.fraguio.hashiwokakero.board.cell.enums.CellAlign;
import es.fraguio.hashiwokakero.strategy.enhancer.ISourceIslandSelector;

/**
 * Estrategia de comienzo que selecciona aquellas islas que sabemos que tienen
 * puentes en todas direcciones, ya sea simples o dobles, y que no están en
 * ninguna esquina o ningún lateral del tablero.
 * 
 * @author Eduardo Nogueira Fraguío
 */
public enum CornersCellsSelector implements ISourceIslandSelector {

	INSTANCE;
	
	// ---------------------------------------------------- Instance Properties

	/**
	 * Selecciona aquellas islas que estén en una esquina del tablero y su
	 * número total de puentes sea 4. Sabremos con toda seguridad que trazando
	 * puentes dobles en todas las direcciones posibles, éstos estarán bien
	 * trazados.
	 * 
	 * @param islandCell
	 *            isla que es objeto de la estrategia de selección.
	 * 
	 * @return <code>true</code> si cumple la estrategia de selección.
	 */
	public boolean isMatchingCriteria(IslandCell islandCell) {
		boolean isMatchingCriteria = false;
		CellAlign islandCellAlign = islandCell.getCellAlign();
		if (CellAlign.LEFT_TOP.equals(islandCellAlign)
				|| CellAlign.RIGHT_TOP.equals(islandCellAlign)
				|| CellAlign.LEFT_BOTTOM.equals(islandCellAlign)
				|| CellAlign.RIGHT_BOTTOM.equals(islandCellAlign)) {

			if (islandCell.getTotalBridges() == INT_4) {
				isMatchingCriteria = true;
			}

		}
		return isMatchingCriteria;
	}
}