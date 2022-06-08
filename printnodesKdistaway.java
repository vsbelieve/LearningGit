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
  // print all values at a level k
  public static void printKLevelsDown(Node node, int k, Node block){    
    // base case
    if(node== null || k<0 || node == block){ return;} 
    if ( k == 0 ){System.out.println(node.data);} // print
    printKLevelsDown(node.left, k-1 , block); // recursion
    printKLevelsDown(node.right, k-1 , block ); // recursion   
    }
//   this function prints node k far away from any given node
  static ArrayList<Integer> res = new ArrayList<>();
  public static void printKNodesFar(Node node, int data, int k) {
    ArrayList<Node> nodes = nodetorootpath(node, data);
    
    for(int i =0; i < nodes.size() && i<=k ; i++) {
    printKLevelsDown( nodes.get(i) , k- i, i == 0 ? null : nodes.get(i-1));
    }
  }
//  this function can print all paths of a tree from node to leaf with their sum as well within some range or not
    public static void pathToLeafFromRoot(Node node, String path, int sum, int lo, int hi){
    // base case for default node
    if(node == null){ return;} // got error here, careful

    if(node.left == null && node.right == null){ // when both the child are null we will reach here for every node
      sum += node.data; // increase the sum only as path is decided w/ lo & hi
      if(sum >= lo && sum <= hi){
        System.out.println(path + node.data +""); }
      return;
    }      
    pathToLeafFromRoot(node.left, path + node.data + " ", sum+node.data, lo, hi);
    pathToLeafFromRoot(node.right, path + node.data + " ", sum+node.data, lo, hi);
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