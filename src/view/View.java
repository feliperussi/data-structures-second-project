package view;

public class View {
	/**
	 * Metodo constructor
	 */
	public View() {

	}

	public void printMenu() {
		System.out.println("1. Realizar la carga de datos");
		System.out.println("2. Buenas pel�culas de un director");
		System.out.println("3. Exit");
		System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
	}

	public void printMessage(String mensaje) {

		System.out.println(mensaje);
	}

}
