import java.util.*;

class Solution {

    public static class Calculator {
        private int[] numbers;
        private int target;
        private int solutions;

        public Calculator(int[] numbers, int target) {
            this.numbers = numbers;
            this.target = target;
            this.solutions = 0;
        }

        public int findTarget() {
            dfs(0, 0);
            return solutions;
        }

        private void dfs(int index, int currSum) {
            if (index == numbers.length) {
                if (currSum == target) {
                    solutions++;
                }

                return;
            }

            dfs(index + 1, currSum + numbers[index]);
            dfs(index + 1, currSum - numbers[index]);
        }
    }

    public int solution(int[] numbers, int target) {

        Calculator calculator = new Calculator(numbers, target);

        return calculator.findTarget();

    }
}