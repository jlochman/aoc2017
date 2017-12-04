package cz.jlochman.aoc2017.day03;

public class Day03a {

   private final static int INPUT = 349975;
   private static Direction direction;

   public static void main(String[] args) {
      mathSolution();
      itSolution();
   }

   private static void mathSolution() {
      int sideSize = 1;
      while (Math.pow(sideSize, 2) < INPUT) {
         sideSize += 2;
      }
      int outerMin = (sideSize - 2) * (sideSize - 2) + 1;

      int counter = outerMin;
      int side = 0;
      while (counter <= INPUT) {
         counter += sideSize - 1;
         side++;
      }
      int outerSideMin = outerMin + (side - 1) * (sideSize - 1);
      int outerSideMiddle = outerSideMin + (sideSize - 1) / 2 - 1;

      System.out.println(Math.abs(INPUT - outerSideMiddle)
                         + (sideSize - 1) / 2);
   }

   private static void itSolution() {
      Square square = new Square(0, 0);
      direction = Direction.RIGHT;

      int counter = 1;
      while (counter < INPUT) {
         counter++;
         square = getNextSquare(square);
      }
      System.out.println(Math.abs(square.getX()) + Math.abs(square.getY()));
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

      public Square(int x, int y) {
         this.x = x;
         this.y = y;
      }

      public int getX() {
         return x;
      }

      public int getY() {
         return y;
      }

      @Override
      public int hashCode() {
         return 1000 * x + y;
      }

   }

}
