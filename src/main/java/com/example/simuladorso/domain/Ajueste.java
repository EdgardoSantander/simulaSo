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

		   public static int primerAjuste(List<Proceso> listaControl, int tamanyo) {
			int res = 0;
		
			for (Proceso bloque : listaControl) {
				if (bloque.getEstado() == 0 && bloque.getTamanio() >= tamanyo) {
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

		   public static int siguienteAjuste(List<Proceso> listaControl, int tamanyo) {
			int res = ultimoBloqueAsignado;
			boolean listaRecorrida = false;
		
			while (res != -1) {
				Proceso bloque = listaControl.get(res);
		
				if (bloque.getEstado() == 0 && bloque.getTamanio() >= tamanyo) {
					ultimoBloqueAsignado = res;
					break;
				}
		
				res++;
		
				if (res >= listaControl.size()) {
					if (listaRecorrida) {
						res = -1;
					} else {
						res %= listaControl.size();
						listaRecorrida = true;
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

		   public static int mejorAjuste(List<Proceso> listaControl, int tamanyo) {
			int menorBloque = -1;
			int tamMenorBloque = -1;
		
			for (int i = 0; i < listaControl.size(); i++) {
				Proceso bloque = listaControl.get(i);
				if (bloque.getEstado() == 0 && bloque.getTamanio() >= tamanyo
						&& (menorBloque == -1 || bloque.getTamanio() < tamMenorBloque)) {
					menorBloque = i;
					tamMenorBloque = bloque.getTamanio();
				}
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

		   public static int peorAjuste(List<Proceso> listaControl, float tamanyo) {
			int indice = -1;
			float tamMayorBloque = -1;
		
			for (int i = 0; i < listaControl.size(); i++) {
				Proceso proceso = listaControl.get(i);
				if (proceso.getEstado() == 0 && proceso.getTamanio() >= tamanyo) {
					if (indice == -1 || proceso.getTamanio() > tamMayorBloque) {
						indice = i;
						tamMayorBloque = proceso.getTamanio();
					}
				}
			}
		
			return indice;
		}
		
		

	 

	}
