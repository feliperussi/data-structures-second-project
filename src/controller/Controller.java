package controller;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

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

			int option = Integer.parseInt(lector.nextLine());
			switch (option) {
				case 1: //Carga datos a una lista encadenada
					view.printMessage("--------- \nCargando Datos a Lista encadenada...");
					try {
						long t_inicial = System.currentTimeMillis();
						modelo.agregarDatosCsv(2);
						long t_final = System.currentTimeMillis();
						long tiempo = t_final - t_inicial;
						view.printMessage("Datos cargados en " + tiempo + "ms");
						view.printMessage("Numero de peliculas cargadas: " + modelo.darTamano());
						view.printMessage(modelo.darInfoExtremos());
					} catch (IOException e) {
						modelo = new Modelo();
						view.printMessage("Error cargando las peliculas\n--------- ");
					}
					break;

				case 2: //Carga datos a un arreglo dinámico
					view.printMessage("--------- \nCargando Datos a Arreglo dinamico...");
					try {
						long t_inicial = System.currentTimeMillis();
						modelo.agregarDatosCsv(1);
						long t_final = System.currentTimeMillis();
						long tiempo = t_final - t_inicial;
						view.printMessage("Datos cargados en " + tiempo + "ms");
						view.printMessage("Numero de peliculas cargadas: " + modelo.darTamano());
						view.printMessage(modelo.darInfoExtremos());
					} catch (IOException e) {
						modelo = new Modelo();
						view.printMessage("Error cargando las peliculas\n--------- ");
					}
					break;

				case 3: //Retorna las 20 peliculas con peor puntuacion
					String[] peliculas = modelo.peoresPeliculas(20);
					if (peliculas != null) {
						view.printMessage("Las 20 peores peliculas son:\n");
						for (String pelicula : peliculas) {
							view.printMessage(pelicula);
						}
					} else {
						view.printMessage("Error: No hay peliculas cargadas \n--------- ");
					}
					break;

				case 4: //Retorna las buenas peliculas de un director 
					view.printMessage("--------- \nNombre del director: ");
					String director = lector.nextLine();
					String[] buenasPelis = modelo.buenasPeliculas(director);
					if (buenasPelis != null) {
						view.printMessage(
								"El director tiene " + buenasPelis.length + " buenas peliculas\n---------\nEstas son:\n");
						for (String pelicula : buenasPelis) {
							view.printMessage(pelicula);
						}
					} else {
						view.printMessage("El director " + director + " no tiene buenas peliculas." + "\n---------");
					}
					break;

				case 5:
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
