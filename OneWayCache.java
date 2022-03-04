import java.lang.Math;
import java.lang.Math;

public class OneWayCache {

   int [] cacheArr;
   int tagBits, indexBits, cacheSize, wordsPerBlock, assoc; 

   public OneWayCache(int cacheSize, int assoc, int wordsPerBlock) {

      int byteOffsetBits = 2;
      int blockOffsetBits = (int)(Math.log(wordsPerBlock) / Math.log(2));
      this.cacheSize = cacheSize;
      this.assoc = assoc;
      this.wordsPerBlock = wordsPerBlock;
      this.indexBits = (int)(Math.log(cacheSize / (4 * wordsPerBlock * assoc)) / Math.log(2));
      this.tagBits = 32 - indexBits - blockOffsetBits - byteOffsetBits;
      this.cacheArr = new int[assoc];

   }

   public int storeAddr(int lineNum, int addr) {
      int index = (addr >> 2) & (int)(Math.pow(2, indexBits) - 1);
      int tag = addr >> (32 - tagBits);
      int lru = cacheArr[0][index][0];
      int lruIndex = 0;

      if(cache[index] == -1){ //cache is empty. Miss adds it to cache
        cache[index] = tag;
        return 0;
      }
      else if(cache[index] == tag){ //in the cache, hit adds one to the counter
        return 1;
      }else{ //cache has something but not equal, Miss replaces the tag in cache
        cache[index] = tag;
        return 0;
      }
   }

}