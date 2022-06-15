import java.util.Scanner;

public class exitpointofmatrix {
  
  public static void main(String[] args) throws Exception {
      Scanner sc = new Scanner(System.in);
      int n = sc.nextInt();
      int m = sc.nextInt();
      int [] [] arr= new int [n][m];

      for (int i = 0; i < n; i++) {
          for (int j = 0; j < m; j++) {
              arr[i][j] = sc.nextInt();
          }
      }
      int x = sc.nextInt();
      int i = 0;
      int j = arr.length -1;
      
      while (i< arr.length && j>= 0 ) {
          
        if(arr[i][j] == x){
            System.out.println(i);
            System.out.println(j);
        }
        else if(arr[i][j] < x){
            i++;
        } else if(arr[i][j] > x){
            j--;
        }
      }
      System.out.println(" not found");

      int dir = 0; // this represents direction 0 - e, 1- s, 2 - w , 3 - n 
      i = 0;
      j = 0;

      while (true) {
         
        dir = (dir + arr[i][j]) % 4; // helps in traversing the matrix

        if(dir == 0 ){ // east
            j++;
          } else if(dir == 1){ // south
            i++;
          } else if(dir == 2){ // west
            j--;
        } else{ // north
            i--;
        }

        // check if we are out of the matrix
        if(i<0){
            System.out.println(i+1);
            System.out.println(j);
            break;
        } else if( j <0){
            System.out.println(i);
            System.out.println(j+1);
            break;
        } else if( i == n){
            System.out.println(i-1);
            System.out.println(j);
            break;
        } else if (j == m){
            System.out.println(i);
            System.out.println(j-1);
            break;
        }
      }
  }  
}
