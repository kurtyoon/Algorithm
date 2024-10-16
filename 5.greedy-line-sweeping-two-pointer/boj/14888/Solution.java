import java.io.*;
import java.util.*;

public class Solution {

    private static class Calculator {
        private int[] numbers;
        private int maxResult = Integer.MIN_VALUE;
        private int minResult = Integer.MAX_VALUE;

        public Calculator(int[] numbers) {
            this.numbers = numbers;
        }

        public void calculate(int index, int current, int plus, int minus, int multiply, int divide) {
            if (index == numbers.length - 1) {
                maxResult = Math.max(current, maxResult);
                minResult = Math.min(current, minResult);
                return;
            }
            if (plus > 0) calculate(index + 1, current + numbers[index + 1], plus - 1, minus, multiply, divide);
            if (minus > 0) calculate(index + 1, current - numbers[index + 1], plus, minus - 1, multiply, divide);
            if (multiply > 0) calculate(index + 1, current * numbers[index + 1], plus, minus, multiply - 1, divide);
            if (divide > 0) calculate(index + 1, current / numbers[index + 1], plus, minus, multiply, divide - 1);
        }

        public int getMaxResult() {
            return maxResult;
        }

        public int getMinResult() {
            return minResult;
        }
    }

    public static String solution(int n, int[] numbers, int plus, int minus, int multiply, int divide) {
        Calculator calculator = new Calculator(numbers);
        calculator.calculate(0, numbers[0], plus, minus, multiply, divide);

        return calculator.getMaxResult() + "\n" + calculator.getMinResult();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] numbers = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int plus = Integer.parseInt(st.nextToken());
        int minus = Integer.parseInt(st.nextToken());
        int multiply = Integer.parseInt(st.nextToken());
        int divide = Integer.parseInt(st.nextToken());

        String result = solution(n, numbers, plus, minus, multiply, divide);

        bw.write(result);
        bw.flush();
        bw.close();
        br.close();
    }
}