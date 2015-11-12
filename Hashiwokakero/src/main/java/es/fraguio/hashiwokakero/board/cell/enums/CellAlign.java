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
package es.fraguio.hashiwokakero.board.cell.enums;

/**
 * Posición de la celda con respecto a los laterales del tablero.
 * 
 * @author Eduardo Nogueira Fraguío
 */
public enum CellAlign {

	/** Esquina superior izquierda */
	LEFT_TOP,
	/** Lateral superior */
	TOP,
	/** Esquina superior derecha */
	RIGHT_TOP,
	/** Lateral izquierda */
	LEFT,
	/** No está pegada a ningún lateral */
	MIDDLE,
	/** Lateral derecha */
	RIGHT,
	/** Esquina inferior izquierda */
	LEFT_BOTTOM,
	/** Lateral inferior */
	BOTTOM,
	/** Esquina inferior derecha */
	RIGHT_BOTTOM;
}