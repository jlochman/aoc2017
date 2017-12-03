package cz.jlochman.aoc2017.day03;

import java.util.HashSet;
import java.util.Set;

public class Day03b {

   private final static int INPUT     = 347991;
   private static Direction direction;

   public static void main(String[] args) {
      Set<Square> squares = new HashSet<>();

      Square firstSuqare = new Square(0, 0);
      firstSuqare.setValue(1);
      squares.add(firstSuqare);
      direction = Direction.RIGHT;

      Square prevSquare = firstSuqare;
      int sum = 0;
      while (sum <= INPUT) {
         Square nextSquare = getNextSquare(prevSquare);
         sum = squares.stream()
                      .filter(square -> square.isNeighbor(nextSquare))
                      .mapToInt(square -> square.getValue())
                      .sum();
         nextSquare.setValue(sum);
         squares.add(nextSquare);
         prevSquare = nextSquare;
      }
      System.out.println(sum);
   }

   private static Square getNextSquare(Square prevSquare) {
      int x = prevSquare.getX();
      int y = prevSquare.getY();
      switch (direction) {
      case UP:
         if (y < x) {
            return new Square(x, y + 1);
         } else {
            direction = Direction.LEFT;
            break;
         }
      case LEFT:
         if (-x < y) {
            return new Square(x - 1, y);
         } else {
            direction = Direction.DOWN;
            break;
         }
      case DOWN:
         if (y > x) {
            return new Square(x, y - 1);
         } else {
            direction = Direction.RIGHT;
            break;
         }
      case RIGHT:
         if (-x >= y) {
            return new Square(x + 1, y);
         } else {
            direction = Direction.UP;
            break;
         }
      }
      return getNextSquare(prevSquare);
   }

   private static enum Direction {
      UP, LEFT, DOWN, RIGHT;

   }

   private static class Square {

      private int x;
      private int y;
      private int value;

      public Square(int x, int y) {
         this.x = x;
         this.y = y;
      }

      public boolean isNeighbor(Square square) {
         return Math.abs(square.getX() - x) <= 1
                && Math.abs(square.getY() - y) <= 1;
      }

      public int getX() {
         return x;
      }

      public int getY() {
         return y;
      }

      public int getValue() {
         return value;
      }

      public void setValue(int value) {
         this.value = value;
      }

      @Override
      public int hashCode() {
         return 1000 * x + y;
      }

   }

}
