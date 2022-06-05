
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
      ArrayList<Integer> ptc =(nodeToRootPath(child, data)); // equating list new ptc to the returned list
      
      if(ptc.size() > 0 ){
        ptc.add(node.data);
        return ptc;
       }
    }
     return new ArrayList<>(); // return an empty array list
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
