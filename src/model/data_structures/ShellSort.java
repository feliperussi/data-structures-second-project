package model.data_structures;

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
    
}