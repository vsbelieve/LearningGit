import java.util.*;
import java.io.*;

public class binarysearch {
    
    public static void main(String[] args) {    
    int[] arr = {10,20,30,40,50,60,70,80,90,100};
    int data = 70;
    binary(arr, data);
    
    }

    public static void binary(int [] arr , int data) {
        int li = 0;
        int ri = arr.length-1;
        
        while(li<=ri){
        int mid = (li+ri)/2;
            if(arr[mid] == data){
                return;
            }
            else if(arr[mid]<data){
                li= mid+1;
            }
            else{
                ri = mid-1;
            }
        }
        System.out.println(-1);
    }
    
    
    public static void firstandlastidx(int [] arr, int data){

    int li = 0;
    int ri = arr.length-1;
    int fti = -1;// first index of duplicate
    while(li<=ri){
    int mid = (li+ri)/2;
        if(arr[mid] == data){
            fti = mid;
            ri = mid-1;
        }
        else if(arr[mid]<data){
            li= mid+1;
        }
        else{
            ri = mid-1;
        }
    }
    System.out.println(fti);
    
    li = 0;
    ri = arr.length-1;
    int lfi = -1; // last index of duplicate
    while(li<=ri){
    int mid = (li+ri)/2;
        if(arr[mid] == data){
            lfi = mid;
            li = mid+1;
        }
        else if(arr[mid]<data){
            li= mid+1;
        }
        else{
            ri = mid-1;
        }
    }
    System.out.println(lfi); }
}

