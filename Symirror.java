
import java.io.*;
import java.util.*;

public class Symirror {
  private static class Node {
    int data;
    ArrayList<Node> children = new ArrayList<>();
  }

  public static void display(Node node) {
    String str = node.data + " -> ";
    for (Node child : node.children) {
      str += child.data + ", ";
    }
    str += ".";
    System.out.println(str);

    for (Node child : node.children) {
      display(child);
    }
  }

  public static Node construct(int[] arr) {
    Node root = null;

    Stack<Node> st = new Stack<>();
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == -1) {
        st.pop();
      } else {
        Node t = new Node();
        t.data = arr[i];

        if (st.size() > 0) {
          st.peek().children.add(t);
        } else {
          root = t;
        }

        st.push(t);
      }
    }

    return root;
  }

  public static int size(Node node) {
    int s = 0;

    for (Node child : node.children) {
      s += size(child);
    }
    s += 1;

    return s;
  }

  public static int max(Node node) {
    int m = Integer.MIN_VALUE;

    for (Node child : node.children) {
      int cm = max(child);
      m = Math.max(m, cm);
    }
    m = Math.max(m, node.data);

    return m;
  }

  public static int height(Node node) {
    int h = -1;

    for (Node child : node.children) {
      int ch = height(child);
      h = Math.max(h, ch);
    }
    h += 1;

    return h;
  }

  public static boolean IsSymmetric(Node node) {
    return areMirror(node, node);
    
  }

  public static boolean areMirror(Node n1, Node n2) {
    if(n1.children.size() != n2.children.size()){
      return false;
    }
    int sn1 = n1.children.size()-1;
    int sn2 = n2.children.size()-1;
    int i = 0;
    int j = sn2;
    while(i <= sn1 && j>=0 ){
      Node ls = n1.children.get(i);
      Node rs = n2.children.get(j);

      if(areMirror(ls,rs) == false){
        return false;
      }
      i++;
      j--;
    }
  return true;
    
  }
  static Node Predecessor;
  static Node Successor;
  static int state;
  // finds the successor and predecessor
  public static void PredecessorandSuccessor(Node node , int data){
      if(state == 0){
        if(node.data == data){
          state = 1; // change state so that predecessor data is saved.
        } else{
          Predecessor = node;
      }
    }
     else if(state == 1){
        Successor = node;
        state = 2;
      }

    for(Node child : node.children){
       PredecessorandSuccessor(child, data);
    }
  }

  static int ceil;
  static int floor;
  public static void ceilAndFloor(Node node, int data) {
    if(node.data > data && node.data != data){
      ceil = Math.min(ceil, node.data);
    }
    else if( node.data < data && node.data != data){
      floor = Math.max(floor, node.data);
    }

    for(Node child : node.children){
      ceilAndFloor(child, data);
    }
  }
  
  static int msn;
  static int ms;
  public static int SubtreeSum(Node node) { // returns sum and node of subtreesum
    int sum = 0;
    
    for (Node child : node.children) {
      int csum = SubtreeSum(child);
      sum +=  csum;
 
    }
    sum += node.data; // the node data also gets added by this. 
    
    if(sum> ms){
      msn = node.data;
      ms = sum;
    }

    return sum;
    
  }
  
  static int dia = Integer.MIN_VALUE;
  public static int diameter(Node node) {
    int h =-1 ;

    int sh = -1;
    for (Node child : node.children) {
       int ch = diameter(child);

      if(ch> h){
        sh = h;
        h = ch;
      } else if(ch> sh){
          sh = ch;
      }

    }

    int res = h +sh+ 2;
    if( res > dia) dia = res;
    
    h += 1;
    return h;
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];
    String[] values = br.readLine().split(" ");
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(values[i]);
    }

    Node root = construct(arr);
    boolean sym = IsSymmetric(root);
    System.out.println(sym);
    // display(root);
  }

}