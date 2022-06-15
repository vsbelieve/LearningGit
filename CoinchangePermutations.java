import java.io.*;
import java.util.*;

public class CoinchangePermutations  {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a[] = new int [n];

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        
        int amt = sc.nextInt();

        int dp[] = new int[amt+1]; // dynamic array
        dp[0] =1; // way to pay 0 is - don't pay at all
        
        for (int i = 1; i < dp.length; i++) { // loop runs from i = 1;

            for (int coin: a) {
                if(coin <= i){
                  dp[i] += dp[i-coin];
                }
            }
        }
        System.out.println(dp[dp.length-1]);
    }
}