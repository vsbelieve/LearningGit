import java.io.*;
import java.util.*;

public class getmazepaths {

    public static void main(String[] args) throws Exception {
        Scanner sc= new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        ArrayList<String> res = getMazePaths(1,1,n,m);
        System.out.println(res);
    }

    // sr - source row
    // sc - source column
    // dr - destination row
    // dc - destination column
    public static ArrayList<String> getMazePaths(int sr, int sc, int dr, int dc) {
        if(sr> dr || sc>dc){
            return new ArrayList<String>();
        }
        if(sr == dr && sc == dc){
            ArrayList<String> bres = new ArrayList<String>();
            bres.add("");
            return bres;
        }
        
        ArrayList<String> hres = getMazePaths(sr,sc+1,dr,dc); // horizontal path movement
        ArrayList<String> vres = getMazePaths(sr+1,sc,dr,dc); // vertical path movement

        ArrayList<String> mres = new ArrayList<>();

        for (String rstr : hres) {
            mres.add("h"+rstr);
        }
        for (String rstr : vres) {
            mres.add("v"+rstr);
        }
        return mres;

    }
    // code to find maze paths suring jumping
    public static ArrayList<String> getMazePathsjump(int sr, int sc, int dr, int dc) {
        //base case
        if(sr == dr && sc == dc){
            ArrayList<String> bres = new ArrayList<String>();
            bres.add("");
            return bres;
        }

        ArrayList<String> paths = new ArrayList<String>(); // result array
        // horizontal move
        for (int ms = 1; ms <=dc- sc ; ms++) { // this ms - move size tells how much can we move
            ArrayList<String> hpaths = getMazePathsjump(sr,sc+ms,dr,dc);
            for (String hpath : hpaths) {
                paths.add("h"+ms + hpath);
            }
        }
        // vertical move
        for (int ms = 1; ms <=dr- sr ; ms++) { // this ms - move size tells how much can we move
            ArrayList<String> vpaths = getMazePathsjump(sr+ms,sc,dr,dc);
            for (String vpath : vpaths) {
                paths.add("v"+ms + vpath);
            }
        }
        // diagonal move increse in sc sr both
        for (int ms = 1; ms <= (dr- sr) && ms<= (dc- sc) ; ms++) { // this ms - move size tells how much can we move
            ArrayList<String> dpaths = getMazePathsjump(sr+ms,sc+ms,dr,dc);
            for (String dpath : dpaths) {
                paths.add("d"+ms + dpath);
            }
        }
    return paths;
    }
    // print maze with jumps
    
    public static void printMazePaths(int sr, int sc, int dr, int dc, String psf) {
	        
        if(sc == dc && sr == dr){
            System.out.println(psf);
            return;
        }

        for (int i = 1; i <= dc-sc; i++) {   
            printMazePaths(sr,sc+i,dr,dc,psf+"h"+i);
         }
        for (int i = 1; i <= dr-sr; i++) {
            printMazePaths(sr+i,sc,dr,dc,psf+"v"+i);
         }
        for (int i = 1; i <= dc-sc || i<= dr-sr; i++) {
            printMazePaths(sr+i,sc+i,dr,dc,psf+"d"+i);
        }
    }
}
