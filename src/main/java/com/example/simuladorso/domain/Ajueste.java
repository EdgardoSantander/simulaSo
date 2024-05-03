	package com.example.simuladorso.domain;


	import java.util.*;

	 

	public class Ajueste {

	      public static int ultimoBloqueAsignado = 0;

	 

	      /**

	       * Implementaci�n del m�todo de primer ajuste.

	       *

	       * @param listaControl

	       *            - Lista de control.

	       * @param tamanyo

	       *            - Tama�o del hueco requerido.

	       * @return Devuelve el �ndice del lugar en el que se encuentra el primer

	       *         bloque libre con el que satisfacer la petici�n, o -1 en el caso

	       *         de no haber ninguno disponible.

	       */

	      public static int primerAjuste(List<int[]> listaControl, int tamanyo) {

	            int res = 0;

	            for (int[] bloque : listaControl) {

	                  if (bloque[0] == 0 && bloque[2] >= tamanyo) {

	                        break;

	                  }

	                  res++;

	            }

	            if (res == listaControl.size()) {

	                  res = -1;

	            }

	            return res;

	      }

	 

	      /**

	       * Implementaci�n del m�todo de siguiente ajuste.

	       *

	       * @param listaControl

	       *            - Lista de control.

	       * @param tamanyo

	       *            - Tamanyo del hueco requerido.

	       * @return Devuelve el �ndice del lugar en el que se encuentra el primer

	       *         bloque libre con el que satisfacer la petici�n, o -1 en el caso

	       *         de no haber ninguno disponible.

	       */

	      public static int siguienteAjuste(List<int[]> listaControl, int tamanyo) {

	            int res = ultimoBloqueAsignado;

	            boolean listaRecorrida=false;

	            while (res!=-1) {

	                  int bloque[] = listaControl.get(res);

	                  if (bloque[0] == 0 && bloque[2] >= tamanyo) {

	                        ultimoBloqueAsignado=res;

	                        break;

	                  }

	                  res++;

	                  if (res>=listaControl.size()) {

	                        if (listaRecorrida) {

	                             res=-1;

	                        } else {

	                             res%=listaControl.size();

	                             listaRecorrida=true;

	                        }

	                  }

	            }

	            return res;

	      }

	 

	      /**

	       * Implementaci�n del m�todo de mejor ajuste.

	       *

	       * @param listaControl

	       *            - Lista de control.

	       * @param tamanyo

	       *            - Tamanyo del hueco requerido.

	       * @return Devuelve el �ndice del lugar en el que se encuentra el primer

	       *         bloque libre con el que satisfacer la petici�n, o -1 en el caso

	       *         de no haber ninguno disponible.

	       */

	      public static int mejorAjuste(List<int[]> listaControl, int tamanyo) {

	            int i = 0;

	            int menorBloque = -1;

	            int tamMenorBloque = -1;

	            for (int[] bloque : listaControl) {

	                  if (bloque[0] == 0 && bloque[2] >= tamanyo

	                             && (menorBloque == -1 || bloque[2] < tamMenorBloque)) {

	                        menorBloque = i;

	                        tamMenorBloque = bloque[2];

	                  }

	                  i++;

	            }

	            return menorBloque;

	      }

	 

	      /**

	       * Implementaci�n del m�todo de peor ajuste.

	       *

	       * @param listaControl

	       *            - Lista de control.

	       * @param tamanyo

	       *            - Tamanyo del hueco requerido.

	       * @return Devuelve el �ndice del lugar en el que se encuentra el primer

	       *         bloque libre con el que satisfacer la petici�n, o -1 en el caso

	       *         de no haber ninguno disponible.

	       */

	      public static int peorAjuste(List<int[]> listaControl, int tamanyo) {

	            int i = 0;

	            int mayorBloque = -1;

	            int tamMayorBloque = -1;

	            for (int[] bloque : listaControl) {

	                  if (bloque[0] == 0 && bloque[2] >= tamanyo

	                             && (mayorBloque == -1 || bloque[2] > tamMayorBloque)) {

	                        mayorBloque = i;

	                        tamMayorBloque = bloque[2];

	                  }

	                  i++;

	            }

	            return mayorBloque;

	            }    

	 

	}
