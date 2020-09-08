package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import model.data_structures.Lista;
import model.logic.Modelo;
import model.logic.Peliculas;
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

			case 3: //Retorna las peliculas especificadas con peor/mejor puntuacion
				view.printMessage("--------- \n Cantidad de películas a rankear: ");
				try{
					int cant = Integer.parseInt(lector.nextLine());
					if(cant >= 10){
						view.printMessage("--------- \n Escoger tipo de ranking (e.g., 1): ");
						view.printMessage("1. Top mejores");
						view.printMessage("2. Top peores");
						int tipo = Integer.parseInt(lector.nextLine());
						switch(tipo){
							case 1:
								String[] peliculas1 = modelo.rankingPeliculas(cant, tipo,null);
								if (peliculas1 != null) {
									view.printMessage("Las "+ cant + " mejores peliculas por puntuación son:\n");
									for (String pelicula : peliculas1) {
										view.printMessage(pelicula);
									}
								} else {
									view.printMessage("Error: No hay peliculas cargadas \n--------- ");
								}
								break;
							case 2:
								String[] peliculas2 = modelo.rankingPeliculas(cant, tipo,null);
								if (peliculas2 != null) {
									view.printMessage("Las " + cant + " peores peliculas por puntuación son:\n");
									for (String pelicula : peliculas2) {
										view.printMessage(pelicula);
									}
								} else {
									view.printMessage("Error: No hay peliculas cargadas \n--------- ");
								}
								break;
							default:
							view.printMessage("--------- \n Opcion Invalida !! \n---------");
							break;
						}
					}
					else view.printMessage("---------\n La longitud debe ser mayor a 10 \n--------- ");
				}
				catch (Exception e) {
					modelo = new Modelo();
					view.printMessage("---------\n Opción inváida!! \n--------- ");
				}
				break;	

			case 4: //Retorna las buenas peliculas de un director 
				view.printMessage("--------- \nNombre del director: ");
				String director = lector.nextLine();
				String[] buenasPelis = modelo.buenasPeliculas(director);
				if (buenasPelis != null) {
					view.printMessage(
							"\n---------\n Las buenas peliculas del director tiene son:\n");
					for (String pelicula : buenasPelis) {
						if(pelicula != buenasPelis[buenasPelis.length-1]) view.printMessage(pelicula);
					}
					view.printMessage(" ---------------------\n RESUMEN \n ---------------------");
					int pelis_num=buenasPelis.length-1;
					view.printMessage("El director " + director + " tiene " + pelis_num + " buenas peliculas\n "
										+ "En general, sus peliculas tienen un puntaje promedio de: " + buenasPelis[buenasPelis.length-1]);
					view.printMessage(" ---------------------");
				} else {
					view.printMessage("El director " + director + " no tiene buenas peliculas." + "\n---------");
				}
				break;

			case 5: //Retorna las peliculas asociadas a un director
				break;
			case 6: //Retorna las peliculas asociadas a un actor
				break;
			case 7: //Retorna las peliculas asociadas a un genero
				view.printMessage("--------- \n \nNombre del genero: ");
				String genero = lector.nextLine();
				long t_inicial = System.currentTimeMillis();
				ArrayList<Peliculas> generos = modelo.darPeliculasPorGenero(genero);
				long t_final = System.currentTimeMillis();
				long tiempo = t_final - t_inicial;
				if (generos != null){
					view.printMessage("--------- \n Estas son: \n---------\n");
					for (Peliculas peliculas : generos) {
						view.printMessage(peliculas.darInfo());
					}
					view.printMessage("---------------------\n RESUMEN \n---------------------");
					view.printMessage("Lista de generos cargados en " + tiempo + "ms");
					view.printMessage("Hay " + generos.size() + " peliculas asociadas al género: " + genero
										+ " con un puntaje promedio de: " + modelo.vote_Ave(generos));
					view.printMessage("---------------------");
				}
				else {
					view.printMessage("El género " + genero + " no tiene peliculas asociadas." + "\n---------");
				}
				break;

			case 8: // Crea un ranking para un genero
				view.printMessage("--------- \n Seleccione el género para rankear: ");
				String pGenero = lector.nextLine();
				t_inicial = System.currentTimeMillis();
				Lista<Peliculas> generosRank = modelo.darListaPorGenero(pGenero);
				t_final = System.currentTimeMillis();
				tiempo = t_final - t_inicial;
				if (generosRank != null){
					view.printMessage("Generos cargados en " + tiempo + "ms");
					view.printMessage("Hay " + generosRank.size() + " peliculas asociadas al género: " + pGenero);
					view.printMessage("--------- \n Ingrese la cantidad de películas a rankear: ");
					try{
						int cant = Integer.parseInt(lector.nextLine());
						if(cant >= 10){
							view.printMessage("--------- \n Escoger tipo de ranking (e.g., 1): ");
							view.printMessage("1. Top mejores");
							view.printMessage("2. Top peores");
							int tipo = Integer.parseInt(lector.nextLine());
							switch(tipo){
								case 1:
									String[] peliculas1 = modelo.rankingPeliculas(cant, tipo, generosRank);
									if (peliculas1 != null) {
										view.printMessage(" --------------------");
										view.printMessage("Las "+ cant + " mejores peliculas de " + pGenero + " por puntuación son:\n");
										view.printMessage(" --------------------");
										for (String pelicula : peliculas1) {
											view.printMessage(pelicula);
										}
									} else {
										view.printMessage("Error: No hay peliculas cargadas \n--------- ");
									}
									break;
								case 2:
									String[] peliculas2 = modelo.rankingPeliculas(cant, tipo, generosRank);
									if (peliculas2 != null) {
										view.printMessage(" --------------------");
										view.printMessage("Las " + cant + " peores peliculas de " + pGenero + " por puntuación son:\n");
										view.printMessage(" --------------------");
										for (String pelicula : peliculas2) {
											view.printMessage(pelicula);
										}
									} else {
										view.printMessage("Error: No hay peliculas cargadas \n--------- ");
									}
									break;
								default:
									view.printMessage("--------- \n Opcion Invalida !! \n---------");
									break;
							}
						}
						else view.printMessage("---------\n La longitud debe ser mayor a 10 \n--------- ");
					}
					catch (Exception e) {
						modelo = new Modelo();
						view.printMessage("---------\n Opción inváida!! \n--------- ");
					}
				}
				else {
					view.printMessage("El género " + pGenero + " no tiene peliculas asociadas." + "\n---------");
				}
				break;
			case 9:
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
