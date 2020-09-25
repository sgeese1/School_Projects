import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Timer;

class MajorityElement {

    // Function to find Majority element
    // in an array using brute force method
    // O(n^2) time complexity
    public static int findMajority(int nums[]) {
        int majorityCount = nums.length / 2;

        for (int num : nums) {
            int count = 0;
            for (int elem : nums) {
                if (elem == num) {
                    count += 1;
                }
            }

            if (count > majorityCount) {
                return num;
            }

        }

        return -1;
    }

    public static int[] readFiles(String file) {
        try {
            File f = new File(file);
            Scanner s = new Scanner(f);
            int ctr = 0;
            while (s.hasNextInt()) {
                ctr++;
                s.nextInt();
            }
            int[] arr = new int[ctr];

            Scanner s1 = new Scanner(f);

            for (int i = 0; i < arr.length; i++)
                arr[i] = s1.nextInt();

            return arr;

        } catch (Exception e) {
            return null;
        }
    }

    // Driver code
    public static void main(String[] args) {

        int arr1[] = readFiles("number_files/Majex1.txt");
        int arr2[] = readFiles("number_files/Majex2.txt");
        int arr3[] = readFiles("number_files/Majex3.txt");
        int arr4[] = readFiles("number_files/Majex4.txt");

        // Function calling
        long startTime1 = System.nanoTime();
        findMajority(arr1);
        long endTime1 = System.nanoTime();
        System.out.println("Execution time for arr1 is " + (endTime1 - startTime1) + " nanoseconds");

        long startTime2 = System.nanoTime();
        findMajority(arr2);
        long endTime2 = System.nanoTime();
        System.out.println("Execution time for arr2 is " + (endTime2 - startTime2) + " nanoseconds");

        long startTime3 = System.nanoTime();
        findMajority(arr3);
        long endTime3 = System.nanoTime();
        System.out.println("Execution time for arr3 is " + (endTime3 - startTime3) + " nanoseconds");

        long startTime4 = System.nanoTime();
        findMajority(arr4);
        long endTime4 = System.nanoTime();
        System.out.println("Execution time for arr4 is " + (endTime4 - startTime4) + " nanoseconds");
    }
}
