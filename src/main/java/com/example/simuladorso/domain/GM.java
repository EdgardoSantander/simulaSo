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

	public static List<Proceso> listaControl;

	public static int[] memoria;

	public static int ultimoPid;


	

	/* Inicia los atributos necesarios para el funcionamiento de la memoria. */

	public static void inicializaMemoria() {

		listaControl = new LinkedList<Proceso>();

		memoria = new int[LONGITUD_MEMORIA];

		ultimoPid = 0;

		// A�ade una primera entrada a la lista de control, un hueco con el

		// tama�o de la memoria completa:
		Proceso proceso = new Proceso("Hueco",0, 0 , LONGITUD_MEMORIA, 0);
		

		listaControl.add(proceso);
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
		switch (1) {
			case 1:
			hueco = Ajueste.primerAjuste(listaControl, tamanyo);
			
			res = (hueco != -1);
	
			if (res) {
				Proceso procesoHueco = listaControl.get(hueco);
				Proceso procesoOrden = new Proceso("Proceso " + pid, 1, ultimoPid, tamanyo, pid);
	
				int espacioRestante = procesoHueco.getTamanio() - tamanyo;
	
				listaControl.add(hueco, procesoOrden);
	
				if (espacioRestante > 0) {
					procesoHueco.setTamanio(espacioRestante);
				} else {
					listaControl.remove(hueco);
				}
	
				escribeMemoria(ultimoPid, tamanyo, 1);
				ultimoPid += tamanyo;
			}
			break;
			case 2:
			hueco = Ajueste.siguienteAjuste(listaControl, tamanyo);
			
			res = (hueco != -1);
	
			if (res) {
				Proceso procesoHueco = listaControl.get(hueco);
				Proceso procesoOrden = new Proceso("Proceso " + pid, 1, ultimoPid, tamanyo, pid);
	
				int espacioRestante = procesoHueco.getTamanio() - tamanyo;
	
				listaControl.add(hueco, procesoOrden);
	
				if (espacioRestante > 0) {
					procesoHueco.setTamanio(espacioRestante);
				} else {
					listaControl.remove(hueco);
				}
	
				escribeMemoria(ultimoPid, tamanyo, 1);
				ultimoPid += tamanyo;
			}
			break;
			case 3:
			hueco = Ajueste.mejorAjuste(listaControl, tamanyo);
			
			res = (hueco != -1);
	
			if (res) {
				Proceso procesoHueco = listaControl.get(hueco);
				Proceso procesoOrden = new Proceso("Proceso " + pid, 1, ultimoPid, tamanyo, pid);
	
				int espacioRestante = procesoHueco.getTamanio() - tamanyo;
	
				listaControl.add(hueco, procesoOrden);
	
				if (espacioRestante > 0) {
					procesoHueco.setTamanio(espacioRestante);
				} else {
					listaControl.remove(hueco);
				}
	
				escribeMemoria(ultimoPid, tamanyo, 1);
				ultimoPid += tamanyo;
			}
			break;
			case 4:
			hueco = Ajueste.peorAjuste(listaControl, tamanyo);
			
			res = (hueco != -1);
			
			if (res) {
				// Se obtiene el lugar que ocupa en la listaControl
				Proceso procesoHueco = listaControl.get(hueco);
				// Se crea el nuevo proceso
				Proceso procesoOrden = new Proceso("Proceso "+pid, 1, ultimoPid, tamanyo, pid);
								
				int espacioRestante = procesoHueco.getTamanio() - tamanyo;
				
				// Inserta el proceso en el lugar del hueco
				listaControl.add(hueco, procesoOrden);
			
				// Actualiza el proceso existente para reflejar el espacio restante
				procesoHueco.setTamanio(espacioRestante);
				
				escribeMemoria(ultimoPid, tamanyo, 1);
				ultimoPid += tamanyo;
			}
			
				break;
			default:
				break;

		}

		
		
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
        int indice = -1;

        for (int i = 0; i < listaControl.size(); i++) {
            Proceso proceso = listaControl.get(i);
            if (proceso.getPid() == pid) {
                indice = i;
                break;
            }
        }

        boolean encontrado = (indice != -1);

        if (encontrado) {
            Proceso procesoABorrar = listaControl.get(indice);
            procesoABorrar.setEstado(0);
            procesoABorrar.setNombre("Hueco");
            procesoABorrar.setPid(0);
            escribeMemoria(procesoABorrar.getDireIni(), procesoABorrar.getTamanio(), 0);
            fusiona(indice);
            if (indice > 0) {
                fusiona(indice - 1);
            }
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
            Proceso bloqueActual = listaControl.get(indice);
            Proceso bloqueSiguiente = listaControl.get(indice + 1);

            if (bloqueActual.getEstado() == 0 && bloqueSiguiente.getEstado() == 0) {
                fusionable = true;
                bloqueActual.setTamanio(bloqueActual.getTamanio() + bloqueSiguiente.getTamanio());
                listaControl.remove(indice + 1);
            }
        }

        return fusionable;
    }


	/**
	 * 
	 * Muestra en pantalla el estado de la lista de control y la memoria.
	 * 
	 */

	public static String imprimeMemoria() {

		String s = " [EST-DIR-TAM-PROC]:";

		for (Proceso bloque : listaControl) {

			s += " >> [" + bloque.getNombre() + "-" + bloque.getEstado() + "-" + bloque.getDireIni() + "-"

					+ bloque.getTamanio()+ "-"+ bloque.getPid();

			s += "]";

			
				
			
			

		}

		

		String cadena = s;
		return cadena;
		
	}

	// Este es el punto de entrada de la implementaci�n.

	// Se puede experimentar con las primitivas creadas.
	public static String imprimeCadena(){
		
		String unocero = ""; 
		for (int i : memoria) {

			unocero += i;

		}
		return "   "+unocero;
	}

	
	
	

}
