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
package es.fraguio.hashiwokakero.board;

import java.util.Iterator;

import es.fraguio.hashiwokakero.board.cell.Cell;
import es.fraguio.hashiwokakero.board.cell.IslandCell;

/**
 * Implementación de {@link Iterator} que itera sobre las {@link IslandCell} del
 * tablero.
 * 
 * @author Eduardo Nogueira Fraguío
 */
public class IslandCellIterator implements Iterator<IslandCell> {

	// ---------------------------------------------------- Instance Properties

	/** Iterador del tablero */
	private BoardIterator boardIterator;

	/**
	 * Siguiente celda que devuelve {@link IslandCellIterator#boardIterator
	 * #nextCell}
	 */
	private Cell nextCell;

	// ----------------------------------------------------------- Constructors

	/**
	 * Constructor del iterador que recibe el {@link BoardIterator iterador} del
	 * tablero.
	 * 
	 * @param boardIterator
	 *            iterador sobre todas las celdas del tablero.
	 */
	public IslandCellIterator(BoardIterator boardIterator) {
		this.boardIterator = boardIterator;
	}

	// --------------------------------------------------------- Public Methods

	/**
	 * {@inheritDoc}
	 */
	public boolean hasNext() {
		boolean hasNext = false;
		while (this.boardIterator.hasNext()) {
			nextCell = this.boardIterator.next();
			if (nextCell instanceof IslandCell) {
				hasNext = true;
				break;
			}
		}
		return hasNext;
	}

	/**
	 * Devuelve la siguiente {@link IslandCell celda de tipo isla}.
	 * 
	 * @return {@link IslandCell}.
	 */
	public IslandCell next() {
		return (IslandCell) nextCell;
	}

	/**
	 * Operación no soportada.
	 * 
	 * @throws UnsupportedOperationException
	 */
	public void remove() {
		throw new UnsupportedOperationException();
	}
}