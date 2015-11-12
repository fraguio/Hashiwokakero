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
package es.fraguio.hashiwokakero.board.cell.exception;

/**
 * Se produce cuando se intenta construir un nuevo puente en una direcci�n
 * determinada y ya existe un puente doble en esa direcci�n.
 * 
 * @author Eduardo Nogueira Fragu�o
 */
public class DoubleBridgeException extends CellException {

	// -------------------------------------------------------- Class Variables

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -740686748030713370L;

}
