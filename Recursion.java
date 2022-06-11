import java.io.*;
import java.util.*;

public class Recursion {

    public static void main(String[] args) throws Exception {
       Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(maxOfArray(arr,0));
        
    }
    //display array 
    public static void displayArr(int[] arr, int idx){
        if(idx==arr.length){
            return;
        }
        
        System.out.println(arr[idx]);
        displayArr(arr,idx+1);
        
    }
    //display reverse of array
    public static void displayArrReverse(int[] arr, int idx) {
        if(idx==arr.length){
           return;
           }

           displayArrReverse(arr,idx+1);
           System.out.println(arr[idx]); // backtrack results in the output
       
   }
   // max in an array
    public static int maxOfArray(int[] arr, int idx){
        if(idx == arr.length-1) return arr[idx];

        int max = maxOfArray(arr,idx+1);
        max = Math.max(arr[idx],max);
        
        return max;
    }
    // recursion to find the 1st idx of value in array
    public static int firstIndex(int[] arr, int idx, int x){
        if(idx == arr.length){
            return -1;
        }
        if(x == arr[idx]){
            return idx;
        } else{
            int res = firstIndex(arr, idx+1, x); 
            return res;   
        }
        
    }
    // last index of a vlaue in array
    public static int lastIndex(int[] arr, int idx, int x){
        if(idx == 0) return -1;

        if(x == arr[idx]){
            return idx;
        } else{
        
        int res  = lastIndex(arr,idx-1,x);
        return res;
        }
  
    }

}