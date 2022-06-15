import java.io.*;
import java.util.*;

public class PaintHouse {

    public static void PaintHouse1(int[][]arr){
        int r = arr[0][0];
        int b = arr[0][1];
        int g = arr[0][2]; // fill em w/ first array element
        
        for (int i = 1; i < arr.length; i++) {
            int nr = Math.min(b,g) + arr[i][0];
            int nb = Math.min(r,g) + arr[i][1];
            int ng = Math.min(b,r) + arr[i][2];
        
        r = nr;
        b = nb;
        g = ng;
        }

        int res = Math.min(g,Math.min(r,b));
        System.out.println(res);
        
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int [][] arr = new int [n][3];

        for (int i = 0; i < n; i++){
          for (int j = 0; j < 3; j++)  
            arr[i][j]= sc.nextInt();
        }

        PaintHouse1(arr);
    }

    
}