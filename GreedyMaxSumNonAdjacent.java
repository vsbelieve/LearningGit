import java.io.*;
import java.util.*;
// first greedy problem
public class GreedyMaxSumNonAdjacent {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int [] arr = new int [n];

        for (int i = 0; i < n; i++){
            arr[i]= sc.nextInt();
        }
        int oinc = arr[0];
        int oexc = 0;
        for (int i = 1; i < n; i++) {
            int ninc = oexc + arr[i];
            int nexc = Math.max(oinc,oexc);
        
        oinc = ninc;
        oexc = nexc;
        }

        System.out.println( (int) Math.max(oinc,oexc));
    }
}