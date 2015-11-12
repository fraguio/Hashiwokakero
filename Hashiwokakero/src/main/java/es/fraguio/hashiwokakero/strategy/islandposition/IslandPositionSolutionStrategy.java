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

import es.fraguio.hashiwokakero.strategy.AbstractSolutionStrategy;
import es.fraguio.hashiwokakero.strategy.IBridgeDesigner;
import es.fraguio.hashiwokakero.strategy.ISourceIslandSelector;

/**
 * @author Eduardo Nogueira Fraguío
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