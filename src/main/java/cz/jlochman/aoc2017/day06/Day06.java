package cz.jlochman.aoc2017.day06;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Day06 {

   private static int[] inputA =
         new int[] { 14, 0, 15, 12, 11, 11, 3, 5, 1, 6, 8, 4, 9, 1, 8, 4 };
   private static int[] inputB =
         new int[] { 14, 13, 12, 11, 9, 8, 8, 6, 6, 4, 4, 3, 1, 1, 0, 12 };

   public static void main(String[] args) {
      System.out.println(resolve(inputA));
      System.out.println(resolve(inputB));
   }

   private static int resolve(int[] initArray) {
      Set<int[]> arrays = new HashSet<>();
      arrays.add(initArray);

      int[] arr = getNextArray(initArray);
      int steps = 1;
      while (!contains(arrays, arr)) {
         arrays.add(arr);
         arr = getNextArray(arr);
         steps++;
      }
      return steps;
   }

   private static int[] getNextArray(int[] array) {
      int max = 0;
      int pos = 0;
      for (int i = 0; i < array.length; i++) {
         if (array[i] > max) {
            max = array[i];
            pos = i;
         }
      }

      int[] result = Arrays.copyOf(array, array.length);
      result[pos] = 0;
      for (int i = 0; i < max; i++) {
         result[(pos + i + 1) % (array.length)] += 1;
      }

      return result;
   }

   private static boolean contains(Set<int[]> arrays, int[] array) {
      for (int[] ar : arrays) {
         if (Arrays.equals(ar, array)) {
            return true;
         }
      }
      return false;
   }

}
