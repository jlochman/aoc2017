package cz.jlochman.aoc2017.day01;

import java.util.Scanner;

import cz.jlochman.aoc2017.utils.FileUtils;

public class Day01b {

   public static void main(String[] args) {
      try (Scanner in = FileUtils.getScanner("Day01/input.txt")) {
         String line = in.next();
         int length = line.length();

         int[] intArr = new int[length];
         for (int i = 0; i < length; i++) {
            intArr[i] = Character.getNumericValue(line.charAt(i));
         }

         int result = 0;
         for (int i = 0; i < length; i++) {
            if (intArr[i] == intArr[(i + length / 2) % length]) {
               result += intArr[i];
            }
         }
         System.out.println(result);
      }
   }

}
