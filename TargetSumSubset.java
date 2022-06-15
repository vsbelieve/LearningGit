import java.io.*;
import java.util.*;

public class TargetSumSubset {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a[] = new int [n];

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        
        int tar = sc.nextInt();
        boolean [][] dp = new boolean [a.length+1][tar+1]; // rows and columns depend on arr.length and target
    
        for (int i = 0; i < a.length+1; i++) {
            for (int j = 0; j < tar+1; j++) {
                if(j ==0){
                dp[i][j] = true;
                } else if(i == 0){
                  dp[i][j]= false;
                }
                else if(a[i-1]> j && dp[i-1][j]){
                    dp[i][j] = true;
                } else if (a[i-1]<= j && (dp[i-1][j] || dp[i-1][j-a[i-1]])){
                   dp[i][j] = true;   
                }else{
                   dp[i][j] = false;
                }
                
            }
        }
        System.out.println(dp[a.length][tar]);
    }
}