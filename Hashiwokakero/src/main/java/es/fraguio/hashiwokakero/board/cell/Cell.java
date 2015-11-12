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
 * Clase abstracta que representa una celda en el tablero.
 * 
 * @author Eduardo Nogueira Fraguío
 */
public abstract class Cell {

	// ---------------------------------------------------- Instance Properties

	/** Coordenadas de posición de la celda. */
	private Coordinate coordinate;

	/** Alineamiento de la celda con respecto a los laterales del tablero. */
	private CellAlign cellAlign;

	// ----------------------------------------------------------- Constructors

	/**
	 * Constructor
	 * 
	 * @param coordinate
	 *            coordenadas de posición de la celda.
	 * @param cellAlign
	 *            alineamiento de la celda.
	 */
	public Cell(Coordinate coordinate, CellAlign cellAlign) {
		super();
		this.coordinate = coordinate;
		this.cellAlign = cellAlign;
	}

	// --------------------------------------------------------- Public Methods

	/**
	 * Clona la celda.
	 * 
	 * @return clon de la celda.
	 */
	@Override
	public abstract Cell clone();

	/**
	 * Devuelve la cadena con la que será representada la celda;
	 * 
	 * @return String representativa de la celda.
	 */
	public abstract String renderToString();

	// --------------------------------------------------- Properties Accessors

	/**
	 * @return the cellAlign
	 */
	public CellAlign getCellAlign() {
		return cellAlign;
	}

	/**
	 * @return the coordinate
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}

	/**
	 * @param coordinate
	 *            the coordinate to set
	 */
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}
}