package model.data_structures;

import model.logic.CompararPor;
import model.logic.Peliculas;

public class ShellSort {

    @SuppressWarnings("all")
    public static void sort(Comparable[] array) {
        int fast=1;
        while(fast/3<array.length){
            fast=fast*3+1;
        }
        for (int gap = fast; gap > 0; gap /= 3) {
            for (int i = gap; i < array.length; i++) {
                Comparable val = array[i];
                int j;
                for (j = i; j >= gap && array[j - gap].compareTo(val) > 0; j -= gap) {
                    array[j] = array[j - gap];
                }
                array[j] = val;
            }
        }
    }

    /**
     * Realiza el ShellSort específico para tipo Peliculas
     * @param Arreglo de peliculas
     * @param comparador a usar
     */
    @SuppressWarnings("all")
    public static void sort(Peliculas[] array, CompararPor comp) {
        CompararPor comparator = new CompararPor(); // Por default se compara por id
        if (comp != null){ //establece nuevo tipo de
            comparator = comp;
        }
        int fast=1;
        while(fast/3<array.length){
            fast=fast*3+1;
        }
        for (int gap = fast; gap > 0; gap /= 3) {
            for (int i = gap; i < array.length; i++) 
            {
                Peliculas val = array[i];
                int j;
                for (j = i; j >= gap && comparator.compare(array[j - gap],val) > 0; j -= gap) {
                    array[j] = array[j - gap];
                }
                array[j] = val;
            }
        }
    }
    
}