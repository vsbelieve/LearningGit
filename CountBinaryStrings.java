import java.io.*;
import java.util.*;

public class CountBinaryStrings {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        //declare the dp's for string ending at 0 and other ending w/ 1
        int dp0 [] = new int[n+1];
        int dp1 [] = new int[n+1];
    
        dp0[1] = 1;
        dp1[1] = 1;
    
        for (int i = 2; i < n+1; i++) {
            dp1[i] = dp1[i-1]+ dp0[i-1]; // add both strings ending with 0s and 1s
            dp0[i] = dp1[i-1]; //add the strings that ended with 1 previously to make new string w/ 0 at end
        }
        System.out.println(dp1[n]+dp0[n]);
     }
     // alternate faster code w/ no space use;
     public static void CountBinaryStrings2(int n) {
        int oczeros = 1;
        int ocones = 1;
        
        for (int i = 2; i < n+1; i++) {
            int nczeros = ocones;
            int ncones = ocones + oczeros;
    
        // update the old counts  
        oczeros = nczeros;
        ocones = ncones;
        }
    
        System.out.println(ocones+oczeros);   
     }
    
    }  

