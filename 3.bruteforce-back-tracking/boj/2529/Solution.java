import java.io.*;
import java.util.*;

public class Solution {

    public static class InequalitySolver {
        private int n;
        private char[] operators;
        private boolean[] usedNumbers;
        private List<String> results;

        public InequalitySolver(int n, char[] operators) {
            this.n = n;
            this.operators = operators;
            this.usedNumbers = new boolean[10];
            this.results = new ArrayList<>();
        }

        private boolean isValid(char x, char y, char op) {
            if (op == '<' && x < y) return true;
            if (op == '>' && x > y) return true;
            return false;
        }

        private void findSequences(int index, String num) {
            if (index == n + 1) {
                results.add(num);
                return;
            }

            for (int i = 0; i <= 9; i++) {
                if (usedNumbers[i]) continue;
                if (index == 0 || isValid(num.charAt(index - 1), (char) (i + '0'), operators[index - 1])) {
                    usedNumbers[i] = true;
                    findSequences(index + 1, num + i);
                    usedNumbers[i] = false;
                }
            }
        }

        public String[] solve() {
            findSequences(0, "");
            Collections.sort(results);
            String max = results.get(results.size() - 1);
            String min = results.get(0);
            return new String[]{max, min};
        }
    }

    public static String[] solution(int n, char[] operators) {
        InequalitySolver solver = new InequalitySolver(n, operators);
        return solver.solve();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        char[] operators = new char[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            operators[i] = st.nextToken().charAt(0);
        }

        String[] result = solution(n, operators);

        bw.write(result[0] + "\n");
        bw.write(result[1] + "\n");

        bw.flush();
        br.close();
        bw.close();
    }
}
