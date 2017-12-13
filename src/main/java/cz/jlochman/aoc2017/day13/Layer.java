package cz.jlochman.aoc2017.day13;

public class Layer {

   private int depth;
   private int range;
   private int index = 0;

   private boolean inc = true;

   public Layer(int depth, int range) {
      this.depth = depth;
      this.range = range;
   }

   public Layer(Layer layer) {
      this.depth = layer.getDepth();
      this.range = layer.getRange();
      this.index = layer.getIndex();
      this.inc = layer.isInc();
   }

   public int getDepth() {
      return depth;
   }

   public void setDepth(int depth) {
      this.depth = depth;
   }

   public int getRange() {
      return range;
   }

   public void setRange(int range) {
      this.range = range;
   }

   public void tick() {
      if (inc) {
         if (index == range - 1) {
            inc = false;
            index--;
         } else {
            index++;
         }
      } else {
         if (index == 0) {
            inc = true;
            index++;
         } else {
            index--;
         }
      }
   }
   
   public void reset() {
      index = 0;
      inc = true;
   }

   public int getIndex() {
      return index;
   }

   
   public boolean isInc() {
      return inc;
   }
   
   

}
