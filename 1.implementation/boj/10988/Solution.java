import java.io.*;

public class Solution {

    public static class PalindromeChecker {
        private final String str;

        public PalindromeChecker(String str) {
            this.str = str;
        }

        public boolean isPalindrome() {
            String reversedStr = new StringBuilder(str).reverse().toString();
            return str.equals(reversedStr);
        }
    }

    public int solution(String input) {
        PalindromeChecker checker = new PalindromeChecker(input);
        return checker.isPalindrome() ? 1 : 0;
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String input = br.readLine();

            Main main = new Main();
            int result = main.solution(input);

            bw.write(result + "\n");

            bw.flush();
            br.close();
            bw.close();

        } catch (IOException e) {
            System.err.println("오류 발생: " + e.getMessage());
        }
    }
}