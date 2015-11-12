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
package es.fraguio.hashiwokakero.util;

/**
 * Clase de utilidades.
 * 
 * @author Eduardo Nogueira Fragu�o
 */
public class Util {

	// -------------------------------------------------------- Private Methods

	/**
	 * Convierte un n�mero en base 10 a otra base.
	 * 
	 * @param base
	 *            a la que se desea convertir.
	 * @param num
	 *            n�mero que se desea convertir.
	 * @return n�mero convertido a otra base num�rica.
	 */
	private static String toNumericSystemString(int base, int num) {
		StringBuffer sb = new StringBuffer();
		if (num < base) {
			return String.valueOf(num);
		} else {
			int dividend = num;
			do {
				sb.append(dividend % base);
				dividend = dividend / base;
				if (dividend < base) {
					sb.append(dividend);
				}
			} while (dividend >= base);
		}
		sb = sb.reverse();
		return sb.toString();
	}

	// --------------------------------------------------------- Public Methods

	/**
	 * Rellena una cadena con caracteres a�adidos por la izquierda hasta
	 * alcanzar una longitud.
	 * 
	 * @param str
	 *            cadena que se rellena
	 * @param size
	 *            longitud que se debe alcanzar
	 * @param padChar
	 *            car�cter de relleno.
	 * @return cadena de determinada longitud.
	 */
	public static String leftPad(String str, int size, char padChar) {
		String _return = str;
		while (_return.length() < size) {
			_return = padChar + _return;
		}
		return _return;
	}

	/**
	 * Convierte un n�mero a base 3.
	 * 
	 * @param num
	 *            n�mero que se convierte.
	 * @return cadena representando el n�mero convertido a base 3.
	 */
	public static String toTerciaryString(int num) {
		return toNumericSystemString(Constants.INT_3, num);
	}
}
