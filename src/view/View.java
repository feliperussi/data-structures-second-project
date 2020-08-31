package view;

public class View {
	/**
	 * Metodo constructor
	 */
	public View() {

	}

	public void printMenu() {
		System.out.println("1. Realizar la carga de datos a una lista encadenada");
		System.out.println("2. Realizar la carga de datos a un arreglo dinamico");
		System.out.println("3. 20 Peliculas con peor puntuacion");	
		System.out.println("4. Buenas peliculas de un director");
		System.out.println("5. Exit");
		System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
	}

	public void printMessage(String mensaje) {

		System.out.println(mensaje);
	}

}
