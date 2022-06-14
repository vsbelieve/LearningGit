import java.io.*;
import java.util.*;

public class MergeKsortedLists {
   //make a new Pair class to be used
   public static class Pair implements Comparable<Pair> {
      int li;
      int di;
      int val;
      //make a constructor
      Pair(int li, int di, int val){
         this.li = li;
         this.di = di;
         this.val = val;
      }

      // making the comparable function - now with this, PQ can decide which Pair is greater and which is not
      public int compareTo(Pair o) {
         return this.val - o.val;
      }
   }
   public static ArrayList<Integer> mergeKSortedLists(ArrayList<ArrayList<Integer>> lists){
      ArrayList<Integer> rv = new ArrayList<>();

      PriorityQueue<Pair> pq = new PriorityQueue<>(); // Pair priority queue
      for (int i = 0; i<lists.size(); i++) {
         Pair np = new Pair(i, 0, lists.get(i).get(0));
         pq.add(np); 
         //adding new pair in PQ
      }

      while(pq.size()>0){
         Pair p = pq.remove(); // pair is removed - sorted 1st element
         rv.add(p.val); // add it's value to merge arraylist
         p.di++;
         // add only if list has elements
         if(p.di < lists.get(p.li).size()){
            p.val = lists.get(p.li).get(p.di);
            pq.add(p);
             // adding the next element of the list where we just removed 
         }
      }

      return rv;
   }

   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int k = Integer.parseInt(br.readLine());
      ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
      for(int i = 0; i < k; i++){
         ArrayList<Integer> list = new ArrayList<>();

         int n = Integer.parseInt(br.readLine());
         String[] elements = br.readLine().split(" ");
         for(int j = 0; j < n; j++){
            list.add(Integer.parseInt(elements[j]));
         }

         lists.add(list);
      }

      ArrayList<Integer> mlist = mergeKSortedLists(lists);
      for(int val: mlist){
         System.out.print(val + " ");
      }
      System.out.println();
   }

}