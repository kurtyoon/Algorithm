import java.io.*;

public class Solution {

    public static class SmallestNumberFinder {
        private final int n;

        public SmallestNumberFinder(int n) {
            this.n = n;
        }

        public int findSmallestLength() {
            int count = 1;
            int length = 1;

            while (true) {
                if (count % n == 0) {
                    return length;
                } else {
                    count = (count * 10 + 1) % n;
                    length++;
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input;

            while ((input = br.readLine()) != null && !input.isEmpty()) {
                int n = Integer.parseInt(input);

                SmallestNumberFinder finder = new SmallestNumberFinder(n);
                int result = finder.findSmallestLength();

                System.out.println(result);
            }

        } catch (IOException e) {
            System.err.println("입출력 오류 발생: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("잘못된 입력: 숫자를 입력해야 합니다.");
        }
    }
}