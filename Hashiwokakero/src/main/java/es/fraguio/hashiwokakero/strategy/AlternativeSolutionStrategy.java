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
package es.fraguio.hashiwokakero.strategy;

import es.fraguio.hashiwokakero.board.Board;

/**
 * Ejecuta un conjunto de {@link ISolutionStrategy estrategias de soluci�n} de
 * manera iterativa por ciclos completos, entendiendo por ciclo completo la
 * ejecuci�n de todas las {@link ISolutionStrategy estrategias de soluci�n} que
 * contiene una instancia de esta clase que recibe a trav�s de su
 * {@link AlternativeSolutionStrategy#AlternativeSolutionStrategy(ISolutionStrategy...)
 * constructor}. Esta estrategia de soluci�n deja de {@link #apply(Board)
 * aplicarse} en el momento que un ciclo completo no es capaz que construir
 * ning�n puente.
 * 
 * @author F1198 [Eduardo Nogueira Fragu�o - fraguio@openmailbox.org]
 *
 */
public class AlternativeSolutionStrategy implements ISolutionStrategy {

	// ---------------------------------------------------- Instance Properties

	private ISolutionStrategy[] solutionStrategies = null;

	private int totalBridges = 0;

	// ----------------------------------------------------------- Constructors

	/**
	 * Constructor que recibe el conjunto de {@link ISolutionStrategy
	 * estrategias de soluci�n} que se ejecutan por ciclos completos, tal y como
	 * se describe en la cabecera de la documentaci�n de esta clase.
	 * 
	 * @param solutionStrategies
	 *            conjunto de estrategias de soluci�n que se
	 *            {@link #apply(Board) aplica}.
	 */
	public AlternativeSolutionStrategy(ISolutionStrategy... solutionStrategies) {
		this.solutionStrategies = solutionStrategies;
	}
	
	// --------------------------------------------------------- Public Methods

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int apply(Board board) {

		int bridges = -1;
		int i = -1;
		boolean exit = false;

		while (exit == false) {
			ISolutionStrategy solutionStrategy = solutionStrategies[++i
					% solutionStrategies.length];
			bridges = solutionStrategy.apply(board);
			totalBridges += bridges;
			exit = (bridges == 0) && (i + 1) % solutionStrategies.length == 0;
		}

		return totalBridges;
	}
}