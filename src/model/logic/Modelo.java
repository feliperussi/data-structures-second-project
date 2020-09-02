package model.logic;

import model.data_structures.ArregloDinamico;
import model.data_structures.Lista;
import model.data_structures.ListaEncadenada;
import model.data_structures.ShellSort;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo {
	/**
	 * Atributos del modelo del mundo
	 */
	private static final String PELICULAS_DETALLES = "data/SmallMoviesDetailsCleaned.csv";
	private static final String PELICULAS_CASTING = "data/MoviesCastingRaw-small.csv";
	private Lista<Peliculas> datos;
	private int tamanoAprox = 100;

	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public Modelo() 
	{
		datos = new ArregloDinamico<Peliculas>(tamanoAprox);
	}

	/**
	 * Constructor del modelo del mundo con capacidad dada
	 * 
	 * @param tamano
	 */
	public Modelo(int capacidad) 
	{
		datos = new ArregloDinamico<Peliculas>(capacidad);
	}

	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo
	 * 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamano() 
	{
		return datos.size();
	}

	/**
	 * Requerimiento de agregar dato
	 * 
	 * @param dato
	 */
	public void agregar(Peliculas dato) 
	{
		datos.append(dato);
	}

	/**
	 * Requerimiento buscar dato
	 * 
	 * @param dato Dato a buscar
	 * @return dato encontrado
	 */
	public Peliculas buscar(Peliculas dato) 
	{
		return datos.buscar(dato);
	}

	/**
	 * Requerimiento eliminar dato
	 * @param dato Dato a eliminar
	 * @return dato eliminado
	 */
	public Peliculas eliminar(Peliculas dato) {
		return datos.removeByType(dato);
	}

	/**
	 * Agrega los datos del Csv en el tipo correcto de estructura de datos
	 * @param tipo de estructura: 1 = Arreglo dinamico, 2 = Lista encadenada
	 * default = se crea un arreglo dinamico
	 */
	public void agregarDatosCsv(int tipo) throws IOException {
		try 
		{
			// Prueba la lectura de los archivos
			Reader readerDetalles = Files.newBufferedReader(Paths.get(PELICULAS_DETALLES));
			Reader readerCasting = Files.newBufferedReader(Paths.get(PELICULAS_CASTING));
			// Crea el separador con ";"
			CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
			// Crea los respectivos lectores
			CSVReader csvReaderD = new CSVReaderBuilder(readerDetalles).withCSVParser(parser).build();
			CSVReader csvReaderC = new CSVReaderBuilder(readerCasting).withCSVParser(parser).build();
			// Lee todos los datos y los agrega a una List<String[]>
			List<String[]> detalles = csvReaderD.readAll();
			List<String[]> castings = csvReaderC.readAll();
			csvReaderD.close();
			csvReaderC.close();

			//Parte de un arreglo vacio 
			//Crea las variables temporales con el tipo de estructura correcto
			Lista<Peliculas> castLimpio = null;
			switch (tipo) {
				case 1:
					datos = new ArregloDinamico<Peliculas>(tamanoAprox);
					castLimpio = new ArregloDinamico<Peliculas>(tamanoAprox);
					break;
				case 2:
					datos = new ListaEncadenada<Peliculas>();
					castLimpio = new ListaEncadenada<Peliculas>();
					break;
				default:
					System.out.println("No es una estructura de datos valida. \n Se utilizara un Arreglo dinamico por default.");
					datos = new ArregloDinamico<Peliculas>(tamanoAprox);
					castLimpio = new ArregloDinamico<Peliculas>(tamanoAprox);
					break;
			}
			if(castLimpio!=null) {
				//Agrega informacion correcta a la lista de castings
				for (int i = 1; i < castings.size(); i++) { // Comienza en 1 ya que el primer dato es el nombre de la
					// columna
					if (verificarCastings(castings.get(i)) != null) {
						castLimpio.append(verificarCastings(castings.get(i)));
					}
				}
				// Combina las peliculas con informacion completa y correcta
				for (int i = 1; i < detalles.size(); i++) 
				{
					// Comprueba que la linea tenga informacion correcta
					Peliculas infoD = verificarDetalles(detalles.get(i));
					if (infoD != null) 
					{
						// Busca la pelicula con misma identificacion en el arreglo dinamico
						Peliculas infoC = castLimpio.buscar(infoD);
						if (infoC != null) 
						{
							// Asigna la informacion verificada
							Integer id = infoC.darId();
							String director = infoC.darDirector();
							String[] actores = infoC.darActores();
							String nombre = infoD.darNombre();
							Double puntuacion = infoD.darPuntuacion();
							Date fecha = infoD.darFecha();
							String[] genero = infoD.darGenero();
							Peliculas pelicula = new Peliculas(id, director, nombre, puntuacion, actores, fecha, genero);
							datos.append(pelicula);
						}
					}
				}
			}		
		} 
		catch (CsvException e) 
		{
			System.out.println("Fallo en leer algun CSV");
			System.out.println(e.getStackTrace());
		}
	}

	/**
	 * Verificar que la informacion tiene el formato correcto
	 * @return retorna la informacion en el formato correcto null si hay errores
	 */
	public Peliculas verificarDetalles(String[] detalle) {
		try {
			Peliculas resp = null;
			if (detalle[16].compareTo("") != 0 && detalle[16] != null) {
				// Cosas a verificar:
				String nombre = detalle[16]; // Nombre de la pelicula
				Integer id = Integer.parseInt(detalle[0]); // identificacion
				Double puntuacion = Double.parseDouble(detalle[17]); // Puntuacion

				// Se confirma la fecha de estreno este en el formato requerido
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				String fecha = detalle[10];
				Date date = formato.parse(fecha);

				// Se confirman el genero(s) de la pel�cula
				if (detalle[2].compareTo("") != 0 && detalle[2] != null) {
					String[] generos = detalle[2].split("\\|");
					// Se crea una pelicula con la informacion disponible
					resp = new Peliculas(id, "", nombre, puntuacion, null, date, generos);
				}
			}
			return resp;
		} catch (Exception e) {
			System.out.println("Algun dato no tiene el formato esperado");
			return null;
		}
	}

	/**
	 * Verificar que la informacion tiene el formato correcto
	 * @return retorna la informacion disponible en el formato correcto null si hay errores
	 */
	public Peliculas verificarCastings(String[] casting) {
		try {
			Peliculas resp = null;
			// Verifica que los campos de nombres de actores y director sean validos
			boolean bien = true;
			int[] categorias = new int[] { 1, 3, 5, 7, 9, 12 };
			String[] actores = new String[5];
			int cont = 0;
			for (int i : categorias) {
				if (casting[i].compareTo("") != 0 && casting[i] != null) {
					if (i != 12) {
						// Crea el arreglo de actores
						actores[cont] = casting[i];
						cont++;
					}
				} else {
					bien = false;
				}
			}
			// Dado que la info es correcta, se crean las categorias
			if (bien == true) {
				String director = casting[12]; // Nombre del director
				Integer id = Integer.parseInt(casting[0]); // identificaci�n
				// Se crea una pelicula con la informacion disponible
				resp = new Peliculas(id, director, "", null, actores, null, null);
			}
			return resp;
		} catch (Exception e) {
			System.out.println("Algun campo no tiene el formato esperado");
			return null;
		}
	}

	public String darInfoExtremos() {
		return "---------------------\nPrimera pelicula:\n" + datos.firstElement().darInfo() + "\nUltima pelicula:\n"
				+ datos.lastElement().darInfo();
	}

	/**
	 * Metodo para dar la lista de las peliculas buenas de un director
	 * 
	 * @param entra como String el nombre del director
	 * @return String[] lista de peliculas con puntuacion >= umbral; null si no se
	 *         encuentra el director
	 */
	public String[] buenasPeliculas(String director) {
		String[] resp = null;
		Lista<Peliculas> peliculasDirector = new ArregloDinamico<Peliculas>(tamanoAprox / 10);

		for (int i = 1; i < datos.size(); i++) 
		{
			Peliculas temp = datos.get(i);
			// Compara si es el director correcto
			if (temp.darDirector().compareTo(director) == 0) 
			{
				// Compara si la pelicula es buena
				if (temp.darPuntuacion() >= 6) 
				{
					peliculasDirector.append(temp);
				}
			}
		}
		// Da el formato de String[] con la informacion de la pelicula
		if (peliculasDirector.size() != 0) 
		{
			resp = new String[peliculasDirector.size()];

			for (int i = 1; i <= peliculasDirector.size(); i++) 
			{
				resp[i-1] = peliculasDirector.get(i).darInfo();
			}
		}
		return resp;
	}

	/**
	 * Metodo para devolver las películas peor punteadas
	 * @param entra como entero la cantidad de peliculas deseadas
	 * @return String[] lista de las peores peliculas en orden ascendente; 
	 * null si hay problemas
	 */
	public String[] peoresPeliculas(Integer cant){
		String[] resp = null;
		//Verifica que los datos existan
		if(datos != null && datos.size()>0){
			for(int i=1; i <= datos.size(); i++){
				datos.get(i).compararPor(1); //Cambia el criterio de comparacion a puntuación
			}
			//Variable auxiliar tipo Comparable[]
			Peliculas[] pelisPuntuacion = new Peliculas[datos.size()];
			for(int i=1;i<=datos.size();i++) {
				//Inicia en 1 por el conteo de las listas, pero en 0 para Comparable[]
				pelisPuntuacion[i-1]=datos.get(i);
			}
			//Organiza en orden descendente
			ShellSort.sort(pelisPuntuacion);
			//Verifica que hayan suficientes datos como los solicitados
			if(datos.size()>=cant){
				resp = new String[cant];
				for(int i=0; i < cant; i++){
					resp[i]= i+1 + ") " + pelisPuntuacion[pelisPuntuacion.length-i-1].darInfo();
				}
			}
			//Sino, da todos los datos disponiles
			else{
				System.out.println("No hay suficientes datos, estas son las peliculas disponibles: \n");
				resp = new String[datos.size()];
				for(int i=0; i < datos.size(); i++){
					resp[i]= i+1 + ") " + pelisPuntuacion[pelisPuntuacion.length-i-1].darInfo();
				}
			}
		}
		return resp;
	}

	public ArregloDinamico<Peliculas> darDatos()
	{
		return (ArregloDinamico<Peliculas>) datos;
	}

}
