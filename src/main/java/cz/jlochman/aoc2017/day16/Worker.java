package cz.jlochman.aoc2017.day16;

public interface Worker {

   void work();

   public class Spinner implements Worker {

      private int spinBy;

      public Spinner(int spinBy) {
         this.spinBy = spinBy;
      }

      @Override
      public void work() {
         char[] newPrograms = new char[Day16.programs.length];
         for (int i = 0; i < Day16.programs.length; i++) {
            newPrograms[(i + spinBy) % Day16.programs.length] =
                  Day16.programs[i];
         }
         Day16.programs = newPrograms;
      }

   }

   public class Exchanger implements Worker {

      private int p1;
      private int p2;

      public Exchanger(int p1, int p2) {
         this.p1 = p1;
         this.p2 = p2;
      }

      @Override
      public void work() {
         char ch1 = Day16.programs[p1];
         Day16.programs[p1] = Day16.programs[p2];
         Day16.programs[p2] = ch1;
      }

   }

   public class Partner implements Worker {

      private char ch1;
      private char ch2;

      public Partner(char ch1, char ch2) {
         this.ch1 = ch1;
         this.ch2 = ch2;
      }

      @Override
      public void work() {
         int p1 = 0, p2 = 0;
         for (int i = 0; i < Day16.programs.length; i++) {
            if (Day16.programs[i] == ch1) {
               p1 = i;
            }
            if (Day16.programs[i] == ch2) {
               p2 = i;
            }
         }
         char ch1 = Day16.programs[p1];
         Day16.programs[p1] = Day16.programs[p2];
         Day16.programs[p2] = ch1;
      }

   }

}
