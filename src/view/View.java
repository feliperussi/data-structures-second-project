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
		System.out.println("3. Crear ranking de películas");	
		System.out.println("4. Buenas peliculas de un director");
		System.out.println("5. Conocer a un director");
		System.out.println("6. Conocer a un actor");
		System.out.println("7. Entender un género");
		System.out.println("8. Crear ranking de un género");
		System.out.println("9. Exit");
		System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
	}

	public void printMessage(String mensaje) {

		System.out.println(mensaje);
	}

}
