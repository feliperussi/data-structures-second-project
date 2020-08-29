package model.data_structures;

/** Encabezado de definici�n generico para la interface IArregloDinamico */
public interface Lista<T extends Comparable<T>> {

	/**
	 * Retornar el numero de elementos maximo en el arreglo
	 * 
	 * @return
	 */
	int darCapacidad();

	/**
	 * Retornar el numero de elementos presentes en el arreglo
	 * 
	 * @return
	 */
	int darTamano();

	/**
	 * Retornar el elemento en la posicion i
	 * 
	 * @param i posicion de consulta
	 * @return elemento de consulta. null si no hay elemento en posicion.
	 */
	T darElemento(int i);

	/**
	 * Agrega un elemento al principio del arreglo.
	 * Corre todos los elementos a la derecha.
	 * @param dato nuevo.
	 */
	public void addFirst(T element);
		
	/**
	 * Agregar un dato de forma compacta (en la primera casilla disponible) 
	 * Caso Especial: Si el arreglo esta lleno debe aumentarse su capacidad, agregar el nuevo dato y deben quedar multiples casillas disponibles para futuros nuevos datos.
	 * @param dato nuevo elemento
	 */
	public void agregar( T dato );
	
	/**
	 * Elimina el primer elemento. Se retorna el elemento eliminado.
	 */
	public T removeFirst();
	
	/**
	 * Elimina el �ltimo elemento. Se retorna el elemento eliminado.
	 */
	public T removeLast();
	
	/**
	 * Eliminar un dato del arreglo. Los datos restantes deben quedar "compactos"
	 * desde la posicion 0.
	 * 
	 * @param dato Objeto de eliminacion en el arreglo
	 * @return dato eliminado
	 */
	public T eliminarPorIndice( int index );
	
	/**
	 * Retorna el primer elemento.
	 */
	public T firstElement( );
	
	/**
	 * Retorna el �ltimo elemento.
	 */
	public T lastElement( );
	
	/**
	 * Buscar un dato en el arreglo.
	 * @param dato Objeto de busqueda en el arreglo
	 * @return elemento encontrado en el arreglo (si existe). null si no se encontro el dato.
	 */
	public T buscar(T dato);
	
	/**
	 * Retorna true si el arreglo No tiene datos. false en caso contrario.
	 * @return true || false;
	 */
	public boolean isEmpty();
	
	/**
	 * Retorna la posicion valida de un elemento. La bussqueda debe usar el metodo compareTo( � ) definido en el tipo T.
	 * Si no se encuentra el elemento, el valor retornado es -1.
	 * @param element a buscar
	 * @return El �ndice del elemento || -1 si no se hall�
	 */
	public int isPresent(T element);
	
	/**
	 * Intercambia la informaci�n de los elementos en dos posiciones v�lidas
	 * @param pos1 
	 * @param pos2
	 */
	public void exchange (int pos1, int pos2);

	/**
	 * Actualiza el elemento en una posici�n v�lida
	 */
	public void changeInfo (int pos, T elem);
	
	/**
	 * Expande el arreglo din�mico.
	 */
	public void expandArray();

	public T eliminarPorTipo(T dato);
}
