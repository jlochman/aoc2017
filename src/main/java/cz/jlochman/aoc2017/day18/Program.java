package cz.jlochman.aoc2017.day18;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import cz.jlochman.aoc2017.utils.FileUtils;

class Program {

   private Map<Character, Long> registers      = new HashMap<>();
   private int                  workerPosition = 0;
   private Program              otherProgram;
   private List<Long>           rcvQueue       =
         Collections.synchronizedList(new ArrayList<>());
   private List<Worker>         workers;
   private int                  sndValueCount  = 0;

   public Program(long id) {
      registers.put('a', 0L);
      registers.put('b', 0L);
      registers.put('f', 0L);
      registers.put('i', 0L);
      registers.put('p', id);

      try (Scanner in = FileUtils.getScanner("Day18/input.txt")) {
         workers = new ArrayList<>();
         while (in.hasNextLine()) {
            Scanner line = new Scanner(in.nextLine());
            switch (line.next()) {
            case "snd":
               workers.add(new SndWorker(line.next(), this));
               break;
            case "set":
               workers.add(new SetWorker(line.next(), line.next(), this));
               break;
            case "add":
               workers.add(new AddWorker(line.next(), line.next(), this));
               break;
            case "mul":
               workers.add(new MulWorker(line.next(), line.next(), this));
               break;
            case "mod":
               workers.add(new ModWorker(line.next(), line.next(), this));
               break;
            case "rcv":
               workers.add(new RcvWorker(line.next(), this));
               break;
            case "jgz":
               workers.add(new JgzWorker(line.next(), line.next(), this));
               break;
            default:
               System.out.println("not handled: " + line.toString());
               break;
            }
            line.close();
         }

      }

   }
   
   public Map<Character, Long> getRegisters() {
      return registers;
   }

   public void setOtherProgram(Program otherProgram) {
      this.otherProgram = otherProgram;
   }

   public void recieve(long value) {
      rcvQueue.add(value);
   }

   public void step() {
      if (workers.get(workerPosition) instanceof SndWorker) {
         sndValueCount++;
      }
      workers.get(workerPosition)
             .work();
   }

   public int getSndValueCount() {
      return sndValueCount;
   }

   private interface Worker {

      void work();

      abstract class SingleValWorker implements Worker {

         protected Value val;

         public SingleValWorker(String s, Program program) {
            this.val = new Value(s, program);
         }
      }

      abstract class DoubleValWorker implements Worker {

         protected Value val1;
         protected Value val2;

         public DoubleValWorker(String s1, String s2, Program program) {
            this.val1 = new Value(s1, program);
            this.val2 = new Value(s2, program);
         }
      }

   }

   private static class Value {

      private long val;
      private char reg;
      private Program program;

      public Value(String s, Program program) {
         this.program = program;
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
            return program.registers.get(reg);
         }
      }

      public char getReg() {
         return reg;
      }

   }

   private class SndWorker extends Worker.SingleValWorker {

      public SndWorker(String s, Program program) {
         super(s, program);
      }

      @Override
      public void work() {
         otherProgram.recieve(val.getValue());
         workerPosition++;
      }

   }

   private class SetWorker extends Worker.DoubleValWorker {

      public SetWorker(String s1, String s2, Program program) {
         super(s1, s2, program);
      }

      @Override
      public void work() {
         registers.put(val1.getReg(), val2.getValue());
         workerPosition++;
      }

   }

   private class AddWorker extends Worker.DoubleValWorker {

      public AddWorker(String s1, String s2, Program program) {
         super(s1, s2, program);
      }

      @Override
      public void work() {
         long newValue = val1.getValue() + val2.getValue();
         registers.put(val1.getReg(), newValue);
         workerPosition++;
      }

   }

   private class MulWorker extends Worker.DoubleValWorker {

      public MulWorker(String s1, String s2, Program program) {
         super(s1, s2, program);
      }

      @Override
      public void work() {
         long newValue = val1.getValue() * val2.getValue();
         registers.put(val1.getReg(), newValue);
         workerPosition++;
      }

   }

   private class ModWorker extends Worker.DoubleValWorker {

      public ModWorker(String s1, String s2, Program program) {
         super(s1, s2, program);
      }

      @Override
      public void work() {
         long newValue = val1.getValue() % val2.getValue();
         registers.put(val1.getReg(), newValue);
         workerPosition++;
      }

   }

   private class RcvWorker extends Worker.SingleValWorker {

      public RcvWorker(String s, Program program) {
         super(s, program);
      }

      @Override
      public void work() {
         while (rcvQueue.isEmpty()) {
            try {
               Thread.sleep(1L);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
         }
         registers.put(val.getReg(), rcvQueue.get(0));
         rcvQueue.remove(0);
         workerPosition++;
      }

   }

   private class JgzWorker extends Worker.DoubleValWorker {

      public JgzWorker(String s1, String s2, Program program) {
         super(s1, s2, program);
      }

      @Override
      public void work() {
         if (val1.getValue() > 0) {
            workerPosition += val2.getValue();
         } else {
            workerPosition++;
         }
      }

   }

}
