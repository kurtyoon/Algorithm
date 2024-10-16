import java.io.*;

public class Solution {

    public static class WordChecker {
        private final String word;
        private boolean invalid;

        public WordChecker(String word) {
            this.word = word;
            this.invalid = false;
        }

        public String checkWord() {
            for (int i = 0; i < word.length(); i++) {
                if (i < word.length() - 1 && (word.substring(i, i + 2).equals("pi") || word.substring(i, i + 2).equals("ka"))) {
                    i++;
                } else if (i < word.length() - 2 && word.substring(i, i + 3).equals("chu")) {
                    i += 2;
                } else {
                    invalid = true;
                    break;
                }
            }

            return invalid ? "NO" : "YES";
        }
    }

    public static String solution(String word) {
        WordChecker wordChecker = new WordChecker(word);
        return wordChecker.checkWord();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();

        String result = solution(s);
        bw.write(result);
        bw.newLine();

        bw.flush();
        br.close();
        bw.close();
    }
}