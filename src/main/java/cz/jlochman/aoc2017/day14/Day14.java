package cz.jlochman.aoc2017.day14;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import cz.jlochman.aoc2017.day10.Day10b;

public class Day14 {

   private final static String INPUT     = "ffayrhll";
   private final static int    GRID_SIZE = 128;

   public static void main(String[] args) throws UnsupportedEncodingException {
      int[][] groups = new int[GRID_SIZE][GRID_SIZE];
      int mockGroupCounter = 0;
      for (int row = 0; row < GRID_SIZE; row++) {
         String binRow = hexToBinary(Day10b.resolve(INPUT + "-" + row));
         for (int col = 0; col < binRow.length(); col++) {
            if (binRow.charAt(col) == '1') {
               groups[row][col] = mockGroupCounter++;
            }
         }
      }
      System.out.println("A: " + mockGroupCounter);

      for (int row = 0; row < GRID_SIZE; row++) {
         for (int col = 0; col < GRID_SIZE; col++) {
            if (groups[row][col] == 0) {
               continue;
            }
            if (col > 0 && groups[row][col - 1] > 0) {
               mergeGroups(groups[row][col - 1], groups[row][col], groups);
            }
            if (row > 0 && groups[row - 1][col] > 0) {
               mergeGroups(groups[row - 1][col], groups[row][col], groups);
            }
         }
      }

      Set<Integer> ints = new HashSet<>();
      for (int row = 0; row < GRID_SIZE; row++) {
         for (int col = 0; col < GRID_SIZE; col++) {
            if (groups[row][col] != 0) {
               ints.add(groups[row][col]);
            }
         }
      }
      System.out.println("B: " + ints.size());
   }

   private static void mergeGroups(int low, int high, int[][] groups) {
      for (int row = 0; row < GRID_SIZE; row++) {
         for (int col = 0; col < GRID_SIZE; col++) {
            if (groups[row][col] == high) {
               groups[row][col] = low;
            }
         }
      }
   }

   private static String hexToBinary(String hex) {
      String bin = new BigInteger(hex, 16).toString(2);
      while (bin.length() != hex.length() * 4) {
         bin = "0" + bin;
      }
      return bin;
   }

}
