# Hashiwokakero
Resolución en Java del puzzle lógico "Hashiwokakero" (https://es.wikipedia.org/wiki/Hashiwokakero)

# Descripción

Ejercicio propuesto como práctica de la asignatura de Programación III (curso 2009 - 2010) por la Escuela Técnica Superior de Ingeniería Informática de la UNED. [Ver documento adjunto](https://github.com/fraguio/Hashiwokakero/blob/master/doc/Practica-PIII-09-10.pdf).

# Notas sobre el proyecto

La implementación se realiza teniendo en cuenta todos los requerimientos descritos en la [documentación adjunta](https://github.com/fraguio/Hashiwokakero/blob/master/doc/Practica-PIII-09-10.pdf).

La solución se [implementa](https://github.com/fraguio/Hashiwokakero/blob/master/Hashiwokakero/src/main/java/es/fraguio/hashiwokakero/strategy/BackTracking.java) mediante la estrategia de [backtracking](https://es.wikipedia.org/wiki/Vuelta_atr%C3%A1s) además de otros [algoritmos](https://github.com/fraguio/Hashiwokakero/tree/master/Hashiwokakero/src/main/java/es/fraguio/hashiwokakero/strategy/enhancer) de optimización de tiempos en la búsqueda de la solución.

### Estructura repositorio

 - <b><i>Hashiwokakero</i></b>: Proyecto que se puede importar directamente desde Eclipse.
 - <b><i>doc</i></b>: Documento pdf con la descripción de la práctica.

### Ejecución

En la ruta [src/main/resources/boards](https://github.com/fraguio/Hashiwokakero/tree/master/Hashiwokakero/src/main/resources/boards) se encuentran varios tableros de juego sin resolver. Tal y como se indica en la [documentación](https://github.com/fraguio/Hashiwokakero/blob/master/doc/Practica-PIII-09-10.pdf) la sintaxis para la ejecución es:

<b><i>java hashiwokakero [-h] fichero</i></b>
en donde:

-h: modo ayuda. Muestra la sintaxis y los creditos.<br/>
fichero: el nombre del fichero que contiene los datos de entrada, el "tablero de juego"

A mayores de los requisitos de la práctica se ha añadido el parámetro opcional "-o" que ejecuta determinados algoritmos de optimización de tiempos en la búsqueda de solución.

-o: aplica algoritmos de optimización en la búsqueda de la solución.

Para pruebas desde un IDE se puede descomentar alguna de las líneas en donde se asigna valores a la variable 'args' del método es.fraguio.hashiwokakero.Hashiwokakero#main(String[])

# Javadoc

Se puede consultar en [este enlace](https://fraguio.github.io/Hashiwokakero/javadoc/index.html)



