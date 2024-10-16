import java.io.*;
import java.util.*;

public class Solution {

    public static class NumberExtractor {
        private final List<String> numbers;
        private String currentNumber;

        public NumberExtractor() {
            this.numbers = new ArrayList<>();
            this.currentNumber = "";
        }

        private void cleanAndAddNumber() {
            while (currentNumber.length() > 0 && currentNumber.charAt(0) == '0') {
                currentNumber = currentNumber.substring(1);
            }
            if (currentNumber.length() == 0) {
                currentNumber = "0";
            }
            numbers.add(currentNumber);
            currentNumber = "";
        }

        public void extractNumbers(String s) {
            currentNumber = "";
            for (int j = 0; j < s.length(); j++) {
                if (Character.isDigit(s.charAt(j))) {
                    currentNumber += s.charAt(j);
                } else if (!currentNumber.isEmpty()) {
                    cleanAndAddNumber();
                }
            }
            if (!currentNumber.isEmpty()) {
                cleanAndAddNumber();
            }
        }
 
        public List<String> getSortedNumbers() {
            Collections.sort(numbers, (a, b) -> {
                if (a.length() == b.length()) {
                    return a.compareTo(b);
                }
                return Integer.compare(a.length(), b.length());
            });
            return numbers;
        }
    }

    public void solution(int n, String[] inputs, BufferedWriter bw) throws IOException {
        NumberExtractor extractor = new NumberExtractor();

        for (int i = 0; i < n; i++) {
            extractor.extractNumbers(inputs[i]);
        }

        List<String> sortedNumbers = extractor.getSortedNumbers();

        for (String number : sortedNumbers) {
            bw.write(number + "\n");
        }
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            int n = Integer.parseInt(br.readLine());
            String[] inputs = new String[n];
            for (int i = 0; i < n; i++) {
                inputs[i] = br.readLine();
            }

            Solution solution = new Solution();
            solution.solution(n, inputs, bw);

            bw.flush();
            br.close();
            bw.close();

        } catch (IOException e) {
            System.err.println("입출력 오류 발생: " + e.getMessage());
        }
    }
}