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
package es.fraguio.hashiwokakero.strategy.bridgesnumber;

import java.math.BigDecimal;

import es.fraguio.hashiwokakero.board.cell.IslandCell;
import es.fraguio.hashiwokakero.strategy.ISourceIslandSelector;
import es.fraguio.hashiwokakero.util.Constants;

/**
 * Implementaci�n de {@link ISourceIslandSelector} que forma parte de la
 * estrategia de soluci�n {@link BridgesNumberSolutionStrategy}
 * 
 * @see #isMatchingCriteria(IslandCell)
 * @author Eduardo Nogueira Fragu�o
 */
public enum UnbuiltBridgesNumberSelector implements ISourceIslandSelector {

	INSTANCE;

	// ---------------------------------------------------- Instance Properties

	/**
	 * Comprueba si se cumple la condici�n establecida por la estrategia de
	 * selecci�n que, en esta instancia, cumplen aquellas islas donde el n�mero
	 * de puentes que {@link IslandCell#getUnbuiltBridges() faltan por
	 * construir} sea igual a 1, 2, 4, 6 u 8.
	 * 
	 * @param islandCell
	 *            isla que es objeto de an�lisis.
	 * @return <code>true</code> si se cumple la condici�n establecida por la
	 *         estrategia de selecci�n.
	 */
	public boolean isMatchingCriteria(IslandCell islandCell) {
		return (islandCell.getUnbuiltBridges() == BigDecimal.ONE.intValue()
				|| islandCell.getUnbuiltBridges() == Constants.INT_2
				|| islandCell.getUnbuiltBridges() == Constants.INT_4
				|| islandCell.getUnbuiltBridges() == Constants.INT_6 || islandCell
					.getUnbuiltBridges() == Constants.INT_8);
	}
}