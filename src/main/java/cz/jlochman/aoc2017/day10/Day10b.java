package cz.jlochman.aoc2017.day10;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.google.common.primitives.Bytes;

public class Day10b {

   public static void main(String[] args) throws UnsupportedEncodingException {
      resolve("230,1,2,221,97,252,168,169,57,99,0,254,181,255,235,167");
   }

   private static void resolve(String s) throws UnsupportedEncodingException {
      byte[] inputBytes = s.getBytes("ASCII");
      byte[] appendBytes = new byte[] { 17, 31, 73, 47, 23 };
      byte[] input = Bytes.concat(inputBytes, appendBytes);

      int[] list = IntStream.rangeClosed(0, 255)
                            .toArray();
      int currentPosition = 0;
      int skipSize = 0;

      for (int repeat = 0; repeat < 64; repeat++) {
         for (int i = 0; i < input.length; i++) {
            int in = input[i];
            for (int k = currentPosition; k < currentPosition + in / 2; k++) {
               int firstPos = k % list.length;
               int mirrorPos = (2 * currentPosition + in - k - 1) % list.length;
               int helper = list[firstPos];
               list[firstPos] = list[mirrorPos];
               list[mirrorPos] = helper;
            }
            currentPosition += in + skipSize;
            skipSize++;
         }
      }

      List<Integer> xors = new ArrayList<>();
      for (int i = 0; i < list.length; i += 16) {
         int xor = list[i];
         for (int k = i + 1; k < i + 16; k++) {
            xor ^= list[k];
         }
         xors.add(xor);
      }

      System.out.println(xors.stream()
                             .map(xor -> String.format("%02x", xor))
                             .collect(Collectors.joining("")));

   }

}
