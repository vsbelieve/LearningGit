import java.io.*;
import java.util.*;

public class clonebinarytree {
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

  public static Node createLeftCloneTree(Node node){
      if(node == null){
        return null; // return null for node 
      }
      // have faith that node left and right can clone themselves
      // so now we only need to make clone of root and attach left right -  small calc
      
      Node lcc = createLeftCloneTree(node.left);
      Node rcc = createLeftCloneTree(node.right);

      node.right = rcc; // root's right is root clone child
      Node temp = new Node(node.data,node.left,null); // helps us in connecting the clone node to the left node in b/w root and left we need a root's clone
      node.left = temp;
      return node;
  }
  // this code undoes the effect of the above code it transback from leftcloned tree
  public static Node transBackFromLeftClonedTree(Node node){
    if(node == null ){
      return null;
    }
    Node lct = transBackFromLeftClonedTree(node.left.left);
    Node rct = transBackFromLeftClonedTree(node.right);
    
    node.left = lct;
    node.right = rct;
    
    return node;
    
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
    root = createLeftCloneTree(root);
    display(root);
  }

}