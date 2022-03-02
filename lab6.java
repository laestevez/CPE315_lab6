/* 
 * Luis Estevez
 * Vincent Viloria
 * CPE 315-07
 * Lab 6
*/

public class lab4 {
    static int addrCount;

    public static int oneWay(int hitCount, int addr, int[] cache){

        //byte_Offset of two, no block_Offset, index is 9 bits, tag is 21 bits
        int index = (addr >> 2) & 511; //grabs the index
        System.out.println(index);

        int tag = addr >> 9;

        if(cache[index] == -1) //cache is empty. Miss adds it to cache
            cache[index] = tag;
        else if(cache[index] == tag){ //in the cache, hit adds one to the counter
            hitCount++;
        }else{ //cache has something but not equal, Miss replaces the tag in cache
            cache[index] = tag;
        }
        return hitCount;
    }

    public static void main(String[] args) {
        Parser parser = new Parser();
        addrCount = 0;

        //Functions could return an int that counts the hit count
        int hit1, hit2, hit3, hit4, hit5, hit6, hit7;
        int[] problemOne = new int[512];

        if (args.length == 0) {
           System.out.println("usage: java lab6 [filename]");
           return;
        }

        String filename = args[0];
        Scanner scanner = new Scanner(System.in);
        File inputFile = new File(filename);

        //read file
        try {
            File scriptFile = new File(scriptName);
            Scanner scanner = new Scanner(scriptFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                //to do: need to grab just the addr not the whole line

                //checks \n
                if (addr.length() == 0)
                    continue;

                //to do: add funtions in here
                hit1 = oneWay(hit1, addr, problemOne);

                //adds to line count
                addrCount++;
            }
         }
      }
      catch (FileNotFoundException e) {
         System.out.println("File not found");
         e.printStackTrace();
      }
     }
}
