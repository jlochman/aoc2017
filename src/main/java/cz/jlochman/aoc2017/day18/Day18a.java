package cz.jlochman.aoc2017.day18;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import cz.jlochman.aoc2017.utils.FileUtils;

public class Day18a {

   public static Map<Character, Long> registers      = new HashMap<>();
   public static long                 sound          = 0;
   public static int                  workerPosition = 0;
   public static boolean              rcvCalled      = false;

   public static void main(String[] args) {
      registers.put('a', 0L);
      registers.put('b', 0L);
      registers.put('f', 0L);
      registers.put('i', 0L);
      registers.put('p', 0L);
      try (Scanner in = FileUtils.getScanner("Day18/input.txt")) {
         List<Worker> workers = new ArrayList<>();
         while (in.hasNextLine()) {
            Scanner line = new Scanner(in.nextLine());
            switch (line.next()) {
            case "snd":
               workers.add(new SndWorker(line.next()));
               break;
            case "set":
               workers.add(new SetWorker(line.next(), line.next()));
               break;
            case "add":
               workers.add(new AddWorker(line.next(), line.next()));
               break;
            case "mul":
               workers.add(new MulWorker(line.next(), line.next()));
               break;
            case "mod":
               workers.add(new ModWorker(line.next(), line.next()));
               break;
            case "rcv":
               workers.add(new RcvWorker(line.next()));
               break;
            case "jgz":
               workers.add(new JgzWorker(line.next(), line.next()));
               break;
            default:
               System.out.println("not handled: " + line.toString());
               break;
            }
            line.close();
         }

         while (!rcvCalled) {
            workers.get(workerPosition)
                   .work();
         }
         System.out.println(sound);

      }

   }

   private static interface Worker {

      void work();

      public abstract class SingleValWorker implements Worker {

         protected Value val;

         public SingleValWorker(String s) {
            this.val = new Value(s);
         }
      }

      public abstract class DoubleValWorker implements Worker {

         protected Value val1;
         protected Value val2;

         public DoubleValWorker(String s1, String s2) {
            this.val1 = new Value(s1);
            this.val2 = new Value(s2);
         }
      }

   }

   private static class Value {

      private long val;
      private char reg;

      public Value(String s) {
         try {
            val = Long.parseLong(s);
         } catch (Exception e) {
            reg = s.charAt(0);
         }
      }

      public long getValue() {
         if (reg == 0) {
            return val;
         } else {
            return Day18a.registers.get(reg);
         }
      }

      public char getReg() {
         return reg;
      }

   }

   private static class SndWorker extends Worker.SingleValWorker {

      public SndWorker(String s) {
         super(s);
      }

      @Override
      public void work() {
         Day18a.sound = val.getValue();
         Day18a.workerPosition++;
      }

   }

   private static class SetWorker extends Worker.DoubleValWorker {

      public SetWorker(String s1, String s2) {
         super(s1, s2);
      }

      @Override
      public void work() {
         Day18a.registers.put(val1.getReg(), val2.getValue());
         Day18a.workerPosition++;
      }

   }

   private static class AddWorker extends Worker.DoubleValWorker {

      public AddWorker(String s1, String s2) {
         super(s1, s2);
      }

      @Override
      public void work() {
         long newValue = val1.getValue() + val2.getValue();
         Day18a.registers.put(val1.getReg(), newValue);
         Day18a.workerPosition++;
      }

   }

   private static class MulWorker extends Worker.DoubleValWorker {

      public MulWorker(String s1, String s2) {
         super(s1, s2);
      }

      @Override
      public void work() {
         long newValue = val1.getValue() * val2.getValue();
         Day18a.registers.put(val1.getReg(), newValue);
         Day18a.workerPosition++;
      }

   }

   private static class ModWorker extends Worker.DoubleValWorker {

      public ModWorker(String s1, String s2) {
         super(s1, s2);
      }

      @Override
      public void work() {
         long newValue = val1.getValue() % val2.getValue();
         Day18a.registers.put(val1.getReg(), newValue);
         Day18a.workerPosition++;
      }

   }

   private static class RcvWorker extends Worker.SingleValWorker {

      public RcvWorker(String s) {
         super(s);
      }

      @Override
      public void work() {
         if (val.getValue() != 0) {
            Day18a.rcvCalled = true;
         } else {
            Day18a.workerPosition++;
         }
      }

   }

   private static class JgzWorker extends Worker.DoubleValWorker {

      public JgzWorker(String s1, String s2) {
         super(s1, s2);
      }

      @Override
      public void work() {
         if (val1.getValue() > 0) {
            Day18a.workerPosition += val2.getValue();
         } else {
            Day18a.workerPosition++;
         }
      }

   }

}
