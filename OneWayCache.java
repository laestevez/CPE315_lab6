import java.lang.Math;

public class OneWayCache {

   int [] cacheArr;
   int tagBits, indexBits, cacheSize, wordsPerBlock, assoc, blockOffsetBits; 

   public OneWayCache(int cacheSize, int assoc, int wordsPerBlock) {

      int byteOffsetBits = 2;
      this.blockOffsetBits = (int)(Math.log(wordsPerBlock) / Math.log(2));
      this.cacheSize = cacheSize;
      this.assoc = assoc;
      this.wordsPerBlock = wordsPerBlock;
      this.indexBits = (int)(Math.log(cacheSize / (4 * wordsPerBlock * assoc)) / Math.log(2));
      this.tagBits = 32 - indexBits - blockOffsetBits - byteOffsetBits;
      this.cacheArr = new int[(int)(Math.pow(2, indexBits))];

   }

   public int storeAddr(int addr) {
      int index = (addr >> 2 + blockOffsetBits) & (int)(Math.pow(2, indexBits) - 1);
      int tag = addr >> (32 - tagBits);

      if(cacheArr[index] == -1){ //cache is empty. Miss adds it to cache
        cacheArr[index] = tag;
        return 0;
      }
      else if(cacheArr[index] == tag){ //in the cache, hit adds one to the counter
        return 1;
      }else{ //cache has something but not equal, Miss replaces the tag in cache
        cacheArr[index] = tag;
        return 0;
      }
   }

}
