package model.logic;

import model.data_structures.ArregloDinamico;
import model.data_structures.IArregloDinamico;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo {
	/**
	 * Atributos del modelo del mundo
	 */
	private static final String PELICULAS_DETALLES = "/T1_202020/data/SmallMoviesDetailsCleaned.csv";
	private static final String PELICULAS_CASTING = "/T1_202020/data/MoviesCastingRaw-small.csv";
	private IArregloDinamico<Peliculas> datos;
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
	 * @param tamano
	 */
	public Modelo(int capacidad)
	{
		datos = new ArregloDinamico(capacidad);
	}
	
	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamano()
	{
		return datos.darTamano();
	}

	/**
	 * Requerimiento de agregar dato
	 * @param dato
	 */
	public void agregar(Peliculas dato)
	{	
		datos.agregar(dato);
	}
	
	/**
	 * Requerimiento buscar dato
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
	public Peliculas eliminar(Peliculas dato)
	{
		return datos.eliminar(dato);
	}

	/**
	 * Metodo adicional de dar la lista de datos
	 * @return lista de datos
	 */
	//TODO pensar como retornar esto
	public String darDatos() {
		String resp="->";
		for (int i=0; i<darTamano(); i++) {
			resp=resp + datos.darElemento(i) + "-";
		}
		return resp;
	}
	
	/**
	 * Agregar datos de archivo csv
	 */
	public void agregarDatosCsv() throws IOException{
	try {
		//Prueba la lectura de los archivos
		Reader readerDetalles = Files.newBufferedReader(Paths.get(PELICULAS_DETALLES));
		Reader readerCasting = Files.newBufferedReader(Paths.get(PELICULAS_DETALLES));
        CSVReader csvReaderD = new CSVReader(readerDetalles);
		CSVReader csvReaderC = new CSVReader(readerCasting);
        // Lee todos los datos y los agrega a una List<String[]>
        List<String[]> detalles = csvReaderD.readAll();
        List<String[]> castings = csvReaderC.readAll();
        //Combina las peliculas con informacion completa y correcta
        	for (String[] detalle : detalles) {
        		boolean encontro = false;
        		Peliculas infoD = verificarDetalles(detalle);
        		Iterator<String[]> iterator = castings.iterator();
        		while(iterator.hasNext() && encontro==false && infoD!= null) {
        			String[] temp = iterator.next();
        			Peliculas infoC = verificarCastings(temp);
        			if (infoC!=null) {
        				if(infoD.compareTo(infoC)==0) {
        					//Asigna la informacion verificada
        					String nombre = infoD.darNombre();
        					String director = infoC.darDirector();
        					Double puntuacion = infoD.darPuntuacion();
        					Integer id = infoD.darId();
            				Peliculas pelicula = new Peliculas(id, director, nombre, puntuacion);
            				datos.agregar(pelicula);
            				encontro=true;
            			}
        			}
        		}
        	}
        }
        catch (CsvException e) {
        	System.out.println("Fallo en leer algun CSV");
    		System.out.println(e.getStackTrace());
    	}
	}
	
	/**---------------------------------------------------------------------
	 * OPCION 2
	 * Agregar datos optimizado
	 */
	public void agregarDatosCsvOpt() throws IOException{
		try {
			//Prueba la lectura de los archivos
			Reader readerDetalles = Files.newBufferedReader(Paths.get(PELICULAS_DETALLES));
			Reader readerCasting = Files.newBufferedReader(Paths.get(PELICULAS_CASTING));
			//Crea el separador con ";"
			CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
			//Crea los respectivos lectores
	        CSVReader csvReaderD = new CSVReaderBuilder(readerDetalles).withCSVParser(parser).build();
			CSVReader csvReaderC = new CSVReaderBuilder(readerCasting).withCSVParser(parser).build();
	        // Lee todos los datos y los agrega a una List<String[]>
	        List<String[]> detalles = csvReaderD.readAll();
	        List<String[]> castings = csvReaderC.readAll();
	        csvReaderD.close();
	        csvReaderC.close();
	        
	        // Crea una lista Castings con los datos en formato correcto
	        IArregloDinamico<Peliculas> castLimpio = new ArregloDinamico<Peliculas>(tamanoAprox);
	        for (String[] casting : castings) {
	        	if (verificarCastings(casting)!=null) {
	        		castLimpio.agregar(verificarCastings(casting));
	        	}
	        }
	        //Combina las peliculas con informacion completa y correcta
	        for (String[] detalle : detalles) {
	        	//Comprueba que la linea tenga informacion correcta
	        	Peliculas infoD = verificarDetalles(detalle);
	        	if(infoD!=null) {
	        		// Busca la pelicula con misma identificacion en el arreglo dinamico
	        		Peliculas infoC = castLimpio.buscar(infoD);
	        		if (infoC != null) {
	        			//Asigna la informacion verificada
    					Integer id = infoC.darId();
    					String director = infoC.darDirector();
    					String nombre = infoD.darNombre();
    					Double puntuacion = infoD.darPuntuacion();
        				Peliculas pelicula = new Peliculas(id, director, nombre, puntuacion);
        				datos.agregar(pelicula);
	        		}
	        	}
	        }
		}
	    catch (CsvException e) {
	    	System.out.println("Fallo en leer algun CSV");
	    	System.out.println(e.getStackTrace());
	    }
	}
	
	
	/**
	 * Verificar que la información tiene el formato correcto
	 * @return retorna la información en el formato correcto
	 * null si hay errores
	 */
	public Peliculas verificarDetalles(String[] detalle) {
		try{
			Peliculas resp = null;
			if(detalle[16].compareTo("")!=0 && detalle[16]!=null) {
			//Cosas a verificar:
			String nombre = detalle[16];							//Nombre de la pelicula
			Integer id = Integer.parseInt(detalle[0]);				//identificación
			Double puntuacion=Double.parseDouble(detalle[17]);		//Puntuación
			//Se crea una pelicula con la informacion disponible
			resp= new Peliculas(id, "", nombre, puntuacion);
			}
			return resp;
		}
		catch(Exception e){
			System.out.println("Algun dato no tiene el formato esperado");
			return null;
		}
	}
	
	/**
	 * Verificar que la información tiene el formato correcto
	 * @return retorna la información disponible en el formato correcto
	 * null si hay errores
	 */
	public Peliculas verificarCastings(String[] casting) {
		try{
			Peliculas resp = null;
			if(casting[12].compareTo("")!=0 && casting[12]!=null) {
			//Cosas a verificar:
			String director = casting[12];							//Nombre del director
			Integer id = Integer.parseInt(casting[0]);				//identificación
			//Se crea una pelicula con la informacion disponible
			resp= new Peliculas(id, director, "", null);
			}
			return resp;
		}
		catch(Exception e){
			System.out.println("Algun dato no tiene el formato esperado");
			return null;
		}
	}
}
