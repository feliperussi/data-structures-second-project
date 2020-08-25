package model.data_structures;

/**
 * 2019-01-23
 * Estructura de Datos Arreglo Dinamico de Strings.
 * El arreglo al llenarse (llegar a su maxima capacidad) debe aumentar su capacidad.
 * @author Fernando De la Rosa
 *
 */
/** Encabezado de definici�n generico para la clase ArregloDinamico */
public class ArregloDinamico<T extends Comparable<T>> implements IArregloDinamico<T> {
	/**
	 * Capacidad maxima del arreglo
	 */
	private int tamanoMax;
	/**
	 * Numero de elementos presentes en el arreglo (de forma compacta desde la
	 * posicion 0)
	 */
	private int tamanoAct;
	/**
	 * Arreglo de elementos de tamaNo maximo
	 */
	private T[] elementos;

	/**
	 * Construir un arreglo con la capacidad maxima inicial.
	 * 
	 * @param max Capacidad maxima inicial
	 */
	public ArregloDinamico(int max) {
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Comparable[max];
		elementos = temp;
		tamanoMax = max;
		tamanoAct = 0;
	}

	public void agregar(T dato) {
		if (tamanoAct == tamanoMax) { // caso de arreglo lleno (aumentar tamaNo)
			tamanoMax = 2 * tamanoMax;
			T[] copia = elementos;
			@SuppressWarnings("unchecked")
			T[] temp = (T[]) new Comparable[tamanoMax];
			elementos = temp;
			for (int i = 0; i < tamanoAct; i++) {
				elementos[i] = copia[i];
			}
			System.out.println("Arreglo lleno: " + tamanoAct + " - Arreglo duplicado: " + tamanoMax);
		}
		elementos[tamanoAct] = dato;
		tamanoAct++;
	}

	public int darCapacidad() {
		return tamanoMax;
	}

	public int darTamano() {
		return tamanoAct;
	}

	public T darElemento(int i) {
		// Implementar
		if (i < tamanoAct) {
			return elementos[i];
		} else {
			// System.out.println("No hay un elemento en la posicion: "+ i);
			return null;
		}
	}

	public T buscar(T dato) {
		// Implementar
		// Recomendacion: Usar el criterio de comparacion natural (metodo compareTo())
		// definido en Strings.
		boolean termino = false;
		int i = 0;
		T resp = null;
		while (termino == false) {
			// Revisa que aun estoy en el arreglo y no he encontrado el dato
			if (i < tamanoAct) {
				int prueba = dato.compareTo(elementos[i]);
				if (prueba == 0) {
					resp = elementos[i];
					termino = true;
				}
				i++;
			} else {
				termino = true;
				// System.out.println("El dato no se encuentra en el arreglo");
			}
		}
		return resp;
	}

	public T eliminar(T dato) {
		// Implementar
		// Recomendacion: Usar el criterio de comparacion natural (metodo compareTo())
		// definido en Strings.
		boolean termino = false;
		int i = 0;
		T resp = null;
		while (termino == false) {
			// Busco el dato y su posicion
			if (i < tamanoAct) {
				int prueba = dato.compareTo(elementos[i]);
				if (prueba == 0) {
					resp = elementos[i];
					// Modifica el arreglo para que sea compacto
					T[] copia = elementos;
					for (int k = 0; k < tamanoAct; k++) {
						System.out.println(copia[k]);
					}
					@SuppressWarnings("unchecked")
					T[] temp = (T[]) new Comparable[tamanoMax];
					elementos = temp;
					for (int j = 0; j < tamanoAct; j++) { // Elementos antes quedan igual
						if (j < i) {
							elementos[j] = copia[j];
						}
						if (j > i) {
							elementos[j - 1] = copia[j];
						}
					}
					for (int k = 0; k < tamanoAct; k++) {
						System.out.println(elementos[k]);
					}
					tamanoAct--;
					termino = true;
				}
				i++;
			} else {
				termino = true;
				// System.out.println("El dato no se encuentra en el arreglo");
			}
		}
		return resp;
	}

}
