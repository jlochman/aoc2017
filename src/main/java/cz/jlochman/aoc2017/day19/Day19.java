package cz.jlochman.aoc2017.day19;

import java.util.Scanner;

import cz.jlochman.aoc2017.utils.FileUtils;

public class Day19 {

   private final static int ROWS = 201;
   private final static int COLS = 201;

   public static void main(String[] args) {
      char[][] lines = new char[ROWS][COLS];
      try (Scanner in = FileUtils.getScanner("Day19/input.txt")) {
         int lineNumber = 0;
         while (in.hasNextLine()) {
            String line = in.nextLine();
            lines[lineNumber++] = line.toCharArray();
         }

         int currCol = 0;
         int currRow = 0;
         Direction direction = Direction.DOWN;
         for (int col = 0; col < COLS; col++) {
            if (lines[0][col] == '|') {
               currCol = col;
               break;
            }
         }

         String path = "";
         int steps = 1;
         boolean cont = true;
         while (cont) {
            switch (direction) {
            case UP:
               for (int row = currRow - 1; row >= 0; row--) {
                  if (Character.isLetter(lines[row][currCol])) {
                     path += lines[row][currCol];
                  }
                  if (lines[row][currCol] == ' ') {
                     cont = false;
                     break;
                  }
                  steps++;
                  if (lines[row][currCol] == '+') {
                     currRow = row;
                     direction =
                           (lines[row][currCol - 1] != ' ') ? Direction.LEFT
                                                            : Direction.RIGHT;
                     break;
                  }
               }
               break;
            case DOWN:
               for (int row = currRow + 1; row < ROWS; row++) {
                  if (Character.isLetter(lines[row][currCol])) {
                     path += lines[row][currCol];
                  }
                  if (lines[row][currCol] == ' ') {
                     cont = false;
                     break;
                  }
                  steps++;
                  if (lines[row][currCol] == '+') {
                     currRow = row;
                     direction =
                           (lines[row][currCol - 1] != ' ') ? Direction.LEFT
                                                            : Direction.RIGHT;
                     break;
                  }
               }
               break;
            case LEFT:
               for (int col = currCol - 1; col >= 0; col--) {
                  if (Character.isLetter(lines[currRow][col])) {
                     path += lines[currRow][col];
                  }
                  if (lines[currRow][col] == ' ') {
                     cont = false;
                     break;
                  }
                  steps++;
                  if (lines[currRow][col] == '+') {
                     currCol = col;
                     direction =
                           (lines[currRow - 1][col] != ' ') ? Direction.UP
                                                            : Direction.DOWN;
                     break;
                  }
               }
               break;
            case RIGHT:
               for (int col = currCol + 1; col < COLS; col++) {
                  if (Character.isLetter(lines[currRow][col])) {
                     path += lines[currRow][col];
                  }
                  if (lines[currRow][col] == ' ') {
                     cont = false;
                     break;
                  }
                  steps++;
                  if (lines[currRow][col] == '+') {
                     currCol = col;
                     direction =
                           (lines[currRow - 1][col] != ' ') ? Direction.UP
                                                            : Direction.DOWN;
                     break;
                  }
               }
               break;
            }
         }

         System.out.println("A: " + path);
         System.out.println("B: " + steps);
      }
   }

   private static enum Direction {
      UP, DOWN, LEFT, RIGHT;
   }

}
