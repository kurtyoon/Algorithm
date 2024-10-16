import java.io.*;
import java.util.*;

public class Solution {

    public static class NameAnalyzer {
        private final int[] cnt = new int[26];
        private final List<String> names;

        public NameAnalyzer(List<String> names) {
            this.names = names;
        }

        public void analyze() {
            for (String name : names) {
                cnt[name.charAt(0) - 'a']++;
            }
        }

        public String getFrequentLetters() {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (cnt[i] >= 5) {
                    result.append((char) (i + 'a'));
                }
            }
            return result.toString();
        }
    }

    public String solution(int n, List<String> names) {
        NameAnalyzer analyzer = new NameAnalyzer(names);
        analyzer.analyze();
        String result = analyzer.getFrequentLetters();
        return result.isEmpty() ? "PREDAJA" : result;
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            int n = Integer.parseInt(br.readLine());

            List<String> names = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                names.add(br.readLine());
            }

            Main main = new Main();
            String result = main.solution(n, names);

            bw.write(result + "\n");

            bw.flush();
            br.close();
            bw.close();

        } catch (IOException e) {
            System.err.println("오류 발생: " + e.getMessage());
        }
    }
}