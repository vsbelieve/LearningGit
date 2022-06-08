
import java.io.*;
import java.util.*;

public class BinaryTree {
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

  public static int size(Node node) {
    
    if( node == null){
      return 0;
    }
    
    int a = size(node.left);
    int b = size(node.right);
    
    int s = a+b+1;
    
    return s;
  }

  public static int sum(Node node) {
    if( node == null){
      return 0;
    }
    int sum1 = sum(node.left);
    int sum2 = sum(node.right);

    int sums = sum1+sum2 +node.data ;

    return sums;
  }
  // static int maxi = Integer.MIN_VALUE;
  public static int max(Node node) {
    if( node == null){
      return Integer.MIN_VALUE;
    } 
    int max1 = max(node.left);
    int max2 = max(node.right);
    
    int maxi = Math.max(node.data , Math.max(max2 , max1));

    return maxi;
  }

  // static int ht = Integer.MIN_VALUE;
  public static int height(Node node) {
    if(node == null){
      return -1; // for height interms of edges return -1 in terms of node return 1;
    }
    int ht1 = height(node.left); 
    int ht2 = height(node.right); 
    
    int ht = Math.max(ht1,ht2) + 1;

    return ht;
  }
  // code for level order traverseal in binary tree
  public static void levelOrder(Node node) {
    Queue<Node> q = new ArrayDeque<>();
     q.add(node);
     
     while ( q.size()> 0 ) { 
     int siz = q.size();
       for (int i = 0; i < siz; i++) { // all inside one loop
         Node val = q.remove(); // remove 
         System.out.print(val.data + " "); // print
 
         if(val.left != null){
           q.add(val.left);  // add child
         } 
         if (val.right != null) {
           q.add(val.right);
         }
       }
       System.out.println();        
     } 
   }
   // this finds and tells if the node is from the tree
   public static boolean find(Node node, int data){
    // base case 
    if(node == null){ // if node is null
      return false;
    }
    
    if(node.data == data){
      return true;
    }
    
    boolean lctc = find(node.left, data); //have faith this will work
    if(lctc){ // works only when left call returns true
      return true;
    }

    boolean rctc = find(node.right, data); //have faith this will work
    if(rctc){ // works only when right call returns true
      return true;
    }

    return false;
  }
  // this gives us the node to root path of the function
  static ArrayList<Integer> arr = new ArrayList<>();
  public static ArrayList<Integer> nodeToRootPath(Node node, int data){
    
    if(node == null){
      return arr;
    }
    
    if(node.data == data){
      arr.add(node.data);
      return arr;
    } 
      
      ArrayList<Integer> nrpl =  nodeToRootPath(node.left, data);
      if(nrpl.size()> 0){
        nrpl.add(node.data);
        return nrpl;
      } 

      ArrayList<Integer> nrpr =  nodeToRootPath(node.right, data);
      if(nrpr.size()> 0){
         nrpr.add(node.data);
         return nrpr; 
      }   
   
    if(nrpl.size() > 0){
      return nrpl;
    } else if(nrpr.size() > 0){
      return nrpr;
    } 

    return new ArrayList<>();

  }
  // prints all nodes at a given height lvl in preorder traversing
  public static void printKLevelsDown(Node node, int k){    
    // base case
    if(node== null ){ return;} 
    if (k == 0 ){System.out.println(node.data);} // print
    printKLevelsDown(node.left, k-1 ); // recursion
    printKLevelsDown(node.right, k-1 ); // recursion   

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

    Node root = construct(arr);

    int size = size(root);
    int sum = sum(root);
    int max = max(root);
    int ht = height(root);
    System.out.println(size);
    System.out.println(sum);
    System.out.println(max);
    System.out.println(ht);
  }

}