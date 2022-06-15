import java.util.*;

public class printsubsq {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        printSS(str,"");
    }

    public static void printSS(String str, String ans) {
      if(str.length() == 0){
          System.out.println(ans); // print when question or str is 0
          return;
      }
     
      char q = str.charAt(0);
      String roq = str.substring(1); // restofstring

       printSS(roq,ans+q);

      printSS(roq,ans+""); // recursive call for when charater isn't included in the ans
      
    }

    static String [] codes = {".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz"};
    public static void printKPC(String str, String asf) { // key Pad count
        if(str.length() == 0){
           System.out.println(asf); 
            return;
        }
     char ch = str.charAt(0); // this is ans
     String roq = str.substring(1); // remaining string i.e. ques
     
     String codeforch = codes[ch-'0'];
     for (int i =0; i<codeforch.length() ;i++) {
         char p = codeforch.charAt(i);
         printKPC(roq,asf+p);
     }
        
    }
 
}