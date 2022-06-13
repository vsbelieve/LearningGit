
import java.io.*;
import java.util.*;

public class LargestBSTsubtree {
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

  public static int height(Node node) {
    if (node == null) {
      return -1;
    }

    int lh = height(node.left);
    int rh = height(node.right);

    int th = Math.max(lh, rh) + 1;
    return th;
  }
  public static class BSTPair{
    boolean isBST;
    int min;
    int max;
    Node root; // 2 new variables were introduced
    int size;
  }
  public static BSTPair isBST(Node node){ // this is a function that can be called only by using class
    //base case
    if(node == null){
      BSTPair bp = new BSTPair(); // make a new BST pair
      bp.isBST = true;
      bp.min = Integer.MAX_VALUE;
      bp.max = Integer.MIN_VALUE;
      bp.root = null;
      bp.size = 0;
      return bp;
    }  

    BSTPair lp = isBST(node.left);
    BSTPair rp = isBST(node.right);

    BSTPair bs = new BSTPair(); // making a new object of BSTPair class which can be accessed by isBST function
    bs.isBST = lp.isBST && rp.isBST && (lp.max <= node.data && rp.min >= node.data);
    bs.min = Math.min(node.data,Math.min(lp.min,rp.min));
    bs.max = Math.max(node.data,Math.max(lp.max,rp.max)); // the reason why min of rp and max of lp were needed to calc min/max of node which might have a parent to give min/max to.

    if (bs.isBST) {
      bs.root = node;
      bs.size = lp.size + rp.size + 1 ;
    } 
    else if(lp.size > rp.size){ // lp is bigger BST then main root becomes lp.root
      bs.root = lp.root;
      bs.size = lp.size; 
    }
    else {
      bs.root = rp.root;
      bs.size = rp.size;
    }
    return bs;
    
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
    
    BSTPair bp = isBST(root); // calling function using class BSTPair
    System.out.println(bp.root.data + "@" + bp.size);
    
  }

}