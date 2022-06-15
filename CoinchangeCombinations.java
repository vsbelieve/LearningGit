import java.io.*;
import java.util.*;

public class CoinchangeCombinations {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a[] = new int [n];

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        
        int amt = sc.nextInt();

        int dp[] = new int[amt+1]; // dynamic array

        for (int i = 0; i < a.length; i++) {
            dp[0] =1; // way to pay 0 is - don't pay at all
            int coin = a[i];
            for (int j = coin; j < dp.length; j++) {
                dp[j] += dp[j-coin];
            }
        }
        System.out.println(dp[dp.length-1]);
    }
}