import java.io.*;
import java.util.*;

public class PQusingHeap {

  public static class PriorityQueue {
    ArrayList<Integer> data;

    public PriorityQueue() {
      data = new ArrayList<>();
    }

    public void add(int val) {
      data.add(val);
      upHeapify(data.size()-1);
    }
    // unheapify function
    private void upHeapify(int i){
      if(i == 0){
        return;
      }
      int pi = (i -1)/2;
      if(data.get(i) < data.get(pi)){ // sp priority is more
        swap(i,pi); // swap their values to meet HOP - heap Order Prop.
        upHeapify(pi); // call again from new parent's position
      }
    }
    public int remove() {
      if(this.size()==0){
        System.out.println("Underflow");
        return -1;
      }
      swap(0,data.size()-1);
      int val = data.remove(data.size()-1);
      downHeapify(0);
      return val;
    }

    public int peek() {
      if(this.size()==0){
        System.out.println("Underflow");
        return -1;
      } 
      return data.get(0);
    }
    public int size() {
      return data.size();
    }
  
  //swap function
  private void swap(int i,int j){
    int ith = data.get(i);
    int jth = data.get(j);
    data.set(i,jth);
    data.set(j,ith);
  }
  
  private void downHeapify(int i){
    
    int mini = i; // take this as min

    int li = 2*i + 1;
    if(li < data.size() && data.get(li) < data.get(mini)){ // sp priority is more
      mini = li;
    }

    int ri = (2*i +2);
    if(ri < data.size() && data.get(ri) < data.get(mini)){ // sp priority is more
      mini = ri; 
    }

    if(mini != i){
      swap(i,mini);
      downHeapify(mini); // call again from new child's position
    }
    
  }

  }

  

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PriorityQueue qu = new PriorityQueue();

    String str = br.readLine();
    while (str.equals("quit") == false) {
      if (str.startsWith("add")) {
        int val = Integer.parseInt(str.split(" ")[1]);
        qu.add(val);
      } else if (str.startsWith("remove")) {
        int val = qu.remove();
        if (val != -1) {
          System.out.println(val);
        }
      } else if (str.startsWith("peek")) {
        int val = qu.peek();
        if (val != -1) {
          System.out.println(val);
        }
      } else if (str.startsWith("size")) {
        System.out.println(qu.size());
      }
      str = br.readLine();
    }
  }
}