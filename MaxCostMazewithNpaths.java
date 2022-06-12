import java.io.*;
import java.util.*;

public class MaxCostMazewithNpaths {

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


        // creation and meaning - we are storing at every index the max gold found that can be found
        int [][] dp = new int[n][m];
        // find direction - the destination is the easy problem
         
        for (int i = m-1; i >= 0; i--) { // loops travels in column
            for (int j = n-1; j >= 0; j--) { // loops travels in row
                if(i == m-1 ){  // divide the test cases based on the traversal restrictions - destination case
                   dp[j][i] = arr[j][i];
                //    System.out.println("dp" + i + " "+ j + " "+ dp[i][j]);
                }
                else if( j == 0){ // case of last row
                    dp[j][i] = arr[j][i] + Math.max(dp[j][i+1],dp[j+1][i+1]);
                }
                else if( j == n-1){ // case of last col
                    dp[j][i] = arr[j][i] + Math.max(dp[j][i+1],dp[j-1][i+1]);
                }
                else{ // rest of the matrix cases
                    // System.out.println("dp" + j + " "+ i + " "+ dp[j][i]); 
                    dp[j][i] = arr[j][i] + Math.max(dp[j-1][i+1],Math.max(dp[j+1][i+1],dp[j][i+1]));
                    
                }     
            }
        }
        int max = Integer.MIN_VALUE;
        for (int k = 0; k < n; k++) {
            max = Math.max(dp[k][0],max);
        }
        System.out.println(max);
    }

}