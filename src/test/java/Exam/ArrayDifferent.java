package Exam;

import java.util.Random;
import java.util.Scanner;

public class ArrayDifferent {

    public static void main(String[] args) {
        int[] arr = createRandomArr(getArraySize());
        printArr(arr);
        int[] sortedArr = sortArray(arr);
        printArr(sortedArr);
        int differentElemCount = 1;
        for (int i = 0; i < sortedArr.length - 1; i++) {
            if (arr[i] != arr[i + 1]) {
                differentElemCount++;
            }
        }
        System.out.println("There are " + differentElemCount +" different numbers in array");
    }

    public static int getArraySize() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter size of the arraay with random numbers: ");
        return scan.nextInt();
    }

    public static int[] createRandomArr(int arrSize) {
        Random rand = new Random();
        int[] arr = new int[arrSize];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(10);
        }
        return arr;
    }

    public static void printArr(int[] arr) {
        for (int arrElem : arr) {
            System.out.print(arrElem + " ");
        }
        System.out.println();
    }

    public static int[] sortArray (int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length-1; j++) {
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        return arr;
    }
}
