
import java.util.*;

public class DPtabulationClimbJump {

    public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int arr [] = new int [n]; 
    for (int i = 0; i < arr.length; i++) {
        arr[i] = sc.nextInt();
    }
    // declaring a dp - storeage and meaning
    int dp [] = new int [n+1]; //size is n+1 for n elemetns both inclusive
    dp[n] =1; // sorta base case- find direction of smaller problem

    
    for (int i = n-1; i >=0; i--) { // here i+j < dp.length is done bcoz we dont want invalid paths where stair at i do not jump out of dp.length
        for (int j = 1; j <= arr[i] && i + j < dp.length; j++) {
            dp[i] +=  dp[i+j]; // this tells the path from i to n via all paths from j so, adding them up
        }
    }
    
    System.out.println(dp[0]);
    }
    
}