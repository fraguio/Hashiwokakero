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

import java.util.List;

import es.fraguio.hashiwokakero.board.Board;
import es.fraguio.hashiwokakero.board.cell.IslandCell;

/**
 * Diseña los tipos de puente que se puden trazar a partir de una isla de
 * origen.
 * 
 * @author Eduardo Nogueira Fraguío
 */
public interface IBridgeDesigner {

	/**
	 * Devuelve los tipos de puente que se puden trazar a partir de una isla de
	 * origen.
	 * 
	 * @param board
	 *            tablero de juego.
	 * @param source
	 *            isla de origen.
	 * @return lista de {@link BridgeDesign} posibles.
	 */
	List<BridgeDesign> getBridgeDesigns(Board board, IslandCell source);
}