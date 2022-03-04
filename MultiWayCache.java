import java.lang.Math;
import java.lang.Math;

public class MultiWayCache {

   int [][][] cacheArr;
   int tagBits, indexBits, cacheSize, wordsPerBlock, assoc; 

   public MultiWayCache(int cacheSize, int assoc, int wordsPerBlock) {
     
      int byteOffsetBits = 2;
      int blockOffsetBits = (int)(Math.log(wordsPerBlock) / Math.log(2));
      this.cacheSize = cacheSize;
      this.assoc = assoc;
      this.wordsPerBlock = wordsPerBlock;
      this.indexBits = (int)(Math.log(cacheSize / (4 * wordsPerBlock * assoc)) / Math.log(2));
      this.tagBits = 32 - indexBits - blockOffsetBits - byteOffsetBits;
      this.cacheArr = new int[assoc][(int)(Math.pow(2, indexBits))][2];
      // cacheArr[assoc][index][0] = lineNum
      // cacheArr[assoc][index][1] = tag 

   }

   public int storeAddr(int lineNum, int addr) {
      int index = (addr >> 2) & (int)(Math.pow(2, indexBits) - 1);
      int tag = addr >> (32 - tagBits);
      int lru = cacheArr[0][index][0];
      int lruIndex = 0;
      for (int k = 0; k < assoc; k++) {
         if(cacheArr[k][index][1] == tag){ // check if tag is in cache
            cacheArr[k][index][0] = lineNum; // if so, update lineNum
            return 1;
         }
         else {
            if (Math.min(lru, cacheArr[k][index][0]) == cacheArr[k][index][0]) {
               lru = cacheArr[k][index][0];
               lruIndex = k;
            }
         }
      }
      cacheArr[lruIndex][index][0] = lru;
      cacheArr[lruIndex][index][1] = tag;
      return 0;
   }

}
