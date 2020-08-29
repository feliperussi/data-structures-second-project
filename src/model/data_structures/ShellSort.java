package model.data_structures;

public class ShellSort {

    @SuppressWarnings("all")
    public static void sort(Comparable[] array) {
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
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
    
}