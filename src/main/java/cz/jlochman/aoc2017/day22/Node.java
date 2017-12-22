package cz.jlochman.aoc2017.day22;

class Node {

   private int x;
   private int y;

   public Node(int x, int y) {
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
      return 10000 * x + y;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Node) {
         Node other = (Node) obj;
         return this.getX() == other.getX() && this.getY() == other.getY();
      } else {
         return super.equals(obj);
      }
   }

}
