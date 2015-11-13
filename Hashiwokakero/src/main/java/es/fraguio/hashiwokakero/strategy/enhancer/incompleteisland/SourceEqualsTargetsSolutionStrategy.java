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

import es.fraguio.hashiwokakero.strategy.enhancer.AbstractSolutionStrategy;
import es.fraguio.hashiwokakero.strategy.enhancer.IBridgeDesigner;
import es.fraguio.hashiwokakero.strategy.enhancer.ISourceIslandSelector;

/**
 * Selecciona las islas cuyo criterio define este
 * {@link IncompleteIslandSelector#isMatchingCriteria(es.fraguio.hashiwokakero.board.cell.IslandCell)
 * selector} y construye los tipos de puente que determina este
 * {@link IncompleteIslandBridgeDesigner#getBridgeDesigns(es.fraguio.hashiwokakero.board.Board, es.fraguio.hashiwokakero.board.cell.IslandCell)
 * dise�ador}.
 * 
 * @author Eduardo Nogueira Fragu�o
 */
public class SourceEqualsTargetsSolutionStrategy extends
		AbstractSolutionStrategy {

	// ----------------------------------------------------------- Constructors

	/**
	 * Constructor.
	 */
	public SourceEqualsTargetsSolutionStrategy() {
		super();
	}

	// ------------------------------------------------------ Protected Methods

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected IBridgeDesigner getBridgeDesigner() {
		return IncompleteIslandBridgeDesigner.INSTANCE;
	}

	/**
	 * {@inheritDoc}
	 */
	protected ISourceIslandSelector[] getSourceSelectionStrategies() {
		return new ISourceIslandSelector[] { IncompleteIslandSelector.INSTANCE };
	}
}