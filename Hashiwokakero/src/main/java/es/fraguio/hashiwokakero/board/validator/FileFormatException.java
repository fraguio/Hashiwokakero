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
package es.fraguio.hashiwokakero.board.validator;

import static es.fraguio.hashiwokakero.util.Constants.RETURN;

/**
 * Se produce si los datos del fichero de entrada son incorrectos en su formato.
 * 
 * @author Eduardo Nogueira Fraguío
 */
public class FileFormatException extends Exception {
	// -------------------------------------------------------- Class Variables

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1237212962083729855L;

	// ----------------------------------------------------------- Constructors

	/**
	 * Constructor con el mensaje de error
	 * 
	 * @param message
	 *            mensaje de error que provoca la excepción.
	 */
	public FileFormatException(String message) {
		super(message);
	}

	// --------------------------------------------------------- Public Methods

	/**
	 * Devuelve el mensaje descriptivo del error que ha provocado la excepción.
	 * 
	 * @return mensaje de error.
	 */
	@Override
	public String toString() {
		final String ERR = "Error de validación de fichero de entrada !!!";
		StringBuilder toString = new StringBuilder(RETURN);
		toString.append(ERR);
		toString.append(RETURN);
		toString.append(getMessage());
		return toString.toString();
	}
}