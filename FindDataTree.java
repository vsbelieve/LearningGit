
import java.io.*;
import java.util.*;

public class FindDataTree {
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

  public static boolean find(Node node, int data) {
    boolean flag = false;
    
    if(node.data == data){
        flag = true;
        return flag;
      }

      for (Node child : node.children) {
        flag = find(child, data);
        if(flag){
          return flag;
        }
      }

      return flag;

  }
  public static ArrayList<Integer> nodeToRootPath(Node node, int data){
    if(node.data== data){
    ArrayList<Integer> list = new ArrayList<Integer>();
     list.add(node.data);
     return list;
    } // if and if only if the data is found the list will have some data
 
    for (Node child : node.children) {
      ArrayList<Integer> ptc =(nodeToRootPath(child, data)); // receiveing the list in a new list ptc 
      
      if(ptc.size() > 0 ){
        ptc.add(node.data); // add the current node to the list if 
        return ptc; // returnig this so that recursion stops further
       }
    }
     return new ArrayList<>(); // return an empty array list
  }
  public static int lca(Node node, int d1, int d2) { //least common ancestor code
    ArrayList<Integer> p1 = nodeToRootPath(node, d1);
    ArrayList<Integer> p2 = nodeToRootPath(node, d2);

    int i = p1.size() - 1;
    int j = p2.size() - 1;

    while(i >= 0 && j >= 0 && p1.get(i) == p2.get(j)){
      i--;
      j--;
    }

    return p1.get(i + 1);
  }

  public static int distanceBetweenNodes(Node node, int d1, int d2){ // uses least common ancestor code to find distance between 2 nodes;
    ArrayList<Integer> a1 = nodeToRootPath(node, d1);
    ArrayList<Integer> a2 = nodeToRootPath(node, d2);
    int i = a1.size()-1;
    int j = a2.size()-1;
    while ( i>=0 && j>=0 && a1.get(i) == a2.get(j) ) {
      i--;
      j--;
    }
    i++;
    j++;

    return i+j;
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];
    String[] values = br.readLine().split(" ");
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(values[i]);
    }

    int data = Integer.parseInt(br.readLine());

    Node root = construct(arr);
    boolean flag = find(root, data);
    System.out.println(flag);
    // display(root);
  }

}
