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
package es.fraguio.hashiwokakero.strategy.enhancer.bridgesnumber;

import es.fraguio.hashiwokakero.strategy.enhancer.AbstractSolutionStrategy;
import es.fraguio.hashiwokakero.strategy.enhancer.IBridgeDesigner;
import es.fraguio.hashiwokakero.strategy.enhancer.ISourceIslandSelector;

/**
 * @author Eduardo Nogueira Fragu�o
 */
public class BridgesNumberSolutionStrategy extends AbstractSolutionStrategy {

	// ----------------------------------------------------------- Constructors

	/**
	 * Constructor privado que impide que se creen instancias de la clase desde
	 * fuera de la misma.
	 */
	public BridgesNumberSolutionStrategy() {
		super();
	}

	// ------------------------------------------------------ Protected Methods

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected IBridgeDesigner getBridgeDesigner() {
		return BridgesNumberBridgeDesigner.INSTANCE;
	}

	/**
	 * {@inheritDoc}
	 */
	protected ISourceIslandSelector[] getSourceSelectionStrategies() {
		return new ISourceIslandSelector[] { UnbuiltBridgesNumberSelector.INSTANCE };
	}
}