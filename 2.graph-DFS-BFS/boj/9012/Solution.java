import java.io.*;
import java.util.*;

public class Solution {

    public static class ParenthesisChecker {

        public boolean isValidParentheses(String s) {
            Stack<Character> stack = new Stack<>();

            for (char c : s.toCharArray()) {
                if (c == '(') {
                    stack.push(c);
                } else {
                    if (!stack.isEmpty()) {
                        stack.pop();
                    } else {
                        return false;
                    }
                }
            }

            return stack.isEmpty();
        }
    }

    public static List<String> solution(List<String> parentheses) {
        ParenthesisChecker checker = new ParenthesisChecker();
        List<String> results = new ArrayList<>();

        for (String s : parentheses) {
            if (checker.isValidParentheses(s)) {
                results.add("YES");
            } else {
                results.add("NO");
            }
        }

        return results;
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            int n = Integer.parseInt(br.readLine());
            List<String> parentheses = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                parentheses.add(br.readLine());
            }

            List<String> results = solution(parentheses);
            for (String result : results) {
                bw.write(result + "\n");
            }

            bw.flush();
            br.close();
            bw.close();

        } catch (IOException e) {
            System.err.println("입출력 오류 발생: " + e.getMessage());
        }
    }
}