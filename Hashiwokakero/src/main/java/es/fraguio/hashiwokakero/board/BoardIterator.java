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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import es.fraguio.hashiwokakero.board.cell.Cell;

/**
 * Iterador sobre todas las celdas del tablero.
 * 
 * @author Eduardo Nogueira Fraguío
 */
public class BoardIterator implements Iterator<Cell> {

	// ---------------------------------------------------- Instance Properties

	/** Iterador */
	private Iterator<Cell> iterator;

	// ----------------------------------------------------------- Constructors

	/**
	 * Constructor que recibe las celdas del tablero sobre las que se itera.
	 * 
	 * @param cells
	 *            celdas del tablero.
	 */
	public BoardIterator(Cell[][] cells) {
		List<Cell> tmp = new ArrayList<Cell>();
		for (int i = 0; i < cells.length; i++) {
			tmp.addAll(Arrays.asList(cells[i]));
		}
		iterator = tmp.iterator();
	}

	/**
	 * Determina si existen más celdas sobre las que iterar.
	 * 
	 * @return <code>true</code> si existen más celdas.
	 */
	public boolean hasNext() {
		return iterator.hasNext();
	}

	/**
	 * Devuelve la siguiente celda del tablero.
	 * 
	 * @return {@link Cell}
	 */
	public Cell next() {
		return iterator.next();
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
