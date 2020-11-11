import java.util.Random;

public class BuildHeap { 
  
    static int numSwaps = 0;

    static void heapify(int arr[], int n, int i) 
    { 
        int largest = i; 
        int l = 2 * i + 1; 
        int r = 2 * i + 2; 
   
        if (l < n && arr[l] > arr[largest]) 
            largest = l; 
   
        if (r < n && arr[r] > arr[largest]) 
            largest = r; 
   
        if (largest != i) { 
            int swap = arr[i]; 
            arr[i] = arr[largest]; 
            arr[largest] = swap; 

            numSwaps = numSwaps + 1;
   
            heapify(arr, n, largest); 
        } 
    } 
   
    static void buildLinearHeap(int arr[], int n) 
    { 
        int startIdx = (n / 2) - 1; 
  
        for (int i = startIdx; i >= 0; i--) { 
            heapify(arr, n, i); 
        } 
    }

    static void buildInsertHeap(int[] arr, int n)
    {
        for (int i = n; i >= 0; i--){
            heapify(arr, n, i);
        }
    }

    static int getSwaps(){
        return numSwaps;
    }

    static void clearSwaps(){
        numSwaps = 0;
    }
   
    static void printHeap(int arr[], int n) 
    { 
        System.out.println("Array representation of Heap is:"); 
  
        for (int i = 0; i < 50; ++i) 
            System.out.print(arr[i] + " "); 
  
        System.out.println(); 
    } 
   
    public static void main(String args[]) 
    { 
        int[] arr = new int[5000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 50000); 
        }

        int n = arr.length; 
  
        long startTime1 = System.nanoTime();
        buildInsertHeap(arr, n);
        long endTime1 = System.nanoTime();
        System.out.println("Execution time for heap insert is " + (endTime1 - startTime1) + " nanoseconds");
        System.out.println("Number of swaps: " + getSwaps());
        clearSwaps();
        printHeap(arr, n);

        long startTime2 = System.nanoTime();
        buildLinearHeap(arr, n);
        long endTime2 = System.nanoTime();
        System.out.println("Execution time for linear heap insertion is " + (endTime2 - startTime2) + " nanoseconds");
        System.out.println("Number of swaps: " + getSwaps());
        clearSwaps();
        printHeap(arr, n); 
    } 
} 