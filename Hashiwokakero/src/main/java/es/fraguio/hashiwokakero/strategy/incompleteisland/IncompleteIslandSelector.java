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
package es.fraguio.hashiwokakero.strategy.incompleteisland;

import es.fraguio.hashiwokakero.board.cell.IslandCell;
import es.fraguio.hashiwokakero.strategy.ISourceIslandSelector;

/**
 * Implementación de {@link ISourceIslandSelector} que forma parte de la
 * estrategia de solución {@link SourceEqualsTargetsSolutionStrategy}
 * 
 * @see #isMatchingCriteria(IslandCell)
 * @author Eduardo Nogueira Fraguío
 */
public enum IncompleteIslandSelector implements ISourceIslandSelector {

	INSTANCE;

	// --------------------------------------------------------- Public Methods

	/**
	 * Selecciona aquellas islas que estén {@link IslandCell#isCompleted()
	 * incompletas.}
	 * 
	 * @param islandCell
	 *            isla que es objeto de la estrategia de selección.
	 * @return <code>true</code> si cumple la estrategia de selección.
	 */
	public boolean isMatchingCriteria(IslandCell islandCell) {
		return (!islandCell.isCompleted());
	}
}