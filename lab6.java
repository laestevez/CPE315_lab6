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
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.io.PrintStream;
import java.lang.Math.*;


public class lab6 {

   public static void main(String[] args) {
      int addrCount = 0;
      int twoKB = (int)(Math.pow(2, 11));

        //Functions could return an int that counts the hit count
        int hit1 = 0, hit2 = 0, hit3 = 0 , hit4 = 0 ,
            hit5 = 0, hit6 = 0, hit7 = 0;
        int[] problemOne = new int[512];

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

            //MultiWayCache cache4 = new MultiWayCache(twoKB, 2, 1);
            // MultiWayCache cache5 = new MultiWayCache(twoKB, 4, 1);
            // MultiWayCache cache6 = new MultiWayCache(twoKB, 4, 4);
            while (scanner.hasNextLine()) {
               String line = scanner.nextLine().trim();
               String addr = line.substring(2);
               int addrDecimal = Integer.parseInt(addr, 16);

               hit1 += cache1.storeAddr(addrDecimal);
               //hit4 = hit4 + cache4.storeAddr(lineNum, addrDecimal);
               // hit5 = hit5 + cache5.storeAddr(lineNum, addrDecimal);
               // hit6 = hit6 + cache6.storeAddr(lineNum, addrDecimal);
               lineNum++;
            }

        System.out.println("Cache #1:");
        System.out.println(String.format("Cache size: %d\t Associativity: %d\t Block size: %d", twoKB, 1, 1));
        System.out.println(String.format("Hits: %d\t Hit rate: %f", hit1, (float)(hit1 / 5000000.0)));

        //  System.out.println("Cache #4:");
        //  System.out.println(String.format("Cache size: %d\t Associativity: %d\t Block size: %d", twoKB, 2, 1));
        //  System.out.println(String.format("Hits: %d\t Hit rate: %f", hit4, (float)(hit4 / 5000000.0)));
   
         // System.out.println("Cache #5:");
         // System.out.println(String.format("Cache size: %d\t Associativity: %d\t Block size: %d", twoKB, 4, 1));
         // System.out.println(String.format("Hits: %d\t Hit rate: %f", hit5, (float)(hit5 / 5000000.0)));

         // System.out.println("Cache #6:");
         // System.out.println(String.format("Cache size: %d\t Associativity: %d\t Block size: %d", twoKB, 4, 4));
         // System.out.println(String.format("Hits: %d\t Hit rate: %f", hit6, (float)(hit6 / 5000000.0)));
     }
      catch (FileNotFoundException e) {
         System.out.println("Oh no my code");
         e.printStackTrace();
      }
   }
}
