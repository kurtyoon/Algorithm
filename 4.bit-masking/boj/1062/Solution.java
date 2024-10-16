import java.io.*;
import java.util.*;

public class Solution {

    public static class WordMask {
        private final int[] words;

        public WordMask(int n) {
            words = new int[n];
        }

        public void addWord(int index, String word) {
            for (char c : word.toCharArray()) {
                words[index] |= (1 << (c - 'a'));
            }
        }

        public int countReadableWords(int mask) {
            int count = 0;
            for (int word : words) {
                if (word != 0 && (word & mask) == word) {
                    count++;
                }
            }
            return count;
        }

        public int findMaxReadable(int index, int remaining, int mask) {
            if (remaining < 0) {
                return 0;
            }
            if (index == 26) {
                return countReadableWords(mask);
            }

            int result = findMaxReadable(index + 1, remaining - 1, mask | (1 << index));

            if (index != 'a' - 'a' && index != 'n' - 'a' && index != 't' - 'a' && index != 'i' - 'a' && index != 'c' - 'a') {
                result = Math.max(result, findMaxReadable(index + 1, remaining, mask));
            }

            return result;
        }
    }

    public static int solution(int n, int m, String[] words) {
        WordMask wordMask = new WordMask(n);

        for (int i = 0; i < n; i++) {
            wordMask.addWord(i, words[i]);
        }

        return wordMask.findMaxReadable(0, m, 0);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String[] words = new String[n];

        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
        }

        int result = solution(n, m, words);
        bw.write(String.valueOf(result));

        bw.flush();
        bw.close();
        br.close();
    }
}