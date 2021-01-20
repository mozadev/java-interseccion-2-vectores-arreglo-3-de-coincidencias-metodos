
import java.util.Arrays;
import java.util.Scanner;

public class IntersectionArrays {

    public static void main(String[] args) {
        // variables
        IntersectionArrays op;
        int target;
        int[] arr1, arr2, arr3; // declaracion de arreglos 
        long startTime, endTime;
        // initialize variables
        op = new IntersectionArrays();
        arr1 = op.enterValues(op.enterUniqueValue("Cantidad de valores para el primer arreglo => "));
        arr2 = op.enterValues(op.enterUniqueValue("Cantidad de valores para el segundo arreglo => "));

        //cod search
        /*
        target = op.enterUniqueValue("Ingrese valor a buscar =>");
        startTime = System.nanoTime();
        op.search(arr1,target,"array 1");
        op.search(arr2,target,"array 2");
        endTime = System.nanoTime();
         */
        //cod intersection
        startTime = System.nanoTime();// devuelve el valor actual del temporizador del sistema 
        arr3 = op.intersectionArray(arr1, arr2);// arreglo interseccion
        endTime = System.nanoTime();// devuelve el valor actual del temporizador del sistema 

        // print arrays
        System.out.println("\n");
        System.out.println("DATA");
        System.out.println("Array1 => " + Arrays.toString(arr1)); //mostrar los valores del primer arreglo
        System.out.println("Array2 => " + Arrays.toString(arr2)); // mostrar los valores del segundo arreglo
        System.out.println("\nRESULT");
        System.out.println("Array3 =>  " + Arrays.toString(arr3));// mostrar el arreglo cuyos valores se repiten 
                                                                   // en el primer y segundo arreglo es decir, 
                                                                   // un arreglo nuevo (inteserccion)

        //print timers
        System.out.println("Tiempo transcurrido(ns): " + (endTime - startTime) + " ns\n");// muestra el tiempo transurrido en nanosegundos 
                                                                                          // que duro la ejecucion del programa
        //System.out.println("Tiempo transcurrido(s): " + (endTime - startTime)/Math.pow(10,-9) + " s\n");

    }

    // enter length values
    private int enterUniqueValue(String label) { // metodo de entrada de unico valor
        Scanner entry;   // creamos un objeto de la clase entry
        entry = new Scanner(System.in); // inicializamos el objeto
        System.out.print("\n" + label); // mensaje de entrada de datos
        return entry.nextInt();// captura de entrada
    }

    // enter values to array
    private int[] enterValues(int length) { //metodo de retorna un arreglo de tipo entero
        //declarando variables
        int[] arr; // declarando un arreglo
        int i; // declarando una variable de tipo entero
        Scanner entry;  // creamos un objeto "entry" de la clase Scanner para poder obtener la entrada
                        // del usuario
                        
        // inicializando variables variables
        entry = new Scanner(System.in);// inicializamos el objeto "entry" de la clase scanner
        arr = new int[length]; // inicializamos el arreglo "arr" con un tamaño que viene por parametro "length"

        // ingresando los valores mediante un for
        for (i = 0; i < length; i++) {// i=0 establece una variable antes de que se inicie el blucle for, 
                                       // "i<length": define la condicion para que se ejecute el bucle for
                                      // si la condición es true, el bucle se iniciará de nuevo, si es false, el bucle finalizará.
                                       //(i++) aumenta un valor cada vez que se ha ejecutado el bloque de código en el bucle
            System.out.print("Valor[" + i + "] = "); // imprime un mensaje para que ingreses un valor i del arreglo
            arr[i] = entry.nextInt();     //asignamos al arreglo "arr" los valores que que se capturan mediante el objeto "entry" 
        }                               // de la clase Scanner mediante su metodo nexInt() que se utiliza para leer enteros
        return arr;    //  se retorna el arreglo "arr" con todos los enteros ingresados mediante el for            
    }
   
    private int[] intersectionArray(int[] arr1, int[] arr2) {
        if ((arr1.length > arr2.length)) { //si el arreglo 1 es mayor que el arreglo 2
            return intersection(arr1, arr2); // retorna un arreglo de tamaño del arreglo mas pequeño
        } else if (arr2.length > arr1.length) {
            return intersection(arr2, arr1);
        } else {
            return intersection(arr1, arr2);
        }
    }

    private int[] intersection(int[] arr1, int[] arr2) {
        int pos, i;
        int[] result;
        pos = 0;
        result = new int[arr2.length];
        for (i = 0; i < arr2.length; i++) {
            if (binarySearch(arr1, arr2[i])) {
                result[pos] = arr2[i];
                pos++;
            }
        }
        return result;
    }

    private boolean sequentialSearch(int[] arr, int target) {
        int index = 0;
        boolean find = false;
        while (index < arr.length && !find) {
            if (arr[index] == target) {
                find = true;
            } else {
                index++;
            }
        }
        return find;
    }

    private boolean binarySearch(int[] arr, int target) {
        boolean find;
        int max, min, guess;
        int[] orderingArray;
        orderingArray = arr.clone();
        find = false;
        min = 0;
        max = arr.length - 1;
        ordering(orderingArray);// ordeno el arreglo clonado de ma
        while (!find && (min <= max)) {
            guess = (max + min) / 2;
            if (orderingArray[guess] == target) {
                find = true;
            } else if (orderingArray[guess] < target) {
                min = guess + 1;
            } else {
                max = guess - 1;
            }

        }
        return find;
    }

    private void ordering(int[] array) {
        int i, j, aux;
        for (i = 0; i < array.length; i++) {
            j = i;
            while (j >= 0) {
                if (array[i] < array[j]) {
                    aux = array[i];
                    array[i] = array[j];
                    array[j] = aux;
                    i = j;
                }
                j--;
            }
        }
    }

    private void search(int[] arr, int target, String reference) {
        if (sequentialSearch(arr, target)) {
            System.out.println("El valor " + target + " ha sido encontrado en " + reference);
        } else {
            System.out.println("El valor " + target + " no ha sido encontrado en" + reference);
        }
    }
}
