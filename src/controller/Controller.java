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
				case 1: // Carga datos a una lista encadenada
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

				case 2: // Carga datos a un arreglo dinámico
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

				case 3: // Retorna las peliculas especificadas con peor/mejor puntuacion/votos
					view.printMessage("--------- \n Cantidad de películas a rankear: ");
					try {
						int cant = Integer.parseInt(lector.nextLine());
						if (cant >= 10) {
							view.printMessage("--------- \n Escoger tipo de ranking (e.g., 1): ");
							view.printMessage("1. Top mejores");
							view.printMessage("2. Top peores");
							int tipo = Integer.parseInt(lector.nextLine());
							view.printMessage("--------- \n Escoger tipo de criterio (e.g., 1): ");
							view.printMessage("1. Puntuación promedio");
							view.printMessage("2. Cantidad de votos");
							int criterio = Integer.parseInt(lector.nextLine());
							// Verifica que el criterio sea valido
							if (criterio != 1 && criterio != 2)
								throw new Exception();
							String strCriterio = (criterio == 1) ? " puntuacion " : " votos ";
							switch (tipo) {
								case 1:
									String[] peliculas1 = modelo.rankingPeliculas(cant, tipo, criterio, null);
									if (peliculas1 != null) {
										view.printMessage(" ---------------------\n RESUMEN \n ---------------------");
										view.printMessage(
												"Las " + cant + " mejores peliculas por" + strCriterio + "tienen:");
										view.printMessage("Puntaje promedio: " + peliculas1[cant]);
										view.printMessage("Votos promedio: " + peliculas1[cant + 1]);
										view.printMessage("---------------------");
										for (String pelicula : peliculas1) {
											if (pelicula != peliculas1[cant] && pelicula != peliculas1[cant + 1])
												view.printMessage(pelicula);
										}
									} else {
										view.printMessage("Error: No hay peliculas cargadas \n--------- ");
									}
									break;
								case 2:
									String[] peliculas2 = modelo.rankingPeliculas(cant, tipo, criterio, null);
									if (peliculas2 != null) {
										view.printMessage(" ---------------------\n RESUMEN \n ---------------------");
										view.printMessage(
												"Las " + cant + " peores peliculas por" + strCriterio + "tienen:");
										view.printMessage("Puntaje promedio: " + peliculas2[cant]);
										view.printMessage("Votos promedio: " + peliculas2[cant + 1]);
										view.printMessage("---------------------");
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
						} else
							view.printMessage("---------\n La longitud debe ser mayor a 10 \n--------- ");
					} catch (Exception e) {
						modelo = new Modelo();
						view.printMessage("---------\n Opción inváida!! \n--------- ");
					}
					break;

				case 4: // Retorna las buenas peliculas de un director
					view.printMessage("--------- \nNombre del director: ");
					String director = lector.nextLine();
					String[] buenasPelis = modelo.buenasPeliculas(director);
					if (buenasPelis != null) {
						view.printMessage("\n---------\n Las buenas peliculas del director tiene son:\n");
						for (String pelicula : buenasPelis) {
							if (pelicula != buenasPelis[buenasPelis.length - 1])
								view.printMessage(pelicula);
						}
						view.printMessage(" ---------------------\n RESUMEN \n ---------------------");
						int pelis_num = buenasPelis.length - 1;
						view.printMessage(" El director " + director + " tiene " + pelis_num + " buenas peliculas\n "
								+ "En general, sus peliculas tienen un puntaje promedio de: "
								+ buenasPelis[buenasPelis.length - 1]);
						view.printMessage(" ---------------------");
					} else {
						view.printMessage("El director " + director + " no tiene buenas peliculas." + "\n---------");
					}
					break;

				case 5: // Retorna las peliculas asociadas a un director
					view.printMessage("--------- \nNombre del director: ");
					String directorAConocer = lector.nextLine();
					String[] conocerDirector = modelo.conocerDirector(directorAConocer);
					if (conocerDirector.length == 1) {
						view.printMessage("El director " + directorAConocer + " no tiene peliculas." + "\n---------");
					} else {
						view.printMessage("--------- \n" + directorAConocer + " tiene " + (conocerDirector.length - 1)
								+ " peliculas.\n---------");
						for (int i = 0; i < conocerDirector.length - 1; i++) {
							view.printMessage(conocerDirector[i] + "\n");
						}
						view.printMessage("--------- \nSu calificacion promedio es de "
								+ conocerDirector[conocerDirector.length - 1] + "\n---------");
					}
					break;
				case 6: // Retorna las peliculas asociadas a un actor
					view.printMessage("--------- \nNombre del actor/actriz: ");
					String actorAConocer = lector.nextLine();
					String[] conocerActor = modelo.conocerActor(actorAConocer);
					if (conocerActor.length == 2) {
						view.printMessage("El actor/actriz " + actorAConocer + " no ha participado en peliculas."
								+ "\n---------");
					} else {
						view.printMessage("--------- \n" + actorAConocer + " ha participado en "
								+ (conocerActor.length - 2) + " peliculas.\n---------");
						for (int i = 0; i < conocerActor.length - 2; i++) {
							view.printMessage(conocerActor[i] + "\n");
						}
						view.printMessage(
								"--------- \nLa calificacion promedio de las peliculas en las que participo es de "
										+ conocerActor[conocerActor.length - 2] + "\n---------");
						view.printMessage("El director con el que tiene mas colaboraciones es "
								+ conocerActor[conocerActor.length - 1] + "\n---------");
					}
					break;
				case 7: // Retorna las peliculas asociadas a un genero
					view.printMessage("--------- \n \nNombre del genero: ");
					String genero = lector.nextLine();
					long t_inicial = System.currentTimeMillis();
					ArrayList<Peliculas> generos = modelo.darPeliculasPorGenero(genero);
					long t_final = System.currentTimeMillis();
					long tiempo = t_final - t_inicial;
					if (generos != null) {
						view.printMessage("--------- \n Estas son: \n---------\n");
						for (Peliculas peliculas : generos) {
							view.printMessage(peliculas.darInfo());
						}
						view.printMessage("---------------------\n RESUMEN \n---------------------");
						view.printMessage("Lista de generos cargados en " + tiempo + "ms");
						view.printMessage("Hay " + generos.size() + " peliculas asociadas al género: " + genero);
						view.printMessage("Estas tienen un puntaje promedio de: " + modelo.promedios(generos)[0] + "\n"
								+ "y una cantidad de votos promedio de: " + modelo.promedios(generos)[1]);
						view.printMessage("---------------------");
					} else {
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
					if (generosRank != null) {
						view.printMessage("Generos cargados en " + tiempo + "ms");
						view.printMessage("Hay " + generosRank.size() + " peliculas asociadas al género: " + pGenero);
						view.printMessage("--------- \n Ingrese la cantidad de películas a rankear: ");
						try {
							int cant = Integer.parseInt(lector.nextLine());
							if (cant >= 10) {
								view.printMessage("--------- \n Escoger tipo de ranking (e.g., 1): ");
								view.printMessage("1. Top mejores");
								view.printMessage("2. Top peores");
								int tipo = Integer.parseInt(lector.nextLine());
								view.printMessage("--------- \n Escoger tipo de criterio (e.g., 1): ");
								view.printMessage("1. Puntuación promedio");
								view.printMessage("2. Cantidad de votos");
								int criterio = Integer.parseInt(lector.nextLine());
								// Verifica que el criterio sea valido
								if (criterio != 1 && criterio != 2)
									throw new Exception();
								String strCriterio = (criterio == 1) ? " puntuacion " : " votos ";
								switch (tipo) {
									case 1:
										String[] peliculas1 = modelo.rankingPeliculas(cant, tipo, criterio,
												generosRank);
										if (peliculas1 != null) {
											view.printMessage(
													"---------------------\n RESUMEN \n---------------------");
											view.printMessage("Las " + cant + " mejores peliculas de " + pGenero
													+ " se clasificaron por" + strCriterio);
											view.printMessage("Puntuación promedio: " + peliculas1[cant]);
											view.printMessage("Votos promedio: " + peliculas1[cant + 1]);
											view.printMessage(" --------------------");
											for (String pelicula : peliculas1) {
												if (pelicula != peliculas1[cant] && pelicula != peliculas1[cant + 1])
													view.printMessage(pelicula);
											}
										} else {
											view.printMessage("Error: No hay peliculas cargadas \n--------- ");
										}
										break;
									case 2:
										String[] peliculas2 = modelo.rankingPeliculas(cant, tipo, criterio,
												generosRank);
										if (peliculas2 != null) {
											view.printMessage(
													"---------------------\n RESUMEN \n---------------------");
											view.printMessage("Las " + cant + " peores peliculas de " + pGenero
													+ " se clasificaron por" + strCriterio);
											view.printMessage("Puntuación promedio: " + peliculas2[cant]);
											view.printMessage("Votos promedio: " + peliculas2[cant + 1]);
											view.printMessage(" --------------------");
											for (String pelicula : peliculas2) {
												if (pelicula != peliculas2[cant] && pelicula != peliculas2[cant + 1])
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
							} else
								view.printMessage("---------\n La longitud debe ser mayor a 10 \n--------- ");
						} catch (Exception e) {
							modelo = new Modelo();
							view.printMessage("---------\n Opción inváida!! \n--------- ");
						}
					} else {
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