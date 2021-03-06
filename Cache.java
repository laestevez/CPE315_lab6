/* 
 * Luis Estevez
 * Vincent Viloria
 * CPE 315-07
 * Lab 6
*/
import java.lang.Math;

public class Cache {

   int [][][] cacheArr;
   int tagBits, indexBits, blockOffsetBits, cacheSize, wordsPerBlock, assoc; 

   public Cache(int cacheSize, int assoc, int wordsPerBlock) {
     
      int byteOffsetBits = 2;
      this.blockOffsetBits = (int)(Math.log(wordsPerBlock) / Math.log(2));
      this.cacheSize = cacheSize;
      this.assoc = assoc;
      this.wordsPerBlock = wordsPerBlock;
      this.indexBits = (int)(Math.log(cacheSize / (4 * wordsPerBlock * assoc)) / Math.log(2));
      this.tagBits = 32 - indexBits - blockOffsetBits - byteOffsetBits;
      this.cacheArr = new int[assoc][(int)(Math.pow(2, indexBits))][2];

   }

   public int storeAddr(int lineNum, int addr) {
      int index = (addr >> (2 + blockOffsetBits)) & (int)(Math.pow(2, indexBits) - 1);
      int tag = addr >> (32 - tagBits);
      int lru = cacheArr[0][index][0];
      int lruIndex = 0;
      for (int k = 0; k < assoc; k++) {
         if(cacheArr[k][index][1] == tag){ 
            cacheArr[k][index][0] = lineNum; 
            return 1;
         }
         if (cacheArr[k][index][0] < lru) {
            lru = cacheArr[k][index][0];
            lruIndex = k;
         }
      }
      cacheArr[lruIndex][index][0] = lineNum;
      cacheArr[lruIndex][index][1] = tag;
      return 0;
   }
}
