package practice.apple.phonescreen1;

/*
 * Click `Run` to execute the snippet below!
 */

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

public class Solution {  

  int[][] directions = {
    {-1, -2}, {-2, -1} // ...
  };
  
  public double calculateInBoardPossibility(int x, int y, int n) {
    // board 8*8, start from 0,0
    if (x < 0 || x >= 8 || y < 0 || y >= 8 || n < 0 ) {
      return -1; // invalid
    }

    if (n == 0) {
      return 1;
    }

    
    // key: "x,y", count in that layer
    Map<String, Integer> map = new HashMap<>();
    Map<String, Integer> newMap = new HashMap<>();

    // LinkedList<int[]> q = new LinkedList<int[]>();
    map.put(x + "," + y, 1);

    for (int i = 0; i < n; ++i) {
      newMap.clear();

      for (Map.Entry<String, Integer> entry : map.entrySet()) {
        int count = entry.getValue();
        String[] node = entry.getKey().split(",");

        for (int[] dir : directions) {
          int newX = Integer.parseInt(node[0]) + dir[0];
          int newY = Integer.parseInt(node[1]) + dir[1];

          if (checkOnBoard(newX, newY)) {
            String newKey = newX + "," + newY;
            int newCount = newMap.getOrDefault(newKey, 0);
            newCount += count;
            newMap.put(newKey, newCount);
          } 
      }

      Map<String, Integer> tmp = map;
      map = newMap;
      newMap = map;

      // LinkedList<int[]> newQ = new LinkedList<int[]>();
      // while (!map.isEmpty() ) {
      //   int[] node = q.poll();

      //   for (int[] dir : directions) {
      //     int newX = node[0] + dir[0];
      //     int newY = node[1] + dir[1];

      //     if (checkOnBoard(newX, newY)) {
      //       newQ.add(new int[] {newX, newY});
      //     }  
      //   }
      // q = newQ;
    }
    
    int sum = 0;
    for (int count : map.values()) {
      sum += count;
    }
    return (double) sum / (double) Math.pow(8, n);
  } 

  private boolean checkOnBoard(int x, int y) {
    if (x < 0 || x >= 8 || y < 0 || y >= 8) {
      return false;
    }
    return true;
  }

  public static void main(String[] args) {
    ArrayList<String> strings = new ArrayList<String>();
    strings.add("Hello, World!");
    strings.add("Welcome to CoderPad.");
    strings.add("This pad is running Java " + Runtime.version().feature());

    for (String string : strings) {
      System.out.println(string);
    }
  }
}


// Your previous Plain Text content is preserved below:

// input: x, y, N