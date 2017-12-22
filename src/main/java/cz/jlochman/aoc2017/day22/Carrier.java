package cz.jlochman.aoc2017.day22;

class Carrier {

   private Node      node      = new Node(0, 0);
   private Direction direction = Direction.UP;

   public void turnLeft() {
      direction = Direction.values()[(direction.ordinal() + 3) % 4];
   }

   public void turnRight() {
      direction = Direction.values()[(direction.ordinal() + 1) % 4];
   }

   public void reverseDirection() {
      direction = Direction.values()[(direction.ordinal() + 2) % 4];
   }

   public void move() {
      switch (direction) {
      case UP:
         node = new Node(node.getX() - 1, node.getY());
         break;
      case DOWN:
         node = new Node(node.getX() + 1, node.getY());
         break;
      case LEFT:
         node = new Node(node.getX(), node.getY() - 1);
         break;
      case RIGHT:
         node = new Node(node.getX(), node.getY() + 1);
         break;
      }
   }

   public Node getNode() {
      return node;
   }

   private enum Direction {
      UP, RIGHT, DOWN, LEFT;
   }

}
