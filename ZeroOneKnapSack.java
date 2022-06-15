
import java.io.*;
import java.util.*;

public class ZeroOneKnapSack {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int v[] = new int [n];
        int a[] = new int [n];

        for (int i = 0; i < n; i++) {
            v[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        
        int tar = sc.nextInt();

        int [][] dp = new int [a.length+1][tar+1]; // dynamic array

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (a[i-1]<= j){
                   dp[i][j] = Math.max(v[i-1] + dp[i-1][j-a[i-1]], dp[i-1][j]);   // find max of the two and add the max value - crucial step
                }else{
                    dp[i][j] = dp[i-1][j];
                }
                
            }
        }
        System.out.println(dp[dp.length-1][dp[0].length-1]);

    }
}