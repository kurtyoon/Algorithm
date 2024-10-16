import java.io.*;

public class Solution {

    public static class FactorialZeroCalculator {

        private int countDivisors(int num, int divisor) {
            int count = 0;
            for (int i = divisor; i <= num; i *= divisor) {
                count += num / i;
            }
            return count;
        }

        public int calculateTrailingZeros(int num) {
            int count2 = countDivisors(num, 2);
            int count5 = countDivisors(num, 5);
            return Math.min(count2, count5);
        }
    }

    public static int[] solution(int[] numbers) {
        FactorialZeroCalculator calculator = new FactorialZeroCalculator();
        int[] results = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            results[i] = calculator.calculateTrailingZeros(numbers[i]);
        }

        return results;
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            int n = Integer.parseInt(br.readLine());
            int[] numbers = new int[n];

            for (int i = 0; i < n; i++) {
                numbers[i] = Integer.parseInt(br.readLine());
            }

            int[] results = solution(numbers);

            for (int result : results) {
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