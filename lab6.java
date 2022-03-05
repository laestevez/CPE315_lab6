/* 
 * Luis Estevez
 * Vincent Viloria
 * CPE 315-07
 * Lab 6
*/
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.PrintStream;
import java.lang.Math.*;


public class lab6 {

   public static void main(String[] args) {
      int twoKB = (int)(Math.pow(2, 11));
      int fourKB = (int)(Math.pow(2, 12));

      int hit1 = 0, hit2 = 0, hit3 = 0 , hit4 = 0,
            hit5 = 0, hit6 = 0, hit7 = 0;

      if (args.length == 0) {
        System.out.println("usage: java lab6 [filename]");
        return;
      }

      String filename = args[0];
      File inputFile = new File(filename);
      try {
         Scanner scanner = new Scanner(inputFile);
         int lineNum = 0;

         OneWayCache cache1 = new OneWayCache(twoKB, 1, 1);
         OneWayCache cache2 = new OneWayCache(twoKB, 1, 2);
         OneWayCache cache3 = new OneWayCache(twoKB, 1, 4);
         MultiWayCache cache4 = new MultiWayCache(twoKB, 2, 1);
         MultiWayCache cache5 = new MultiWayCache(twoKB, 4, 1);
         MultiWayCache cache6 = new MultiWayCache(twoKB, 4, 4);
         OneWayCache cache7 = new OneWayCache(fourKB, 1, 1);

         while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            String addr = line.substring(2);
            int addrDecimal = Integer.parseInt(addr, 16);
            hit1 += cache1.storeAddr(addrDecimal);
            hit2 += cache2.storeAddr(addrDecimal);
            hit3 += cache3.storeAddr(addrDecimal);
            hit4 = hit4 + cache4.storeAddr(lineNum, addrDecimal);
            hit5 = hit5 + cache5.storeAddr(lineNum, addrDecimal);
            hit6 = hit6 + cache6.storeAddr(lineNum, addrDecimal);
            hit7 += cache7.storeAddr(addrDecimal);
            lineNum++;
         }
      }
      catch (FileNotFoundException e) {
         System.out.println("Oh no my code");
         e.printStackTrace();
      }

      System.out.println("Cache #1");
      System.out.println(String.format("Cache size: %dB\t Associativity: %d\t Block size: %d", twoKB, 1, 1));
      System.out.println(String.format("Hits: %d\t Hit Rate: %.2f%%", hit1, hit1 / 5000000.0 * 100));
      System.out.println("---------------------------");
      System.out.println("Cache #2");
      System.out.println(String.format("Cache size: %dB\t Associativity: %d\t Block size: %d", twoKB, 1, 2));
      System.out.println(String.format("Hits: %d\t Hit Rate: %.2f%%", hit2, hit2 / 5000000.0 * 100));
      System.out.println("---------------------------");
      System.out.println("Cache #3");
      System.out.println(String.format("Cache size: %dB\t Associativity: %d\t Block size: %d", twoKB, 1, 4));
      System.out.println(String.format("Hits: %d\t Hit Rate: %.2f%%", hit3, hit3 / 5000000.0 * 100));
      System.out.println("---------------------------");
      System.out.println("Cache #4");
      System.out.println(String.format("Cache size: %dB\t Associativity: %d\t Block size: %d", twoKB, 2, 1));
      System.out.println(String.format("Hits: %d\t Hit Rate: %.2f%%", hit4, hit4 / 5000000.0 * 100));
      System.out.println("---------------------------");
      System.out.println("Cache #5");
      System.out.println(String.format("Cache size: %dB\t Associativity: %d\t Block size: %d", twoKB, 4, 1));
      System.out.println(String.format("Hits: %d\t Hit Rate: %.2f%%", hit5, hit5 / 5000000.0 * 100));
      System.out.println("---------------------------");
      System.out.println("Cache #6");
      System.out.println(String.format("Cache size: %dB\t Associativity: %d\t Block size: %d", twoKB, 4, 4));
      System.out.println(String.format("Hits: %d\t Hit Rate: %.2f%%", hit6, hit6 / 5000000.0 * 100.0));
      System.out.println("---------------------------");
      System.out.println("Cache #7");
      System.out.println(String.format("Cache size: %dB\t Associativity: %d\t Block size: %d", fourKB, 1, 1));
      System.out.println(String.format("Hits: %d\t Hit Rate: %.2f%%", hit7, hit7 / 5000000.0 * 100));
      System.out.println("---------------------------");
     }
}
