/*
 * Copyright 2010-2015 Eduardo Nogueira Fragu�o.
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
package es.fraguio.hashiwokakero.strategy.enhancer;

import es.fraguio.hashiwokakero.board.Board;
import es.fraguio.hashiwokakero.strategy.BridgeDesign;

/**
 * Define una estrategia de soluci�n.
 * 
 * @author Eduardo Nogueira Fragu�o
 */
public interface ISolutionStrategy {

	/**
	 * Aplica las {@link BridgeDesign estrategias de construcci�n de puentes}
	 * aplicables y definidos por el {@link IBridgeDesigner dise�ador de
	 * puentes}. Construye los puentes desde la isla origen.
	 * 
	 * @param board
	 *            tablero de juego.
	 * 
	 * @return n�mero de puentes trazados.
	 */
	int apply(Board board);
}