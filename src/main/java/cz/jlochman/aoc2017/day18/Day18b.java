package cz.jlochman.aoc2017.day18;

public class Day18b {

   public static Program program0;
   public static Program program1;

   public static void main(String[] args) throws InterruptedException {
      program0 = new Program(0L);
      program1 = new Program(1L);
      program0.setOtherProgram(program1);
      program1.setOtherProgram(program0);

      Thread t0 = new Thread(new MyProcess(program0));
      Thread t1 = new Thread(new MyProcess(program1));
      t0.start();
      t1.start();

      int lastResult = 0;
      while (true) {
         Thread.sleep(100L);
         if (program1.getSndValueCount() != lastResult) {
            lastResult = program1.getSndValueCount();
         } else {
            System.out.println(program1.getSndValueCount());
            System.exit(0);
         }
      }

   }

   private static class MyProcess implements Runnable {

      private Program program;

      public MyProcess(Program program) {
         this.program = program;
      }

      @Override
      public void run() {
         for (int i = 0; i < 1e9; i++) {
            program.step();
         }
      }

   }

}
