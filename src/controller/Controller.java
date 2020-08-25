package controller;

import java.util.Scanner;

import model.logic.Modelo;
import view.View;

public class Controller {

	/* Instancia del Modelo */
	private Modelo modelo;

	/* Instancia de la Vista */
	private View view;

	/**
	 * Crear la vista y el modelo del proyecto
	 * 
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller() {
		view = new View();
		modelo = new Modelo();
	}

	public void run() {
		Scanner lector = new Scanner(System.in);
		boolean fin = false;

		while (!fin) {
			view.printMenu();

			int option = lector.nextInt();
			switch (option) {
				case 1:
					view.printMessage("--------- \nCargando Datos...");
					modelo.agregarDatosCsvOpt();
					view.printMessage("Datos cargados");
					view.printMessage("Numero de peliculas cargadas: " + modelo.darTamano());
					view.printMessage(modelo.darInfoExtremos());
					break;

				case 2:
					view.printMessage("--------- \nNombre del director: ");
					String director = lector.next();
					String[] peliculas = modelo.buscarPeliculas(director);
					if (peliculas != null) {
						String[] buenas = modelo.darBuenasPeliculas();
						view.printMessage("El director tiene "+buenas.length+" buenas peliculas\n---------\nEstas son:\n");
						for(String pelicula:buenas){
							view.printMessage(pelicula);
						}
					} else {
						view.printMessage("El director " + director + " no fue encontrado." + "\n---------");
					}
					break;

				case 3:
					view.printMessage("--------- \n Hasta pronto !! \n---------");
					lector.close();
					fin = true;
					break;

				default:
					view.printMessage("--------- \n Opcion Invalida !! \n---------");
					break;
			}
		}

	}
}
