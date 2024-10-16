import java.io.*;
import java.util.*;

public class Solution {

    public static class Solver {

        private final List<Integer> numbers;

        public Solver(List<Integer> numbers) {
            this.numbers = numbers;

            if (numbers.size() != 9) {
                throw new IllegalArgumentException("입력한 숫자는 반드시 9개여야 합니다.");
            }
        }

        public List<Integer> findDwarves() throws Exception {
            List<Integer> result = new ArrayList<>();

            boolean found = combination(new ArrayList<>(), 0, 0, result);

            if (!found) {
                throw new Exception("합이 100인 7개의 숫자를 찾을 수 없습니다.");
            }

            return result;
        }

        private boolean combination(List<Integer> current, int start, int sum, List<Integer> result) {
                if (current.size() == 7) {
                    if (sum == 100) {
                        result.addAll(current);
                        return true;
                    }

                    return false;
                }

            for (int i = start; i < numbers.size(); i++) {
                current.add(numbers.get(i));

                if (combination(current, i + 1, sum + numbers.get(i), result)) {
                    return true;
                }

                current.remove(current.size() - 1);
            }

            return false;
        }
    }



    public List<Integer> solution(List<Integer> numbers) throws Exception {
        Solver solver = new Solver(numbers);
        return solver.findDwarves();
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            List<Integer> numbers = new ArrayList<>();

            for (int i = 0; i < 9; i++) {
                try {
                    int number = Integer.parseInt(br.readLine());

                    if (number < 0) {
                        throw new IllegalArgumentException("숫자는 음수가 될 수 없습니다.");
                    }

                    numbers.add(number);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("입력은 반드시 숫자여야 합니다.");
                }
            }

            Main main = new Main();
            List<Integer> result = main.solution(numbers);

            result.stream().sorted().forEach(num -> {
                try {
                    bw.write(num + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            bw.flush();
            br.close();
            bw.close();

        } catch (Exception e) {
            System.err.println("오류 발생: " + e.getMessage());
        }
    }
}