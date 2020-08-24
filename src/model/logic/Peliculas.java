package model.logic;

public class Peliculas implements Comparable<Peliculas> {

	private String nombre;
	private String director;
	private Double puntuacion;
	private Integer id;
	
	/**
	 * Constructor de la clase Peliculas
	 * @param idCasting numero de identificacion de la pelicula !=null
	 * @param dir director, si no hay informacion en el csv dir=""
	 * @param nom nombre pelicula, si no hay informacion en el csv nom=""
	 * @param punt puntuacion, si no hay informacion en el csv punt=null
	 */
	public Peliculas(Integer pID, String dir, String nom, Double punt) {
		director= dir;
		id=pID;
		nombre=nom;
		puntuacion=punt;
	}
	
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
	
	public int compareTo(Peliculas pelicula) {
	    return pelicula.darId().compareTo(this.id);
	}
}
