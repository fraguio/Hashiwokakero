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

/**
 * Coordenadas que determinan una posición.
 * 
 * @author Eduardo Nogueira Fraguío
 */
public class Coordinate {

	// ---------------------------------------------------- Instance Properties

	/** Coordenada x */
	private int x;

	/** Coordenada y */
	private int y;

	// ----------------------------------------------------------- Constructors

	/**
	 * Constructor.
	 * 
	 * @param x
	 *            valor para la coordenada x.
	 * @param y
	 *            valor para la coordenada y.
	 */
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// --------------------------------------------------------- Public Methods

	/**
	 * Clona la coordenada.
	 * 
	 * @return clon de la coordenada.
	 */
	@Override
	public Coordinate clone() {
		return new Coordinate(this.x, this.y);

	}

	// --------------------------------------------------- Properties Accessors

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
}