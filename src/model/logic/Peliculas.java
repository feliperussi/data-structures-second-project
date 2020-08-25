package model.logic;

import java.util.Date;

public class Peliculas implements Comparable<Peliculas> {

	private String nombre;
	private String director;
	private Date fecha;
	private String[] actores;
	private Double puntuacion;
	private Integer id;
	private String[] genero;
	/**
	 * Atributo que establece como comparar una pelicula
	 * LISTA: 1 = por director, else (o 0) = por ID
	 */
	private int tipoComp = 0; 
	
	/**
	 * Constructor de la clase Peliculas
	 * @param idCasting numero de identificacion de la pelicula !=null
	 * @param dir director, si no hay informacion en el csv dir=""
	 * @param nom nombre pelicula, si no hay informacion en el csv nom=""
	 * @param punt puntuacion, si no hay informacion en el csv punt=null
	 * @param act lista con los actores de la película 
	 * @param rdate fecha de estreno de la pelicula
	 */
	public Peliculas(Integer pID, String dir, String nom, Double punt, String[] act, Date rdate, String[] gen) {
		director = dir;
		id = pID;
		nombre = nom;
		puntuacion = punt;
		actores = act;
		fecha = rdate;
		genero = gen;
	}
	
	//Métodos de devolver información
	public String darNombre() {
		return nombre;
	}
	
	public String darDirector() {
		return director;
	}
			
	public Double darPuntuacion() {
		return puntuacion;
	}
	
	public Integer darId() {
		return id;
	}
	
	public String[] darActores() {
		return actores;
	}
	
	public Date darFecha() {
		return fecha;
	}
	
	public String[] darGenero() {
		return genero;
	}
	
	/**
	 * Cambia el método de comparación a ID
	 */
	public void buscarPorID() {
		tipoComp = 0;
	}
	
	/**
	 * Cambia el método de comparación a director
	 */
	public void buscarPorDirector() {
		tipoComp = 1;
	}
	
	/**
	 * Requerimiento de Comparable de implementar metodo compareTo 
	 */
	public int compareTo(Peliculas pelicula) {
		if (tipoComp == 1) {
			return pelicula.darDirector().compareTo(this.director);
		}
		else {
			return pelicula.darId().compareTo(this.id);
		}
	}
	
	/**
	 * Retorna la informacion de la pelicula en formato String
	 * @return String con los datos de la pelicula
	 */
	public String darInfo() {
		String str = "Película: " + nombre + "- ID: " + id + ", \n Genero(s): ";
		//Agrega los generos
		for (String i : genero) {
			str = str + i + ",";
		}
		//Agrega los actores
		str = str + "\n Actores: ";
		for (String i : actores) {
			str = str + i + ",";
		}
		str = str + "\n ---------------------";
		return str;
	}
}
