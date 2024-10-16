import java.io.*;
import java.util.*;

public class Solution {

    public static class WordChecker {
        private final String word;

        public WordChecker(String word) {
            this.word = word;
        }

        public boolean isGoodWord() {
            Stack<Character> stack = new Stack<>();

            for (char ch : word.toCharArray()) {
                if (!stack.isEmpty() && stack.peek() == ch) {
                    stack.pop();
                } else {
                    stack.push(ch);
                }
            }

            return stack.isEmpty();
        }
    }

    public void solution(int n, BufferedReader br, BufferedWriter bw) throws IOException {
        int goodWordCount = 0;

        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            WordChecker checker = new WordChecker(word);

            if (checker.isGoodWord()) {
                goodWordCount++;
            }
        }

        bw.write(goodWordCount + "\n");
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            // 입력 처리
            int n = Integer.parseInt(br.readLine());

            Main main = new Main();
            main.solution(n, br, bw);

            // 출력 스트림 닫기
            bw.flush();
            br.close();
            bw.close();

        } catch (IOException e) {
            System.err.println("입출력 오류 발생: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("숫자 형식 오류: " + e.getMessage());
        }
    }
}