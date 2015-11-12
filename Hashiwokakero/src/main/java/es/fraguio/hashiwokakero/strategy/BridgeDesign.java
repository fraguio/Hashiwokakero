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
package es.fraguio.hashiwokakero.strategy;

import es.fraguio.hashiwokakero.board.cell.IslandCell;
import es.fraguio.hashiwokakero.board.cell.enums.Direction;

/**
 * Contiene toda la información necesaria para "construir" un puente.
 * 
 * @author Eduardo Nogueira Fraguío
 */
public class BridgeDesign {

	// ---------------------------------------------------- Instance Properties

	private Direction direction;

	private IslandCell source;

	private IslandCell target;

	boolean isDouble;

	// ----------------------------------------------------------- Constructors

	/**
	 * Constructor.
	 * 
	 * @param source
	 *            isla de origen del puente.
	 * @param target
	 *            isla de destino del puente.
	 * @param direction
	 *            dirección en la que se traza el puente.
	 * @param isDouble
	 *            determina si el puente es simple o doble.
	 * @see IslandCell
	 * @see Direction
	 */
	public BridgeDesign(IslandCell source, IslandCell target,
			Direction direction, boolean isDouble) {

		super();
		this.source = source;
		this.target = target;
		this.direction = direction;
		this.isDouble = isDouble;
	}

	// --------------------------------------------------------- Public Methods

	/**
	 * Devuelve el número de puentes que se trazan, 2 ó 1, en función de si el
	 * puente es doble o no.
	 * 
	 * @return número de puentes.
	 */
	public int getNumBridges() {
		return (isDouble ? 2 : 1);
	}

	// --------------------------------------------------- Properties Accessors

	/**
	 * @return the source
	 */
	public IslandCell getSource() {
		return source;
	}

	/**
	 * @return the target
	 */
	public IslandCell getTarget() {
		return target;
	}

	/**
	 * @return the direction
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * @return the isDouble
	 */
	public boolean isDouble() {
		return isDouble;
	}
}