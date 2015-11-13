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

import es.fraguio.hashiwokakero.board.cell.IslandCell;

/**
 * Implementación de {@link Iterator} que itera sobre las {@link IslandCell} del
 * tablero que no tienen todos sus puentes construídos.
 * 
 * @author Eduardo Nogueira Fraguío
 */
public class IncompleteIslandCellIterator implements Iterator<IslandCell> {

	// ---------------------------------------------------- Instance Properties

	/** Iterador del tablero de celdas de tipo isla */
	private IslandCellIterator islandCellBoardIterator;

	/**
	 * Siguiente celda que devuelve {@link IslandCellIterator#next()}
	 */
	private IslandCell nextIslandCell;

	// ----------------------------------------------------------- Constructors

	/**
	 * Constructor del iterador que recibe un iterador de celdas de tipo isla.
	 * 
	 * @param islandCellIterator
	 *            iterador sobre celdas de tipo isla.
	 */
	public IncompleteIslandCellIterator(IslandCellIterator islandCellIterator) {
		this.islandCellBoardIterator = islandCellIterator;
	}

	// --------------------------------------------------------- Public Methods

	/**
	 * {@inheritDoc}
	 */
	public boolean hasNext() {
		boolean hasNext = false;
		while (this.islandCellBoardIterator.hasNext()) {
			nextIslandCell = this.islandCellBoardIterator.next();
			if (!nextIslandCell.isCompleted()) {
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
		return nextIslandCell;
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