import java.util.*;

public class DPclimbstairs {

    public static void main(String[] args) throws Exception {
       Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int dp [] = new int [n+1]; //size is n+1 due to storeing n chracters at 1 to n
    System.out.println(countpaths(n,dp));
    }
    public static int countpaths(int n,int [] qb) {
        //base cases
        if(n == 0){
            return 1;
        }
        else if(n <0){
            return 0;

        }
        else if(qb[n] != 0){ // base case for memoization 
            return qb[n];
        }
        // faith 
        int nm1 = countpaths(n-1, qb);
        int nm2 = countpaths(n-2, qb);
        int nm3 = countpaths(n-3, qb);
        int cp = nm1+nm2+nm3;
        //store answers to use later - memoization in dynamic programming
        qb [n] = cp;
        return cp;
        
    }
}