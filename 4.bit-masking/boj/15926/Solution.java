import java.io.*;
import java.util.*;

public class Solution {

    public static class ParenthesisChecker {
        private final int[] d;
        private final String s;
        private final int n;

        public ParenthesisChecker(int n, String s) {
            this.n = n;
            this.s = s;
            this.d = new int[n];
        }

        public int longestValidParenthesis() {
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == '(') {
                    stack.push(i);
                } else if (!stack.isEmpty()) {
                    d[i] = 1;
                    d[stack.pop()] = 1;
                }
            }

            int maxLen = 0, currentLen = 0;
            for (int i = 0; i < n; i++) {
                if (d[i] == 1) {
                    currentLen++;
                    maxLen = Math.max(maxLen, currentLen);
                } else {
                    currentLen = 0;
                }
            }

            return maxLen;
        }
    }

    public static int solution(int n, String s) {
        ParenthesisChecker checker = new ParenthesisChecker(n, s);
        return checker.longestValidParenthesis();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        String s = br.readLine();

        int result = solution(n, s);

        bw.write(String.valueOf(result));
        bw.newLine();

        bw.flush();
        br.close();
        bw.close();
    }
}