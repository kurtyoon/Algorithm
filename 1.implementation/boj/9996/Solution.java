import java.io.*;

public class Solution {

    public static class PatternMatcher {
        private final String prefix;
        private final String suffix;

        public PatternMatcher(String pattern) {
            int pos = pattern.indexOf('*');
            this.prefix = pattern.substring(0, pos);
            this.suffix = pattern.substring(pos + 1);
        }

        public boolean matches(String s) {
            if (prefix.length() + suffix.length() > s.length()) {
                return false;
            }
            return s.startsWith(prefix) && s.endsWith(suffix);
        }
    }

    public void solution(int n, String pattern, BufferedReader br, BufferedWriter bw) throws IOException {
        PatternMatcher matcher = new PatternMatcher(pattern);

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            if (matcher.matches(s)) {
                bw.write("DA\n");
            } else {
                bw.write("NE\n");
            }
        }
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            int n = Integer.parseInt(br.readLine());
            String pattern = br.readLine();

            Main main = new Main();
            main.solution(n, pattern, br, bw);

            bw.flush();
            br.close();
            bw.close();

        } catch (IOException e) {
            System.err.println("오류 발생: " + e.getMessage());
        }
    }
}