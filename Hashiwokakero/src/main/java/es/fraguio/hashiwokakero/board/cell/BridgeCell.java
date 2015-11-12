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

import es.fraguio.hashiwokakero.board.cell.enums.Axis;
import es.fraguio.hashiwokakero.board.cell.enums.CellAlign;

/**
 * Celda que forma parte de un puente.
 * 
 * @author Eduardo Nogueira Fraguío
 */
public class BridgeCell extends Cell {

	// -------------------------------------------------------- Class Variables

	private static final Character DOUBLE_BRIDGE_NORTH_SOUTH = Character
			.valueOf('H');

	private static final Character DOUBLE_BRIDGE_EAST_WEST = Character
			.valueOf('=');

	private static final Character SINGLE_BRIDGE_NORTH_SOUTH = Character
			.valueOf('|');

	private static final Character SINGLE_BRIDGE_EAST_WEST = Character
			.valueOf('-');

	// ---------------------------------------------------- Instance Properties

	/** Determina si el puente es doble */
	private boolean doubleBridge = false;

	/** Eje sobre el que se traza el puente. */
	private Axis axis;

	// ----------------------------------------------------------- Constructors

	/**
	 * Constructor
	 * 
	 * @param coordinate
	 *            coordenadas de posición de la celda
	 * @param cellAlign
	 *            alineamiento de la celda.
	 * @param axis
	 *            Eje sobre el que se traza el puente.
	 */
	public BridgeCell(Coordinate coordinate, CellAlign cellAlign, Axis axis) {
		super(coordinate, cellAlign);
		this.axis = axis;
	}

	// --------------------------------------------------------- Public Methods

	/**
	 * Clona la celda.
	 * 
	 * @return clon de la celda.
	 */
	@Override
	public BridgeCell clone() {
		BridgeCell bridgeCell = new BridgeCell(this.getCoordinate().clone(),
				this.getCellAlign(), this.axis);
		bridgeCell.setDoubleBridge(this.isDoubleBridge());
		return bridgeCell;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String renderToString() {
		String render;
		if (isDoubleBridge()) {
			if (Axis.NORTH_SOUTH.equals(this.axis)) {
				render = DOUBLE_BRIDGE_NORTH_SOUTH.toString();
			} else {
				render = DOUBLE_BRIDGE_EAST_WEST.toString();
			}
		} else {
			if (Axis.NORTH_SOUTH.equals(this.axis)) {
				render = SINGLE_BRIDGE_NORTH_SOUTH.toString();
			} else {
				render = SINGLE_BRIDGE_EAST_WEST.toString();
			}
		}
		return " " + render + " ";
	}

	// --------------------------------------------------- Properties Accessors

	/**
	 * @return the axis
	 */
	public Axis getAxis() {
		return axis;
	}

	/**
	 * @param axis
	 *            the axis to set
	 */
	public void setAxis(Axis axis) {
		this.axis = axis;
	}

	/**
	 * @return the doubleBridge
	 */
	public boolean isDoubleBridge() {
		return doubleBridge;
	}

	/**
	 * @param doubleBridge
	 *            the doubleBridge to set
	 */
	public void setDoubleBridge(boolean doubleBridge) {
		this.doubleBridge = doubleBridge;
	}
}