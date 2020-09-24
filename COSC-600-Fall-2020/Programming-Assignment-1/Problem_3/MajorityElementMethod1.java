import java.io.*; 
import java.util.Arrays;
import java.util.Scanner;
import java.util.Timer;
  
class MajorityElement { 
      
// Function to find Majority element  
// in an array using brute force method
// O(n^2) time complexity
static void findMajority(int arr[], int n)  
{  
    int maxCount = 0;  
    int index = -1; // sentinels  
    for(int i = 0; i < n; i++)  
    {  
        int count = 0;  
        for(int j = 0; j < n; j++)  
        {  
            if(arr[i] == arr[j])  
            count++;  
        }  
          
        // update maxCount if count of  
        // current element is greater  
        if(count > maxCount)  
        {  
            maxCount = count;  
            index = i;  
        }  
    }  
      
    // if maxCount is greater than n/2  
    // return the corresponding element  
    if (maxCount > n/2)  
    System.out.println (arr[index]);  
      
    else
    System.out.println ("No Majority Element");  
}  

public static int[] readFiles(String file) {
    try {
        File f = new File(file);
        Scanner s = new Scanner(f);
        int ctr = 0;
        while(s.hasNextInt()) {
            ctr++;
            s.nextInt();
        }
        int[] arr = new int[ctr];

        Scanner s1 = new Scanner(f);

        for(int i = 0; i < arr.length; i++)
            arr[i] = s1.nextInt();

        return arr;

    } catch(Exception e) {
        return null;
    }
}
  
// Driver code  
    public static void main (String[] args) { 
  
    int arr1[] = readFiles("number_files/Majex1.txt");
    int arr2[] = readFiles("number_files/Majex2.txt");
    int arr3[] = readFiles("number_files/Majex3.txt");
    int arr4[] = readFiles("number_files/Majex4.txt");  
    int n1 = arr1.length;
    int n2 = arr2.length;
    int n3 = arr3.length;
    int n4 = arr4.length;
      
    // Function calling
    long startTime1 = System.nanoTime();
    findMajority(arr1, n1);
    long endTime1 = System.nanoTime();
    System.out.println("Execution time for arr1 is " + (endTime1-startTime1) + " nanoseconds"); 

    long startTime2 = System.nanoTime();
    findMajority(arr2, n2);
    long endTime2 = System.nanoTime();
    System.out.println("Execution time for arr2 is " + (endTime2-startTime2) + " nanoseconds");

    long startTime3 = System.nanoTime();
    findMajority(arr3, n3);
    long endTime3 = System.nanoTime();
    System.out.println("Execution time for arr3 is " + (endTime3-startTime3) + " nanoseconds");

    long startTime4 = System.nanoTime();
    findMajority(arr4, n4);
    long endTime4 = System.nanoTime();
    System.out.println("Execution time for arr4 is " + (endTime4-startTime4) + " nanoseconds");
    }
} 
