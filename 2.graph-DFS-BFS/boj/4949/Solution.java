import java.io.*;
import java.util.*;

public class Solution {

    public static class BracketChecker {

        public boolean isValid(String s) {
            Stack<Character> stack = new Stack<>();

            for (char c : s.toCharArray()) {
                if (c == '(' || c == '[') {
                    stack.push(c);
                } else if (c == ')') {
                    if (stack.isEmpty() || stack.peek() != '(') {
                        return false;
                    }
                    stack.pop();
                } else if (c == ']') {
                    if (stack.isEmpty() || stack.peek() != '[') {
                        return false;
                    }
                    stack.pop();
                }
            }

            return stack.isEmpty();
        }
    }

    public static List<String> solution(List<String> inputs) {
        BracketChecker checker = new BracketChecker();
        List<String> results = new ArrayList<>();

        for (String input : inputs) {
            if (checker.isValid(input)) {
                results.add("yes");
            } else {
                results.add("no");
            }
        }

        return results;
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            List<String> inputs = new ArrayList<>();

            while (true) {
                String input = br.readLine();
                if (input.equals(".")) break;
                inputs.add(input);
            }

            List<String> results = solution(inputs);
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