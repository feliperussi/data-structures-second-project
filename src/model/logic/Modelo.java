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
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
	private static final String PELICULAS_DETALLES = "data/AllMoviesDetailsCleaned.csv";
	private static final String PELICULAS_CASTING = "data/AllMoviesCastingRaw.csv";
	//private static final String PELICULAS_DETALLES = "data/SmallMoviesDetailsCleaned.csv";
	//private static final String PELICULAS_CASTING = "data/MoviesCastingRaw-small.csv";
	private Lista<Peliculas> datos;
	private ListaEncadenada<Peliculas> listaDatos;
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
				listaDatos = new ListaEncadenada<Peliculas>();
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
					if (verificarCastings(castings.get(i)) != null) 
					{
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
							Integer votos = infoD.darVotos();
							Peliculas pelicula = new Peliculas(id, director, nombre, puntuacion, actores, fecha, genero, votos);
							//FIXME 

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
				if (detalle[16].equals("")) return null; // Descarta los datos sin nombre
				String nombre = detalle[16].trim(); // Nombre de la pelicula
				if (detalle[0].equals("")) return null; // Descarta los datos sin id
				Integer id = Integer.parseInt(detalle[0]); // identificacion
				if (detalle[17].equals("")) return null; // Descarta los datos sin puntuacion
				Double puntuacion = Double.parseDouble(detalle[17]); // Puntuacion
				if (detalle[18].equals("")) return null; // Descarta los datos sin votos
				Integer votos = Integer.parseInt(detalle[18]);//Cantidad de votos

				// Se confirma la fecha de estreno este en el formato requerido
				String fecha = detalle[10];
				if (fecha.equals("")) return null; // Descarta los datos sin fecha 
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				Date date = formato.parse(fecha);

				// Se confirman el genero(s) de la pelicula
				if (detalle[2].compareTo("") != 0 && detalle[2] != null) {
					String[] generos = detalle[2].split("\\|");
					for (String genero : generos){ //Quita los espacios 
						genero=genero.trim();
					}
					// Se crea una pelicula con la informacion disponible
					resp = new Peliculas(id, "", nombre, puntuacion, null, date, generos,votos);
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
						actores[cont] = casting[i].trim();
						cont++;
					}
				} else {
					bien = false;
				}
			}
			// Dado que la info es correcta, se crean las categorias
			if (bien == true) {
				String director = casting[12].trim(); // Nombre del director
				Integer id = Integer.parseInt(casting[0]); // identificaci�n
				// Se crea una pelicula con la informacion disponible
				resp = new Peliculas(id, director, "", null, actores, null, null, null);
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
	 * @param entra como String el nombre del director
	 * @return String[] lista de peliculas con puntuacion >= umbral; null si no se
	 *         encuentra el director
	 * NOTA: En la ultima casilla de devuelve el promedio de puntaje de todas las peliculas
	 */
	public String[] buenasPeliculas(String director) {
		String[] resp = null;
		Lista<Peliculas> peliculasDirector = new ArregloDinamico<Peliculas>(tamanoAprox / 10);
		double punt_ave = 0;
		int pelis_num =0;
		for (int i = 1; i < datos.size(); i++) 
		{
			Peliculas temp = datos.get(i);
			// Compara si es el director correcto
			if (temp.darDirector().compareTo(director) == 0) 
			{
				punt_ave= punt_ave+temp.darPuntuacion(); //Agrega la puntuación al promedio
				pelis_num++;
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
			punt_ave=(punt_ave)/pelis_num; //Asegura que no hay div por 0
			resp = new String[peliculasDirector.size()+1];

			for (int i = 1; i <= peliculasDirector.size(); i++) 
			{
				resp[i-1] = peliculasDirector.get(i).darInfo();
			}
			NumberFormat nf = NumberFormat.getNumberInstance();
			nf.setMaximumFractionDigits(1);
			String rounded = nf.format(punt_ave);
			resp[peliculasDirector.size()]=rounded;
		}
		return resp;
	}

	/**
	 * Metodo para devolver las películas peor/mejor punteadas
	 * @param 	Cantidad de peliculas deseadas
	 * @param 	Tipo de clasificación: 1= Descendente (Mejores) - 2= Ascendente (Peores)
	 * @param 	Criterio a comparar
	 * @param 	SubArreglo con datos, si es null se utilizan todos
	 * @return 	String[] lista de las peliculas en el orden especificado 
	 * null si hay problemas
	 * NOTA:  ultimos 2 datos dan el vote count average y puntuacion average 
	 */
	public String[] rankingPeliculas(Integer cant, Integer tipo, Integer criterio, Lista<Peliculas> subDatos){
		String[] resp = null;
		Lista<Peliculas> aux = new ListaEncadenada<Peliculas>();
		CompararPor comp = new CompararPor();
		//Escoge cuales datos usar
		if(subDatos != null) aux = subDatos;
		else aux = datos;
		//Escoge el criterio 
		if(criterio != null) comp = new CompararPor(criterio);
		//Verifica que los datos existan
		if(aux != null && aux.size()>0){
			//for(int i=1; i <= aux.size(); i++){
			//	aux.get(i).compararPor(1); //Cambia el criterio de comparacion a puntuación
			//}

			//Variable auxiliar tipo Comparable[]/Peliculas[]
			Peliculas[] pelisPuntuacion = new Peliculas[aux.size()];
			for(int i=1;i<=aux.size();i++) {
				//Inicia en 1 por el conteo de las listas, pero en 0 para Comparable[]
				pelisPuntuacion[i-1]=aux.get(i);
			}
			//Organiza en orden descendente según el criterio
			if (criterio==null) ShellSort.sort(pelisPuntuacion);
			else ShellSort.sort(pelisPuntuacion, comp);

			//Verifica que hayan suficientes datos como los solicitados
			if(aux.size()>=cant){
				resp = new String[cant+2];
				//Inicializa variables de promedios
				double punt_ave = 0;
				int vote_ave = 0;
				switch(tipo){//Escoge el tipo de clasificacion
				case 1: //Orden descendente (mejores peliculas)
					for(int i=0; i < cant; i++){
						punt_ave=punt_ave + pelisPuntuacion[pelisPuntuacion.length-i-1].darPuntuacion();
						vote_ave= vote_ave + pelisPuntuacion[pelisPuntuacion.length-i-1].darVotos();
						resp[i]= i+1 + ") " + pelisPuntuacion[pelisPuntuacion.length-i-1].darInfo();
					}
					punt_ave = punt_ave/cant;
					vote_ave = vote_ave/cant;
					NumberFormat nf2 = NumberFormat.getNumberInstance();
					nf2.setMaximumFractionDigits(2);
					resp[cant] = nf2.format(punt_ave);
					resp[cant+1] = nf2.format(vote_ave); //Da la respuesta en el formato correcto
					break;
				case 2: //Orden ascendente (peores peliculas)
					for(int i=0; i < cant; i++){
						punt_ave=punt_ave + pelisPuntuacion[i].darPuntuacion();
						vote_ave= vote_ave + pelisPuntuacion[i].darVotos();
						resp[i]= i+1 + ") " + pelisPuntuacion[i].darInfo();
					}
					punt_ave = punt_ave/cant;
					vote_ave = vote_ave/cant;
					NumberFormat nf = NumberFormat.getNumberInstance();
					nf.setMaximumFractionDigits(2);
					resp[cant] = nf.format(punt_ave);
					resp[cant+1] = nf.format(vote_ave); //Da la respuesta en el formato correcto
					break;
				default:
					System.out.println("--------- \n Criterio de clasificación no válido \n---------");
					break;
				}
			}
			//Sino, da todos los datos disponiles
			else{
				System.out.println("No hay suficientes datos, estas son las peliculas disponibles: \n");
				resp = new String[aux.size()];
				switch(tipo){//Escoge el tipo de clasificacion
				case 1://Orden descendente (mejores peliculas)
					for(int i=0; i < aux.size(); i++){
						resp[i]= i+1 + ") " + pelisPuntuacion[i].darInfo();
					}
					break;
				case 2://Orden ascendente (peores peliculas)
					for(int i=0; i < aux.size(); i++){
						resp[i]= i+1 + ") " + pelisPuntuacion[pelisPuntuacion.length-i-1].darInfo();
					}
					break;
				default:
					System.out.println("--------- \n Criterio de clasificación no válido \n---------");
					break;
				}
			}
		}
		return resp;
	}

	/**
	 * Retorna un areglo de géneros 
	 * @param genero a buscar
	 * @return lista de generos o null si no se encuentra el genero
	 */
	public ArrayList<Peliculas> darPeliculasPorGenero(String pGenero){
		ArrayList<Peliculas> arrayGenero = new ArrayList<>();
		for(int i = 1; i <= datos.size(); i++){
			String[] generos = datos.get(i).darGenero();
			for (String genero : generos) {
				if(genero.equalsIgnoreCase(pGenero)){
					arrayGenero.add(datos.get(i));
					break;
				}
			}
		}
		if (arrayGenero.size()>0) return arrayGenero;
		else return null;	
	}

	/**
	 * Retorna la lista de géneros 
	 * @param genero a buscar
	 * @return lista de generos o null si no se encuentra el genero
	 */
	public Lista<Peliculas> darListaPorGenero(String pGenero){
		Lista<Peliculas> listaGenero = new ListaEncadenada<Peliculas>();
		for(int i = 1; i <= datos.size(); i++){
			String[] generos = datos.get(i).darGenero();
			for (String genero : generos) {
				if(genero.equalsIgnoreCase(pGenero)){
					listaGenero.append(datos.get(i));
					break;
				}
			}
		}
		if (listaGenero.size()>0) return listaGenero;
		else return null;	
	}

	/**
	 * @param ArrayList a evaluar
	 * @return promedio de las puntuaciones y votos de las peliculas como String
	 * promedios[0]=puntaje, promedios[1]=votos
	 */
	public String[] promedios(ArrayList<Peliculas> pLista){
		double temp_punt = 0;
		int temp_vote = 0;
		String[] resp = new String[]{"0","0"};
		for (Peliculas pelicula: pLista){
			temp_punt = temp_punt + pelicula.darPuntuacion();
			temp_vote = temp_vote + pelicula.darVotos();
		}
		if (pLista.size()>0){ //Verifica que no haya div por 0
			temp_punt = temp_punt/pLista.size();
			temp_vote = temp_vote/pLista.size();
			NumberFormat nf = NumberFormat.getNumberInstance();
			nf.setMaximumFractionDigits(1);
			resp[0] = nf.format(temp_punt);
			resp[1] = nf.format(temp_vote); //Da la respuesta en el formato correcto
		}
		return resp;
	}

	/**
	 * @param lista a evaluar
	 * @return promedio de las puntuaciones y votos de las peliculas como String
	 * promedios[0]=puntaje, promedios[1]=votos
	 */
	public String[] promediosLista(Lista<Peliculas> pLista){
		double temp_punt = 0;
		int temp_vote = 0;
		String[] resp = new String[]{"0","0"};
		for (int i=1; i<= pLista.size(); i++){
			temp_punt = temp_punt + pLista.get(i).darPuntuacion();
			temp_vote = temp_vote + pLista.get(i).darVotos();
		}
		if (pLista.size()>0){ //Verifica que no haya div por 0
			System.out.println(temp_punt + "," + pLista.size());
			temp_punt = temp_punt/pLista.size();
			temp_vote = temp_vote/pLista.size();
			NumberFormat nf = NumberFormat.getNumberInstance();
			nf.setMaximumFractionDigits(5);
			resp[0] = nf.format(temp_punt);
			resp[1] = nf.format(temp_vote); //Da la respuesta en el formato correcto
		}
		return resp;
	}

	public ArregloDinamico<Peliculas> darDatos()
	{
		return (ArregloDinamico<Peliculas>) datos;
	}

	public String[] conocerDirector(String directorAConocer) {
		ArrayList<Peliculas> peliculasDeDirector=new ArrayList<Peliculas>();
		for(int i = 1; i <= datos.size(); i++){
			if(datos.get(i).darDirector().equals(directorAConocer)){
				peliculasDeDirector.add(datos.get(i));
			}
		}
		String[] respuesta=new String[peliculasDeDirector.size()+1];
		double sumCal = 0;
		int actual =0;
		for(Peliculas pelicula:peliculasDeDirector){
			respuesta[actual]=pelicula.darNombre();
			sumCal+=pelicula.darPuntuacion();
			actual++;
		}
		double promedio=sumCal/actual;
		respuesta[peliculasDeDirector.size()]=String.format("%.2f", promedio);
		return respuesta;
	}

	public String[] conocerActor(String actorAConocer) {
		ArrayList<Peliculas> peliculasDeActor=new ArrayList<Peliculas>();
		for(int i = 1; i <= datos.size(); i++){
			for(String actor:datos.get(i).darActores()){
				if(actor.equals(actorAConocer)) peliculasDeActor.add(datos.get(i));
			}
		}
		String[] respuesta=new String[peliculasDeActor.size()+2];
		double sumCal = 0;
		int actual =0;
		for(Peliculas pelicula:peliculasDeActor){
			respuesta[actual]=pelicula.darNombre();
			sumCal+=pelicula.darPuntuacion();
			actual++;
		}
		double promedio=sumCal/actual;
		respuesta[peliculasDeActor.size()]=String.format("%.2f", promedio);
		ArrayList<String> dirQueNoSon = new ArrayList<String>();
		String currnt="Error";
		int numPel=0;
		for(Peliculas pelicula:peliculasDeActor){
			if(dirQueNoSon.contains(pelicula.darDirector())) continue;
			else{
				int cant=0;
				for(Peliculas peli:peliculasDeActor){
					if(peli.darDirector().equals(pelicula.darDirector()))cant++;
				}
				if(cant>numPel){
					numPel=cant;
					currnt=pelicula.darDirector();
				}else{
					dirQueNoSon.add(pelicula.darDirector());
				}
			}
		}
		respuesta[peliculasDeActor.size()+1]=currnt;
		return respuesta;
	}

}
