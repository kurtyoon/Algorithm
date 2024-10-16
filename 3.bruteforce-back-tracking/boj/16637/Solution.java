import java.io.*;
import java.util.*;

public class Solution {

    public static class ExpressionEvaluator {
        private final List<Integer> numbers = new ArrayList<>();
        private final List<Character> operators = new ArrayList<>();
        private int result = Integer.MIN_VALUE;

        public ExpressionEvaluator(String expression) {
            for (int i = 0; i < expression.length(); i++) {
                if (i % 2 == 0) {
                    numbers.add(expression.charAt(i) - '0');
                } else {
                    operators.add(expression.charAt(i));
                }
            }
        }

        private int applyOperator(char operator, int a, int b) {
            switch (operator) {
                case '+':
                    return a + b;
                case '-':
                    return a - b;
                case '*':
                    return a * b;
                default:
                    throw new IllegalArgumentException("Invalid operator: " + operator);
            }
        }

        public void evaluate(int index, int currentResult) {
            if (index == numbers.size() - 1) {
                result = Math.max(result, currentResult);
                return;
            }

            evaluate(index + 1, applyOperator(operators.get(index), currentResult, numbers.get(index + 1)));

            if (index + 2 < numbers.size()) {
                int combinedResult = applyOperator(operators.get(index + 1), numbers.get(index + 1), numbers.get(index + 2));
                evaluate(index + 2, applyOperator(operators.get(index), currentResult, combinedResult));
            }
        }

        public int getResult() {
            return result;
        }
    }

    public static int solution(int n, String expression) {
        ExpressionEvaluator evaluator = new ExpressionEvaluator(expression);
        evaluator.evaluate(0, evaluator.numbers.get(0));
        return evaluator.getResult();
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            int n = Integer.parseInt(br.readLine());
            String expression = br.readLine();

            int result = solution(n, expression);
            bw.write(result + "\n");

            bw.flush();
            br.close();
            bw.close();

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}