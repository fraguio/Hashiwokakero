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
package es.fraguio.hashiwokakero.board.cell;

import es.fraguio.hashiwokakero.board.cell.enums.CellAlign;
import es.fraguio.hashiwokakero.board.cell.enums.Direction;

/**
 * Celda que representa una isla.
 * 
 * @author Eduardo Nogueira Fraguío
 */
public class IslandCell extends Cell {

	// ---------------------------------------------------- Instance Properties

	/** Número total de puentes que tiene la isla. */
	private int totalBridges;

	/** Número de puentes establecidos. */
	private int buildBridges;

	// ----------------------------------------------------------- Constructors

	/**
	 * Constructor con parámetros.
	 * 
	 * @param coordinate
	 *            coordenadas de posición de la isla.
	 * @param cellAlign
	 *            alineamiento de la celda.
	 * @param totalBridges
	 *            número total de puentes que tiene la isla.
	 */
	public IslandCell(Coordinate coordinate, CellAlign cellAlign,
			int totalBridges) {
		super(coordinate, cellAlign);
		this.totalBridges = totalBridges;
	}

	// --------------------------------------------------------- Public Methods

	/**
	 * Añade un puente al número de puentes establecidos en la isla.
	 */
	public void addBridge() {
		buildBridges++;
	}

	/**
	 * Elimina un puente del número de puentes establecidos en la isla.
	 */
	public void removeBridge() {
		buildBridges--;
	}

	/**
	 * Determina si la isla está completa en cuanto a puentes se refiere, es
	 * decir, si se han construido tantos puentes como se indica en el tablero.
	 * 
	 * @return <code>true</code> si la isla se ha completado. <code>false</code>
	 *         en caso contrario.
	 */
	public boolean isCompleted() {
		return (this.buildBridges == this.totalBridges);
	}

	/**
	 * Devuelve las posibles direcciones en las que se pueden trazar puentes,
	 * basándose en la {@link CellAlign alineación} de la isla dentro del
	 * tablero.
	 * 
	 * @return array de {@link Direction direcciones}.
	 */
	public Direction[] getPossibleDirections() {

		Direction[] directions;

		switch (this.getCellAlign()) {

		case LEFT_TOP:
			directions = new Direction[] { Direction.SOUTH, Direction.EAST };
			break;

		case TOP:
			directions = new Direction[] { Direction.SOUTH, Direction.EAST,
					Direction.WEST };
			break;

		case RIGHT_TOP:
			directions = new Direction[] { Direction.SOUTH, Direction.WEST };
			break;

		case LEFT:
			directions = new Direction[] { Direction.NORTH, Direction.SOUTH,
					Direction.EAST };
			break;

		case MIDDLE:
			directions = new Direction[] { Direction.NORTH, Direction.SOUTH,
					Direction.EAST, Direction.WEST };
			break;

		case RIGHT:
			directions = new Direction[] { Direction.NORTH, Direction.SOUTH,
					Direction.WEST };
			break;

		case LEFT_BOTTOM:
			directions = new Direction[] { Direction.NORTH, Direction.EAST };
			break;

		case BOTTOM:
			directions = new Direction[] { Direction.NORTH, Direction.EAST,
					Direction.WEST };
			break;

		default:
			directions = new Direction[] { Direction.NORTH, Direction.WEST };
			break;
		}

		return directions;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String renderToString() {
		return " " + String.valueOf(this.totalBridges) + " ";
	}

	public int getUnbuiltBridges() {
		return this.totalBridges - this.buildBridges;
	}

	/**
	 * Clona una isla.
	 * 
	 * @return clon de la isla.
	 */
	@Override
	public IslandCell clone() {
		IslandCell clone = new IslandCell(this.getCoordinate().clone(),
				this.getCellAlign(), this.getTotalBridges());
		clone.setBuildBridges(this.getBuildBridges());
		return clone;
	}

	// --------------------------------------------------- Properties Accessors

	/**
	 * @return the buildBridges
	 */
	public int getBuildBridges() {
		return buildBridges;
	}

	/**
	 * @param buildBridges
	 *            the buildBridges to set
	 */
	protected void setBuildBridges(int buildBridges) {
		this.buildBridges = buildBridges;
	}

	/**
	 * @return the totalBridges
	 */
	public int getTotalBridges() {
		return totalBridges;
	}

	/**
	 * @param totalBridges
	 *            the totalBridges to set
	 */
	public void setTotalBridges(int totalBridges) {
		this.totalBridges = totalBridges;
	}
}