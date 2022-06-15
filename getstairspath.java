import java.io.*;
import java.util.*;

public class getstairspath {

    public static void main(String[] args) throws Exception {
        Scanner sc= new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<String> res = getStairPaths(n);
        System.out.println(res);
    }

    public static ArrayList<String> getStairPaths(int n) {
       if(n == 0){
          ArrayList<String> bres = new ArrayList<String>();
          bres.add("");
          return bres; 
       } else if(n < 0){
          ArrayList<String> bres = new ArrayList<String>();
            // do not add a path in arraylist return empty list
          return bres;
       }
 
       ArrayList<String> rres1 = getStairPaths(n-1);
       ArrayList<String> rres2 = getStairPaths(n-2);
       ArrayList<String> rres3 = getStairPaths(n-3);
       ArrayList<String> mres = new ArrayList<String>();
       for (String path : rres1) {
           mres.add(1 + path);
       }
       for (String path : rres2) {
           mres.add(2 + path);
       }
       for (String path : rres3) {
           mres.add(3 + path);
       }

    return mres;
    }
}