package model.logic;

import java.util.Comparator;

public class CompararPor implements Comparator<Peliculas> {
    // Posibles criterios de comparacion
    // Default o 0: ID
    // 1 = puntuacion, 2 = votos
    private int comparador;

    /**
     * Metodo constructor sin parametro estalece comparacion por id
     */
    public CompararPor() {
        comparador = 0;
    }

    /**
     * Metodo constructor con parametro estalece comparacion segun el tipo
     * 
     * @param tipo: 0 = id, 1 = puntuacion, 2 = votos En caso de no ser un tipo
     *              valido se establece por id
     */
    public CompararPor(int tipo) {
        if (tipo != 0 && tipo != 1 && tipo != 2) {
            System.out.println("No es un criterio valido, se compara por id por default");
            comparador = 0;
        } else
            comparador = tipo;
    }

    /**
     * Establece el tipo de comparacion del comparador
     * 
     * @param tipo: default o 0 = id, 1 = puntuacion, 2 = votos En caso de no ser un
     *              tipo valido se establece por id
     */
    public void cambiarCriterio(Integer tipo) {
        if (tipo != 0 && tipo != 1 && tipo != 2) {
            System.out.println("No es un criterio valido, se compara por id por default");
            comparador = 0;
        } else
            comparador = tipo;
    }

    /**
     * Compara los elementos (Peliculas) bajo el criterio establecido
     * 
     * @param tipo: default o 0 = id, 1 = puntuacion, 2 = votos
     */
    @Override
    public int compare(Peliculas peli1, Peliculas peli2) {
        switch (comparador) {
            case 1:
                return peli1.darPuntuacion().compareTo(peli2.darPuntuacion());
            case 2:
                return peli1.darVotos().compareTo(peli2.darVotos());
            default:
                return peli1.darId().compareTo(peli2.darId());
        }
    }
}
