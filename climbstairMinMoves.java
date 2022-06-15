
import java.io.*;
import java.util.*;

public class climbstairMinMoves {

    public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int arr [] = new int [n]; 
    for (int i = 0; i < arr.length; i++) {
        arr[i] = sc.nextInt();
    }
    // declaring a dp - storeage and meaning
    Integer dp [] = new Integer [n+1]; //size is n+1 for n elemetns both inclusive
    dp[n] = 0; // sorta base case- find direction of smaller problem

    
     for (int i = n-1; i >=0; i--) { // here i+j < dp.length is done bcoz we dont want invalid paths where stair at i do not jump out of dp.length
        if(arr[i] > 0){
          int mn = Integer.MAX_VALUE; // take a min for comparison
          for (int j = 1; j <= arr[i] && i + j < dp.length; j++) {
            if( dp[i + j] != null){ 
              mn = Math.min(mn,dp[i + j]); // avoid null getting compared - to avoid error
            }   
          }
            if(mn != Integer.MAX_VALUE){
              dp [i] = mn+ 1;
            } 
        }
     }
    
    System.out.println(dp[0]);
    }  
}