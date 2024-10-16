import java.util.*;
import java.io.*;

public class Solution {

   public static class FrequencyCounter {
       private final String str;
       private final int[] cnt;

       public FrequencyCounter(String str) {
           this.str = str;
           this.cnt = new int[26];
       }

        public void countFrequencies() {

           for (char c : str.toCharArray()) {

               if (c >= 'a' && c <= 'z') {
                   cnt[c - 'a']++;
               }
           }
       }

        public void printFrequencies(BufferedWriter bw) throws IOException {

           for (int i = 0; i < 26; i++) {

               bw.write(cnt[i] + " ");
           }

            bw.newLine();
       }
   }

    public static void main(String[] args) {
       try {
           BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
           BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

           String str = br.readLine();

           FrequencyCounter frequencyCounter = new FrequencyCounter(str);

           frequencyCounter.countFrequencies();
           frequencyCounter.printFrequencies(bw);

           bw.flush();
           br.close();
           bw.close();
           
       } catch (IOException e) {
           System.err.println("오류 발생: " + e.getMessage());
       }
   }
}