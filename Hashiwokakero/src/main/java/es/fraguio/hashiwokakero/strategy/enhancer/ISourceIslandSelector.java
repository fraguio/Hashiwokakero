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

import es.fraguio.hashiwokakero.board.cell.IslandCell;

/**
 * Define estrategias para seleccionar una isla.
 * 
 * @author Eduardo Nogueira Fraguío
 */
public interface ISourceIslandSelector {

	/**
	 * Determina si una isla cumple el criterio de selección.
	 * 
	 * @param islandCell
	 *            isla para la que se comprueba si cumple el criterio de
	 *            selección.
	 * @return <code>true</code> si la isla cumple el criterio de selección.
	 *         <code>false</code> en caso contrario.
	 */
	boolean isMatchingCriteria(IslandCell islandCell);
}