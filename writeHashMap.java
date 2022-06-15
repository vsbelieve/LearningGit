
import java.io.*;
import java.util.*;

public class writeHashMap {

  public static class HashMap<K, V> {
    private class HMNode {
      K key;
      V value;

      HMNode(K key, V value) {
        this.key = key;
        this.value = value;
      }
    }

    private int size; // n
    private LinkedList<HMNode>[] buckets; // N = buckets.length

    public HashMap() {
      initbuckets(4);
      size = 0;
    }

    private void initbuckets(int N) {
      buckets = new LinkedList[N];
      for (int bi = 0; bi < buckets.length; bi++) {
        buckets[bi] = new LinkedList<>();
      }
    }

    public void put(K key, V value) throws Exception {
      
      int bi = hashfn(key); // this gives the bucket idx -bi
      int di = getIdxWithinBucket(key,bi); // gives the idx in the linkedlist @bi of bucket 
      
      if (di != -1) {
        HMNode node = buckets[bi].get(di);
        node.value = value; 
      } else {
        // insert case
        HMNode node = new HMNode(key,value);
        buckets[bi].add(node); // node gets added to the linkedlist
        size++;
      }

        double lambda = size * 1.0/ buckets.length;
        if(lambda >2.0){
          rehash();
        }
    }

    private void rehash() throws Exception{
      LinkedList<HMNode> [] oba = buckets;
      initbuckets(oba.length*2); // new linkedlist of HMNode created
      size = 0;

      for (int i = 0; i < oba.length; i++) {
        for(HMNode node: oba[i]){
          put(node.key,node.value); // here we have used put to fill the new Hashmap
        }
      }
      
    }
    private int hashfn(K key){
      int hc = key.hashCode(); // retruns a unique hashcode for a given key already implemented in java
      return Math.abs(hc) % buckets.length; // returns idx based on hashcode within the range of buckets length
    }

    private int getIdxWithinBucket(K key,int bi){
        int idx =0;
         for (HMNode node : buckets[bi]) {
           if(node.key.equals(key)){
             return idx;
           }
           idx++;
         }
      return -1;
    }
    
    public V get(K key) throws Exception {
      int bi = hashfn(key); // this gives the bucket idx -bi
      int di = getIdxWithinBucket(key,bi); // gives the idx in the linkedlist @bi of bucket 

      if (di != -1) {
        HMNode node = buckets[bi].get(di);
        return node.value; 
      } else {
        return null;
      }
    }

    public boolean containsKey(K key) {
      int bi = hashfn(key); // this gives the bucket idx -bi
      int di = getIdxWithinBucket(key,bi); // gives the idx in the linkedlist @bi of bucket 
      
      if (di != -1) {
        return true;
      } else {
        return false;
      }
    }

    public V remove(K key) throws Exception {
      int bi = hashfn(key); // this gives the bucket idx -bi
      int di = getIdxWithinBucket(key,bi); // gives the idx in the linkedlist @bi of bucket 

      if (di != -1) {
        HMNode node = buckets[bi].remove(di);
        size--;
        return node.value;
      } else {
        return null;
      }
    }

    public ArrayList<K> keyset() throws Exception {
      ArrayList<K> keys = new ArrayList<K>();
      for (int i = 0; i < buckets.length; i++) {
        for (HMNode node : buckets[i]){
          //add all keys in list
          keys.add(node.key);

        }
      }
      return keys;
    }

    public int size() {
      return size;
    }

   public void display() {
      System.out.println("Display Begins");
      for (int bi = 0; bi < buckets.length; bi++) {
        System.out.print("Bucket" + bi + " ");
        for (HMNode node : buckets[bi]) {
          System.out.print( node.key + "@" + node.value + " ");
        }
        System.out.println(".");
      }
      System.out.println("Display Ends");
  }
}

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    HashMap<String, Integer> map = new HashMap();

    String str = br.readLine();
    while (str.equals("quit") == false) {
      if (str.startsWith("put")) {
        String[] parts = str.split(" ");
        String key = parts[1];
        Integer val = Integer.parseInt(parts[2]);
        map.put(key, val);
      } else if (str.startsWith("get")) {
        String[] parts = str.split(" ");
        String key = parts[1];
        System.out.println(map.get(key));
      } else if (str.startsWith("containsKey")) {
        String[] parts = str.split(" ");
        String key = parts[1];
        System.out.println(map.containsKey(key));
      } else if (str.startsWith("remove")) {
        String[] parts = str.split(" ");
        String key = parts[1];
        System.out.println(map.remove(key));
      } else if (str.startsWith("size")) {
        System.out.println(map.size());
      } else if (str.startsWith("keyset")) {
        System.out.println(map.keyset());
      } else if (str.startsWith("display")) {
        map.display();
      }
      str = br.readLine();
    }
  }
}