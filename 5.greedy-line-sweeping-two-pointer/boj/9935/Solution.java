import java.io.*;

public class Solution {

    public static class StringProcessor {
        private final String source;
        private final String pattern;

        public StringProcessor(String source, String pattern) {
            this.source = source;
            this.pattern = pattern;
        }

    public String processString() {
            StringBuilder result = new StringBuilder();

            for (char c : source.toCharArray()) {
                result.append(c);

                if (result.length() >= pattern.length()) {
                    if (result.substring(result.length() - pattern.length()).equals(pattern)) {
                        result.delete(result.length() - pattern.length(), result.length());
                    }
                }
            }

            if (result.length() == 0) {
                return "FRULA";
            } else {
                return result.toString();
            }
        }
    }

    public static String solution(String source, String pattern) {
        StringProcessor processor = new StringProcessor(source, pattern);
        return processor.processString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String S = br.readLine();
        String T = br.readLine();

        String result = solution(S, T);

        bw.write(result);
        bw.newLine();

        bw.flush();
        br.close();
        bw.close();
    }
}