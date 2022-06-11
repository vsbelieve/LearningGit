
import java.util.*;

public class nQueens {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int [] [] arr = new int [n][n];
        printNQueens(arr,"",0);
    }

    public static boolean IsSafePlace(int[][] chess, int row, int col){
        // for loop to check vertically
        for (int i = row-1, j = col; i >= 0; i--) {
            if(chess[i][j] == 1){ // queeen is spotted
                return false;
            } 
        }
        // for loop to check left diagonal 
        for (int i = row-1, j = col-1; i >= 0 && j >= 0; i--, j--) {
            if(chess[i][j] == 1){ // queeen is spotted
                return false;
            } 
        }
        // for loop to check right diagonal 
        for (int i = row-1, j = col+1; i >= 0 && j < chess.length; i--, j++) {
            if(chess[i][j] == 1){ // queeen is spotted
                return false;
            } 
        }
        return true;
    }

    public static void printNQueens(int[][] chess, String qsf, int row) {
    if(row == chess.length){
        System.out.println(qsf+ ".");
        return;
    }
    for (int col = 0; col < chess.length; col++) {
        if(IsSafePlace(chess,row,col) == true){ // is place is safe then we can palce the queen there
        chess[row][col] = 1;
       printNQueens(chess,qsf + row  + "-" + col + ", ",row + 1);
        chess[row][col] = 0;
        }
    }    
    
    }
}