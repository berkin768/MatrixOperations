import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Berkin on 29.04.2016.
 */
public class Main {
    private static int [][] matrix;
    private static int [][] randomMatrix;
    private static  Scanner scn = new Scanner(System.in);
    private static Random rnd = new Random();
    private static QuickSort qck = new QuickSort();
    private static int matrixSize;
    private static int max = Integer.MIN_VALUE;
    private static int min = Integer.MAX_VALUE;
    private static int maxX = 0,maxY=0, minX = 0,minY = 0;
    private static long product;

    public static void printMatrix(int matrix [][]){
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix[1].length ; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void operations(){
        matrixSize = scn.nextInt();
        if(matrixSize < 10){
            System.out.println("Min size is 10");
            return;
        }
        matrix = new int [matrixSize][matrixSize];
        randomMatrix = new int [matrixSize][matrixSize];
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix[1].length ; j++) {
                matrix[i][j] = rnd.nextInt(1000);
            }
        }

        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix[1].length ; j++) {
                randomMatrix[i][j] = matrix[i][j];
            }
        }

        System.out.println("RANDOM MATRIX\n");
        printMatrix(matrix);

        findMaxMin(matrix);

        System.out.println("\nORDERED MATRIX\n");
        orderAscending(matrix);
        printMatrix(matrix);

        productPrime(matrix);

        IO(matrix);
    }

    public static void findMaxMin(int matrix [][]){
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix[1].length ; j++) {
                if(matrix[i][j] > max){
                    max = matrix[i][j];
                    maxX = i;
                    maxY = j;
                }
                if(matrix[i][j] < min){
                    min = matrix[i][j];
                    minX = i;
                    minY = j;
                }
            }
        }
        System.out.print("\nMax is > " + max);
        System.out.print(" | Max Number's Position's X > " + maxX);
        System.out.println(" | Max Number's Position's Y > " + maxY);

        System.out.print("\nMin is > " + min);
        System.out.print(" | Min Number's Position's X > " + minX);
        System.out.println(" | Min Number's Position's Y > " + minY);
    }

    public static void orderAscending(int matrix[][]){
        int [] lineArray = new int [matrix[0].length];
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix[1].length ; j++) {
                lineArray[j] = matrix[i][j];
            }
            qck.quickSort(lineArray,0,matrix[0].length-1);
            for (int j = 0; j < matrix[1].length ; j++) {
                matrix[i][j] = lineArray[j];
            }
        }
    }

    public static void productPrime(int matrix[][]){
        product = 1;
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix[1].length ; j++) {
                if(i == j && isPrime(matrix[i][j])){
                    product = product * matrix[i][j];
                }
            }
        }
        System.out.println("\nPrime & Diagonal Product is > " + product);
    }

    public static boolean isPrime(int number) {
        if ((number & 1) == 0) {
            if (number == 2) { // Two is prime.
                return true;
            } else {
                return false;
            }
        }
        for (int i = 3; (i * i) <= number; i += 2) {
            if ((number % i) == 0) {
                return false;
            }
        }
        return number != 1;
    }

    private static void IO(int matrix[][]){
        try {
            PrintStream out = new PrintStream(new FileOutputStream("Sonuç.txt"));
            //MATRIX SIZE PRINTED
            out.println("Matrix Size is > "+ matrixSize);
            out.println();

            //--------------RANDOM MATRIX PRINTED-------------------
            for (int i = 0; i < matrix[0].length; i++) {
                for (int j = 0; j < matrix[1].length ; j++) {
                    out.print(randomMatrix[i][j] + " ");
                }
                out.println();
            }
            //----------------MAX MIN NUMBERS PRINTED-----------------------
            out.println();
            out.print("Max is > " + max);
            out.print(" | Max Number's Position's X > " + maxX);
            out.println(" | Max Number's Position's Y > " + maxY);

            out.print("Min is > " + min);
            out.print(" | Min Number's Position's X > " + minX);
            out.println(" | Min Number's Position's Y > " + minY);

            out.println();
            //--------------ORDERED MATRIX PRINTED-------------------
            for (int i = 0; i < matrix[0].length; i++) {
                for (int j = 0; j < matrix[1].length ; j++) {
                    out.print(matrix[i][j] + " ");
                }
                out.println();
            }
            out.println();
            out.println("Prime product is > " + product);

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        operations();
    }
}