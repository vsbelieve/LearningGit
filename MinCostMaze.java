import java.io.*;
import java.util.*;

public class MinCostMaze {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int [][] arr = new int [n][m];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j]= sc.nextInt();
            }
        }
        // creation and meaning - we are storing at every index the min cost to destination
        int [][] dp = new int[n][m];
        // find direction - the destination is the easy problem
         
        for (int i = n-1; i >= 0; i--) { // loops travels in row
            for (int j = m-1; j >= 0; j--) { // loops travels in col
                if(i == n-1 && j == m-1){ 
                   dp[i][j] = arr[i][j];
                //    System.out.println("dp" + i + " "+ j + " "+ dp[i][j]);
                }
                else if( i == n-1){
                    dp[i][j] = arr[i][j] + dp[i][j+1];
                }
                else if( j == m-1){
                    dp[i][j] = arr[i][j] + dp[i+1][j];
                }
                else{
                    dp[i][j] = arr[i][j] + Math.min(dp[i+1][j],dp[i][j+1]);
                }     
            }
        }

        System.out.println(dp[0][0]);
    }

}