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

/**
 * Celda vacía, pero que podrá estar "cubierta" por algún puente.
 * 
 * @author Eduardo Nogueira Fraguío
 */
public class WaterCell extends Cell {

	// -------------------------------------------------------- Class Variables

	/** Carácter espacio en blanco */
	private static final Character BLANK_CHAR = Character.valueOf(' ');

	// ----------------------------------------------------------- Constructors

	/**
	 * Constructor
	 * 
	 * @param coordinate
	 *            coordenadas de posición de la celda.
	 * @param cellAlign
	 *            alineamiento de la celda.
	 */
	public WaterCell(Coordinate coordinate, CellAlign cellAlign) {
		super(coordinate, cellAlign);
	}

	// --------------------------------------------------------- Public Methods

	/**
	 * Clona una celda de agua.
	 * 
	 * @return clon de la celda.
	 */
	@Override
	public WaterCell clone() {
		return new WaterCell(this.getCoordinate().clone(), this.getCellAlign());

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String renderToString() {
		return " " + WaterCell.BLANK_CHAR.toString() + " ";
	}
}