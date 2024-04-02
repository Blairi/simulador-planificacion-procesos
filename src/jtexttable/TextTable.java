package jtexttable; // El paquete al que pertenece la clase

import java.util.Arrays; // Importa la clase Arrays del paquete java.util

/**
 * @Kael cgdon@163.com // Información del autor con su dirección de correo electrónico
 * Biblioteca para mostrar tablas en consola // Descripción de la clase y su funcionalidad
 */
public class TextTable { // Declaración de la clase TextTable

  public final static String SEP = "\n"; // Declaración de una constante estática SEP con valor de salto de línea

  private String[] header; // Declaración de un array de Strings para el encabezado de la tabla

  private String[][] rows; // Declaración de una matriz de Strings para las filas de la tabla

  // Constructor de la clase que recibe el encabezado y las filas de la tabla
  public TextTable(String[] header, String[][] rows) {
    this.header = header; // Inicialización del encabezado
    this.rows = rows; // Inicialización de las filas
  }

  // Método para obtener el encabezado de la tabla
  public String[] getHeader() {
    return header; // Devuelve el encabezado
  }

  // Método para establecer el encabezado de la tabla
  public void setHeader(String[] header) {
    this.header = header; // Establece el encabezado
  }

  // Método para obtener las filas de la tabla
  public String[][] getRows() {
    return rows; // Devuelve las filas
  }

  // Método para establecer las filas de la tabla
  public void setRows(String[][] rows) {
    this.rows = rows; // Establece las filas
  }

  // Método toString que retorna una representación de la tabla en forma de String
  @Override
  public String toString() {
    StringBuffer buff = new StringBuffer(); // Creación de un StringBuffer para construir la representación de la tabla

    // Calcular longitud de cada columna
    int[] lens = calcLenOfColumn(header, rows); // Llama al método para calcular las longitudes de las columnas

    // Construir línea de separación
    String splitLine = buildSplitLine(lens); // Llama al método para construir la línea de separación

    // Imprimir línea de separación
    append(buff, splitLine); // Agrega la línea de separación al StringBuffer

    // Imprimir encabezado
    append(buff, buildArray(lens, header)); // Agrega el encabezado al StringBuffer

    // Imprimir línea de separación
    append(buff, splitLine); // Agrega la línea de separación al StringBuffer

    // Iterar sobre las filas e imprimir cada una
    for (String[] row : rows) { // Itera sobre cada fila de la tabla
      append(buff, buildArray(lens, row)); // Agrega la fila al StringBuffer
      append(buff, splitLine); // Agrega la línea de separación después de cada fila
    }
    return buff.toString(); // Retorna la representación de la tabla como String
  }

  // Método privado para agregar una línea al StringBuffer
  private void append(StringBuffer buff, String line) {
    buff.append(line).append(SEP); // Agrega la línea al StringBuffer seguido del separador de línea
  }

  // Método privado para construir la línea de separación
  private String buildSplitLine(int[] lens) {
    StringBuilder buff = new StringBuilder(); // Crea un StringBuilder para construir la línea de separación
    buff.append("+"); // Agrega el primer caracter de la línea de separación
    for (int i = 0; i < lens.length; i++) { // Itera sobre las longitudes de las columnas
      buff.append(buildStrWithFixCharAndLength('-', lens[i] + 2)); // Agrega los caracteres para la línea de separación
      buff.append("+"); // Agrega el caracter de cierre de la línea de separación
    }
    return buff.toString(); // Retorna la línea de separación como String
  }

  // Método privado para construir una fila de la tabla
  private String buildArray(int[] lens, String[] row) {
    StringBuffer buff = new StringBuffer(); // Crea un StringBuffer para construir la fila
    buff.append("|"); // Agrega el delimitador de inicio de la fila
    for (int i = 0; i < header.length; i++) { // Itera sobre las columnas de la fila
      String s = null; // Inicializa la cadena a imprimir
      if (i < row.length) { // Verifica si la columna está presente en la fila
        s = row[i]; // Obtiene el contenido de la columna
      }
      buff.append(" "); // Agrega un espacio antes del contenido de la celda
      buff.append(polishing(s, lens[i])); // Agrega el contenido de la celda después de aplicarle formato
      buff.append(" |"); // Agrega el delimitador de fin de la celda
    }
    return buff.toString(); // Retorna la fila como String
  }

  // Método privado para calcular las longitudes de las columnas
  private int[] calcLenOfColumn(String[] header, String[][] rows) {
    int[] lens = new int[header.length]; // Crea un array para almacenar las longitudes de las columnas
    Arrays.fill(lens, 0); // Inicializa todas las longitudes a 0
    for (int i = 0; i < header.length; i++) { // Itera sobre las columnas del encabezado
      lens[i] = Math.max(stringLen(header[i]), lens[i]); // Calcula la longitud máxima de la columna
    }
    for (String[] row : rows) { // Itera sobre las filas de la tabla
      for (int i = 0; i < header.length; i++) { // Itera sobre las columnas de la fila
        if (i < row.length) { // Verifica si la columna está presente en la fila
          lens[i] = Math.max(stringLen(row[i]), lens[i]); // Calcula la longitud máxima de la columna
        }
      }
    }
    return lens; // Retorna las longitudes de las columnas
  }

  /**
   * Calcula la longitud de una cadena teniendo en cuenta caracteres multibyte.
   * @param s La cadena de entrada.
   * @return La longitud de la cadena.
   */
  public static int stringLen(String s) {
    if (s == null) { // Verifica si la cadena es nula
      return 0; // Retorna longitud 0
    }
    int len = 0; // Inicializa la longitud de la cadena
    String ch = "[\u0391-\uFFE5]"; // Define un rango de caracteres Unicode
    String ch2 = "[\u00B7]"; // Define otro rango de caracteres Unicode
    for (int i = 0; i < s.length(); i++) { // Itera sobre cada carácter de la cadena
      String tmp = s.substring(i, i + 1); // Obtiene el carácter en la posición actual
      if (tmp.matches(ch) || tmp.matches(ch2)) { // Verifica si el carácter es de un rango Unicode específico
        len += 2; // Si es de ese rango, incrementa la longitud en 2 (caracteres multibyte)
      } else {
        len += 1; // Si no es de ese rango, incrementa la longitud en 1 (caracteres de un byte)
      }
    }
    return len; // Retorna la longitud total de la cadena
  }

  // Método privado para construir una cadena con un carácter específico y una longitud determinada
  private String buildStrWithFixCharAndLength(char c, int len) {
    char[] cs = new char[len]; // Crea un array de caracteres con la longitud especificada
    Arrays.fill(cs, c); // Llena el array con el carácter especificado
    return new String(cs); // Retorna la cadena construida a partir del array de caracteres
  }

  /**
   * Añade espacios en blanco después de una cadena para que coincida con una longitud específica.
   * @param str La cadena de entrada.
   * @param destLength La longitud deseada de la cadena.
   * @return La cadena formateada.
   */
  private String polishing(String str, int destLength) {
    if (str == null) { // Verifica si la cadena es nula
      return buildStrWithFixCharAndLength(' ', destLength); // Retorna una cadena de espacios en blanco con la longitud deseada
    }
    int len = stringLen(str); // Calcula la longitud de la cadena
    if (len < destLength) { // Verifica si la longitud actual es menor que la longitud deseada
      int dif = destLength - len; // Calcula la diferencia de longitud
      char[] cs = new char[dif]; // Crea un array de caracteres con la diferencia de longitud
      Arrays.fill(cs, ' '); // Llena el array con espacios en blanco
      return str + new String(cs); // Concatena la cadena original con los espacios en blanco
    } else if (len == destLength) { // Verifica si la longitud actual es igual a la longitud deseada
      return str; // Si son iguales, retorna la cadena original sin cambios
    } else { // Si la longitud actual es mayor que la longitud deseada
      StringBuffer sb = new StringBuffer(); // Crea un StringBuffer para construir la cadena formateada
      char[] cs = str.toCharArray(); // Convierte la cadena en un array de caracteres
      for (int i = 0; i < cs.length; i++) { // Itera sobre cada carácter de la cadena
        if (stringLen(sb.toString() + cs[i]) >= destLength - 3) { // Verifica si la longitud acumulada supera la longitud deseada menos 3 (para dejar espacio para los puntos suspensivos)
          break; // Si supera la longitud deseada menos 3, termina el bucle
        }
        sb.append(cs[i]); // Agrega el carácter al StringBuffer
      }
      String s = sb.toString() + "..."; // Agrega puntos suspensivos al final de la cadena truncada
      len = stringLen(s); // Recalcula la longitud de la cadena truncada
      if (len < destLength) { // Verifica si la longitud truncada es menor que la longitud deseada
        int dif = destLength - len; // Calcula la diferencia de longitud
        cs = new char[dif]; // Crea un array de caracteres con la diferencia de longitud
        Arrays.fill(cs, ' '); // Llena el array con espacios en blanco
        return s + new String(cs); // Concatena la cadena truncada con los espacios en blanco
      }
      return s; // Retorna la cadena truncada con puntos suspensivos
    }
  }
}
