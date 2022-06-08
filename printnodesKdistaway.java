import java.io.*;
import java.util.*;

public class printnodesKdistaway {
  public static class Node {
    int data;
    Node left;
    Node right;

    Node(int data, Node left, Node right) {
      this.data = data;
      this.left = left;
      this.right = right;
    }
  }

  public static class Pair {
    Node node;
    int state;

    Pair(Node node, int state) {
      this.node = node;
      this.state = state;
    }
  }

  public static Node construct(Integer[] arr) {
    Node root = new Node(arr[0], null, null);
    Pair rtp = new Pair(root, 1);

    Stack<Pair> st = new Stack<>();
    st.push(rtp);

    int idx = 0;
    while (st.size() > 0) {
      Pair top = st.peek();
      if (top.state == 1) {
        idx++;
        if (arr[idx] != null) {
          top.node.left = new Node(arr[idx], null, null);
          Pair lp = new Pair(top.node.left, 1);
          st.push(lp);
        } else {
          top.node.left = null;
        }

        top.state++;
      } else if (top.state == 2) {
        idx++;
        if (arr[idx] != null) {
          top.node.right = new Node(arr[idx], null, null);
          Pair rp = new Pair(top.node.right, 1);
          st.push(rp);
        } else {
          top.node.right = null;
        }

        top.state++;
      } else {
        st.pop();
      }
    }

    return root;
  }

  public static void display(Node node) {
    if (node == null) {
      return;
    }

    String str = "";
    str += node.left == null ? "." : node.left.data + "";
    str += " <- " + node.data + " -> ";
    str += node.right == null ? "." : node.right.data + "";
    System.out.println(str);

    display(node.left);
    display(node.right);
  }
  
  static ArrayList<Node> arr = new ArrayList<>();
  public static ArrayList<Node> nodetorootpath(Node node, int data){
    if(node == null){
      return arr;
    }
    if(node.data == data){
      arr.add(node);
      return arr;
    }
    
    ArrayList<Node> nrpl =  nodetorootpath(node.left, data);
    if(nrpl.size()>0 ){
      nrpl.add(node); // current curr node
      return nrpl;
    }
    
    ArrayList<Node> nrpr =  nodetorootpath(node.right, data);
    if(nrpr.size()>0 ){
      nrpr.add(node); // current curr node
      return nrpr;
    }

    arr = nrpl.size() > nrpr.size() ? nrpl: nrpl;
    return arr;
    
  }
  public static void printKLevelsDown(Node node, int k, Node block){    
    // base case
    if(node== null || k<0 || node == block){ return;} 
    if ( k == 0 ){System.out.println(node.data);} // print
    printKLevelsDown(node.left, k-1 , block); // recursion
    printKLevelsDown(node.right, k-1 , block ); // recursion   
  }
  
  static ArrayList<Integer> res = new ArrayList<>();
  public static void printKNodesFar(Node node, int data, int k) {
    ArrayList<Node> nodes = nodetorootpath(node, data);
    for(int i =0; i < nodes.size() && i<=k ; i++) {
    printKLevelsDown( nodes.get(i) , k- i, i == 0 ? null : nodes.get(i-1));

    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    Integer[] arr = new Integer[n];
    String[] values = br.readLine().split(" ");
    for (int i = 0; i < n; i++) {
      if (values[i].equals("n") == false) {
        arr[i] = Integer.parseInt(values[i]);
      } else {
        arr[i] = null;
      }
    }

    int data = Integer.parseInt(br.readLine());
    int k = Integer.parseInt(br.readLine());

    Node root = construct(arr);
    printKNodesFar(root, data, k);
  }

}