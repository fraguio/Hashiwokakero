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

import es.fraguio.hashiwokakero.strategy.enhancer.AbstractSolutionStrategy;
import es.fraguio.hashiwokakero.strategy.enhancer.IBridgeDesigner;
import es.fraguio.hashiwokakero.strategy.enhancer.ISourceIslandSelector;

/**
 * Estrategia de soluci�n que, teniendo en cuenta la posici�n que ocupa una isla
 * y el n�mero de puentes que se trazan a partir de �sta, se aplica de forma
 * inequ�voca.
 * <p />
 * <ul>
 * <li>Islas que se encuentran en las esquinas del tablero y tienen 4 puentes</li>
 * <li>Islas que se encuentran en un lateral del tablero y tienen 6 puentes</li>
 * <li>Islas que tienen 8 puentes</li>
 * </ul>
 * 
 * @see CornersCellsSelector
 * @see LateralCellsSelector
 * @see MiddleCellsSelector
 * @see ContiguousCellsBridgeDesigner
 * 
 * @author Eduardo Nogueira Fragu�o
 */
public class IslandPositionSolutionStrategy extends AbstractSolutionStrategy {

	// ----------------------------------------------------------- Constructors

	/**
	 * Constructor.
	 */
	public IslandPositionSolutionStrategy() {
		super();
	}

	// ------------------------------------------------------ Protected Methods

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected IBridgeDesigner getBridgeDesigner() {
		return ContiguousCellsBridgeDesigner.INSTANCE;

	}

	/**
	 * {@inheritDoc}
	 */
	protected ISourceIslandSelector[] getSourceSelectionStrategies() {
		return new ISourceIslandSelector[] { MiddleCellsSelector.INSTANCE,
				CornersCellsSelector.INSTANCE, LateralCellsSelector.INSTANCE };
	}
}