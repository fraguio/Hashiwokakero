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

import es.fraguio.hashiwokakero.board.cell.IslandCell;
import es.fraguio.hashiwokakero.strategy.enhancer.ISourceIslandSelector;

/**
 * Implementaci�n de {@link ISourceIslandSelector} que forma parte de la
 * estrategia de soluci�n {@link SourceEqualsTargetsSolutionStrategy}
 * 
 * @see #isMatchingCriteria(IslandCell)
 * @author Eduardo Nogueira Fragu�o
 */
public enum IncompleteIslandSelector implements ISourceIslandSelector {

	INSTANCE;

	// --------------------------------------------------------- Public Methods

	/**
	 * Selecciona aquellas islas que est�n {@link IslandCell#isCompleted()
	 * incompletas.}
	 * 
	 * @param islandCell
	 *            isla que es objeto de la estrategia de selecci�n.
	 * @return <code>true</code> si cumple la estrategia de selecci�n.
	 */
	public boolean isMatchingCriteria(IslandCell islandCell) {
		return (!islandCell.isCompleted());
	}
}