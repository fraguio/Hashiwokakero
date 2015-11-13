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

import static es.fraguio.hashiwokakero.util.Constants.INT_8;
import es.fraguio.hashiwokakero.board.cell.IslandCell;
import es.fraguio.hashiwokakero.board.cell.enums.CellAlign;
import es.fraguio.hashiwokakero.strategy.enhancer.ISourceIslandSelector;

/**
 * Selecciona aquellas islas cuyo número total de puentes es igual a 8 y que no
 * están en ninguna esquina o ningún lateral del tablero. Sabremos con toda
 * seguridad que trazando puentes dobles en todas las direcciones posibles,
 * éstos estarán bien trazados.
 * 
 * @see #isMatchingCriteria(IslandCell)
 * 
 * @author Eduardo Nogueira Fraguío
 */
public enum MiddleCellsSelector implements ISourceIslandSelector {

	INSTANCE;

	// --------------------------------------------------------- Public Methods

	/**
	 * Selecciona aquellas islas cuyo número total de puentes es igual a 8 y que
	 * no están en ninguna esquina o ningún lateral del tablero. Sabremos con
	 * toda seguridad que trazando puentes dobles en todas las direcciones
	 * posibles, éstos estarán bien trazados.
	 */
	public boolean isMatchingCriteria(IslandCell islandCell) {

		boolean isMatchingCriteria = false;
		if (CellAlign.MIDDLE.equals(islandCell.getCellAlign())) {
			if (islandCell.getTotalBridges() == INT_8) {
				isMatchingCriteria = true;
			}
		}
		return isMatchingCriteria;
	}
}