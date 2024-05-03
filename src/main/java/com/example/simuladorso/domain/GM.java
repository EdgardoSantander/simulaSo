package com.example.simuladorso.domain;
import java.util.LinkedList;

import java.util.List;


public class GM {

	// //////////////////////////////////////////////////////////////

	// IMPLEMENTACION DE UNIDAD DE GESTION DE MEMORIA EN JAVA ///////

	//
	//

	// Esta es una implementacion funcional de los metodos de primer,

	// siguiente, mejor y peor ajuste.

	// En ella se supone una memoria de 32 kB y una gestion mediante

	// listas de control con ticks de 1 kB. Cada uno de los bloques de

	// la lista de control es una array de int de 4 valores, que son:

	// 0: Estado (Un valor de 0 significa libre, un valor de 1 ocupado)

	// 1: Direccion inicial del bloque

	// 2: Tamaño (En ticks)

	// 3: Pid del proceso al que esta asignado

	//

	// Para cambiar el tipo de ajuste que se aplica solo hay que

	// comentar/descomentar lineas de codigo de la funcion creaProceso.

	// Se puede cambiar el tamaño del mapa de memoria de 32 a cualquier

	// otro.

	// Para ver el estado de la memoria por pantalla se usa la funcion

	// imprimeMemoria(). Para crear y destruir procesos se usan las

	// funciones creaProceso y destruyeProceso, respectivamente.

	// En la funcion main (Al final de la clase) hay un ejemplo, con la

	// creacion y destruccion de varios procesos.

	public static final int LONGITUD_MEMORIA = 32;

	public static List<int[]> listaControl;

	public static int[] memoria;

	public static int ultimoPid;

	/* Inicia los atributos necesarios para el funcionamiento de la memoria. */

	public static void inicializaMemoria() {

		listaControl = new LinkedList<int[]>();

		memoria = new int[LONGITUD_MEMORIA];

		ultimoPid = 0;

		// A�ade una primera entrada a la lista de control, un hueco con el

		// tama�o de la memoria completa:

		int[] hueco = { 0, 0, LONGITUD_MEMORIA, 0 };

		listaControl.add(hueco);
		System.out.println("Inicializaste la memoria");
	}

	/**
	 * 
	 * Simula la escritura de datos en la memoria. Guarda los datos en una
	 * 
	 * array, a modo de mapa de bits.
	 *
	 * 
	 * 
	 * @param direccion
	 * 
	 *                  - En ticks, posicion en que empieza la escritura.
	 * 
	 * @par am tamanyo
	 * 
	 *      - Ticks que se extiende la escritura.
	 * 
	 * @param dato
	 * 
	 *             - Marca que se pondra en la memoria, que en un caso real seria
	 * 
	 *             parte del proceso. Por ejemplo, '1' para memoria ocupada y '0'
	 * 
	 *             para memoria vacia.
	 * 
	 */

	private static void escribeMemoria(int direccion, int tamanyo, int dato) {

		// Escribe el dato 'dato' en las posiciones pertinentes:

		for (int i = 0; i < tamanyo; i++) {

			memoria[direccion + i] = dato;

			// Aqui se puede incluir un delay para simular el acceso a memoria:

			/*
			 * 
			 * try { Thread.sleep(3); } catch (InterruptedException e) {
			 * 
			 * e.printStackTrace(); }
			 * 
			 */

		}

	}

	/**
	 * 
	 * Asigna un bloque de memoria a un nuevo proceso.
	 *
	 * 
	 * 
	 * @param pid
	 * 
	 *                - Identificador num�rico del proceso que se va a crear. El que
	 * 
	 *                la invoque tiene la responsabiidad de evitar que se repita.
	 * 
	 * @param tamanyo
	 * 
	 *                - Tama�o que necesita el proceso.
	 * 
	 * @return Devuelve <b>true</b> si se ha podido satisfacer la petici�n, o
	 * 
	 *         <b>false</b> en caso contrario.
	 * 
	 */

	public static boolean creaProceso(int pid, int tamanyo) {
		int hueco;
		boolean res = false;
		switch (4) {
			case 1:
				System.out.println("primer ajuste");
				hueco = Ajueste.primerAjuste(listaControl, tamanyo);
				res = (hueco != -1);

				if (res) {

					int direcc = listaControl.get(hueco)[1];

					int[] proceso = { 1, direcc, tamanyo, pid };

					int espacioRestante = listaControl.get(hueco)[2] - tamanyo;

					// Inserta el proceso en el lugar del hueco

					listaControl.set(hueco, proceso);

					// Si el proceso es m�s peque�o que el hueco, inserta un hueco

					if (espacioRestante > 0) {

						int[] bloqRestante = { 0, direcc + tamanyo, espacioRestante, 0 };

						listaControl.add(hueco + 1, bloqRestante);

					}

					escribeMemoria(direcc, tamanyo, 1);
				}
				break;
			case 2:
				hueco = Ajueste.siguienteAjuste(listaControl, tamanyo);
				System.out.println("siguiente ajuste");
				res = (hueco != -1);

				if (res) {

					int direcc = listaControl.get(hueco)[1];

					int[] proceso = { 1, direcc, tamanyo, pid };

					int espacioRestante = listaControl.get(hueco)[2] - tamanyo;

					// Inserta el proceso en el lugar del hueco

					listaControl.set(hueco, proceso);

					// Si el proceso es m�s peque�o que el hueco, inserta un hueco

					if (espacioRestante > 0) {

						int[] bloqRestante = { 0, direcc + tamanyo, espacioRestante, 0 };

						listaControl.add(hueco + 1, bloqRestante);

					}

					escribeMemoria(direcc, tamanyo, 1);
				}
				break;
			case 3:
				hueco = Ajueste.mejorAjuste(listaControl, tamanyo);
				System.out.println("mejor ajuste");
				res = (hueco != -1);

				if (res) {

					int direcc = listaControl.get(hueco)[1];

					int[] proceso = { 1, direcc, tamanyo, pid };

					int espacioRestante = listaControl.get(hueco)[2] - tamanyo;

					// Inserta el proceso en el lugar del hueco

					listaControl.set(hueco, proceso);

					// Si el proceso es m�s peque�o que el hueco, inserta un hueco

					if (espacioRestante > 0) {

						int[] bloqRestante = { 0, direcc + tamanyo, espacioRestante, 0 };

						listaControl.add(hueco + 1, bloqRestante);

					}

					escribeMemoria(direcc, tamanyo, 1);
				}
				break;
			case 4:
				hueco = Ajueste.peorAjuste(listaControl, tamanyo);
				System.out.println("peor ajuste");
				res = (hueco != -1);

				if (res) {

					int direcc = listaControl.get(hueco)[1];

					int[] proceso = { 1, direcc, tamanyo, pid };

					int espacioRestante = listaControl.get(hueco)[2] - tamanyo;

					// Inserta el proceso en el lugar del hueco

					listaControl.set(hueco, proceso);

					// Si el proceso es m�s peque�o que el hueco, inserta un hueco

					if (espacioRestante > 0) {

						int[] bloqRestante = { 0, direcc + tamanyo, espacioRestante, 0 };

						listaControl.add(hueco + 1, bloqRestante);

					}

					escribeMemoria(direcc, tamanyo, 1);
				}
				break;
			default:
				break;

		}

		// int hueco = Ajueste.siguienteAjuste(listaControl, tamanyo);

		// int hueco = Ajueste.mejorAjuste(listaControl, tamanyo);

		// int hueco = Ajueste.peorAjuste(listaControl, tamanyo);

		return res;

	}

	/**
	 * 
	 * Borra un proceso de la memoria.
	 *
	 * 
	 * 
	 * @param pid
	 * 
	 *            - Identificador num�rico del proceso que se pretende terminar.
	 * 
	 * @return Devuelve <b>true</b> si se ha podido satisfacer la petici�n, o
	 * 
	 *         <b>false</b> en caso contrario.
	 * 
	 */

	public static boolean destruyeProceso(int pid) {

		// Busca el �ndice del proceso en la lista de control

		int indice = 0;

		for (int[] bloque : listaControl) {

			indice++;

			if (bloque[3] == pid) {

				break;

			}

		}

		indice--;

		boolean encontrado = (indice != -1);

		// Si lo ha encontrado, lo borra de la memoria y de la lista de control

		if (encontrado) {

			int[] bloqueABorrar = listaControl.get(indice);

			listaControl.get(indice)[0] = 0;

			listaControl.get(indice)[3] = 0;

			escribeMemoria(bloqueABorrar[1], bloqueABorrar[2], 0);

			fusiona(indice);

			fusiona(indice - 1);

		}

		return encontrado;

	}

	/**
	 * 
	 * Fusiona un bloque de memoria de la lista de control con su vecino
	 * 
	 * posterior, si ambos son bloques libres.
	 *
	 * 
	 * 
	 * @param indice
	 * 
	 *               - Posici�n en la lista de control del primer bloque de la
	 * 
	 *               fusi�n, siendo el segundo indice + 1.
	 * 
	 * @return Devuelve <b>true</b> en el caso de que ambos bloques tengan
	 * 
	 *         �ndices v�lidos, y est�n vac�os, <b>false</b> en caso contrario.
	 * 
	 */

	private static boolean fusiona(int indice) {

		boolean fusionable = false;

		if (indice >= 0 && (indice + 1) < listaControl.size()) {

			fusionable = listaControl.get(indice)[0] == 0

					&& listaControl.get(indice + 1)[0] == 0;

		}

		if (fusionable) {

			int tamanyo = listaControl.remove(indice + 1)[2];

			listaControl.get(indice)[2] += tamanyo;

		}

		return fusionable;

	}

	/**
	 * 
	 * Muestra en pantalla el estado de la lista de control y la memoria.
	 * 
	 */

	public static String imprimeMemoria() {

		String s = "Lista de control: {[EST-DIR-TAM-PROC]:";

		for (int[] bloque : listaControl) {

			s += "[" + bloque[0] + "-" + bloque[1] + "-" + bloque[2] + "-"

					+ bloque[3];

			s += "]";

		}

		String x = "Memoria: ";

		for (int i : memoria) {

			x += i;

		}
		String cadena = s+"   "+x;
		return cadena;
		
	}

	// Este es el punto de entrada de la implementaci�n.

	// Se puede experimentar con las primitivas creadas.



}
