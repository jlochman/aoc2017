package cz.jlochman.aoc2017.day20;

import java.util.Arrays;

class Particle {

   private int    order;
   private long[] xVec;
   private long[] vVec;
   private long[] aVec;

   public Particle(String[] in, int order) {
      this.order = order;
      xVec = toLongArr(in, 0, 2);
      vVec = toLongArr(in, 3, 5);
      aVec = toLongArr(in, 6, 8);
   }

   private long[] toLongArr(String[] in, int from, int to) {
      long[] arr = new long[to - from + 1];
      for (int i = from; i <= to; i++) {
         arr[i - from] = Long.parseLong(in[i]);
      }
      return arr;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Particle) {
         return Arrays.equals(this.getPos(), ((Particle) obj).getPos());
      } else {
         return super.equals(obj);
      }
   }

   private long[] sumArrs(long[] arr1, long[] arr2) {
      long[] arr = new long[Math.min(arr1.length, arr2.length)];
      for (int i = 0; i < arr.length; i++) {
         arr[i] = arr1[i] + arr2[i];
      }
      return arr;
   }

   public void print() {
      System.out.print("x: " + Arrays.toString(xVec));
      System.out.print(" v: " + Arrays.toString(vVec));
      System.out.print(" a: " + Arrays.toString(aVec));
      System.out.println();
   }

   public void tick() {
      vVec = sumArrs(vVec, aVec);
      xVec = sumArrs(xVec, vVec);
   }

   public long getDist() {
      long dist = 0;
      for (int i = 0; i < xVec.length; i++) {
         dist += Math.abs(xVec[i]);
      }
      return dist;
   }

   public int getOrder() {
      return order;
   }

   public long[] getPos() {
      return xVec;
   }

}
