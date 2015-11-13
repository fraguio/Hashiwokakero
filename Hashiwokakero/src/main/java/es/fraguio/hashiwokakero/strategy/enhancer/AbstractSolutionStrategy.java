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
package es.fraguio.hashiwokakero.strategy.enhancer;

import java.util.Iterator;
import java.util.List;

import es.fraguio.hashiwokakero.board.Board;
import es.fraguio.hashiwokakero.board.cell.IslandCell;
import es.fraguio.hashiwokakero.strategy.BridgeDesign;

/**
 * Estrategia de solución abstracta que permita resolver total o parcialmente un
 * tablero. Las implementaciones que contiene este proyecto son soluciones
 * parciales pero inequívocas, en el trazado de puentes entre islas, con el fin
 * de optimizar los tiempos de resolución.Contiene un conjunto de
 * {@link ISourceIslandSelector estrategias de selección de islas de origen} y
 * un {@link IBridgeDesigner diseñador} de {@link BridgeDesign tipos de puente}
 * que se aplicarán a las islas de origen seleccionadas.
 * 
 * @author Eduardo Nogueira Fraguío
 */
public abstract class AbstractSolutionStrategy implements ISolutionStrategy {

	// ----------------------------------------------------------- Constructors

	/**
	 * Constructor sin argumentos.
	 */
	public AbstractSolutionStrategy() {
		super();
	}

	// ------------------------------------------------------ Protected Methods

	/**
	 * Devuelve un conjunto de selectores de isla que definien el criterio de
	 * selección mediante se elijen las islas apropiadas para aplicarles la
	 * estrategia de solución que representa una instancia de esta clase.
	 * 
	 * @return array de selectores de isla.
	 */
	protected abstract ISourceIslandSelector[] getSourceSelectionStrategies();

	/**
	 * Devuelve el diseñador de tipos de puente que permite aplicar la
	 * estrategia de solución que representa una instancia de esta clase.
	 * 
	 * @return {@link IBridgeDesigner}
	 */
	protected abstract IBridgeDesigner getBridgeDesigner();

	// --------------------------------------------------------- Public Methods

	/**
	 * {@inheritDoc}
	 */
	public int apply(Board board) {

		int num = 0;

		Iterator<IslandCell> iterator = board.incompleteIslandCellIterator();

		ISourceIslandSelector[] ssStrategies = getSourceSelectionStrategies();

		while (iterator.hasNext()) {
			IslandCell islandCell = iterator.next();
			for (ISourceIslandSelector iSourceSelectionStrategy : ssStrategies) {
				if (!(islandCell.isCompleted())
						&& iSourceSelectionStrategy
								.isMatchingCriteria(islandCell)) {

					IBridgeDesigner bridgeDesigner = getBridgeDesigner();
					List<BridgeDesign> bridgeDesigns = bridgeDesigner
							.getBridgeDesigns(board, islandCell);

					for (BridgeDesign bridgeDesign : bridgeDesigns) {
						board.createBridge(bridgeDesign);
						num += bridgeDesign.getNumBridges();
					}
				}
			}
		}

		return num;
	}
}