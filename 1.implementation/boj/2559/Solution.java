import java.io.*;

public class Solution {

    public static class SumCalculator {
        private final int[] psum;
        private final int n, k;

        public SumCalculator(int n, int k) {
            this.n = n;
            this.k = k;
            this.psum = new int[n + 1];
        }

        public void calculatePrefixSum(int[] arr) {
            for (int i = 1; i <= n; i++) {
                psum[i] = psum[i - 1] + arr[i - 1];
            }
        }

        public int findMaxSum() {
            int maxSum = Integer.MIN_VALUE;
            for (int i = k; i <= n; i++) {
                maxSum = Math.max(maxSum, psum[i] - psum[i - k]);
            }
            return maxSum;
        }
    }

    public int solution(int n, int k, int[] arr) {
        SumCalculator calculator = new SumCalculator(n, k);
        calculator.calculatePrefixSum(arr);
        return calculator.findMaxSum();
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] firstLine = br.readLine().split(" ");
            int n = Integer.parseInt(firstLine[0]);
            int k = Integer.parseInt(firstLine[1]);

            int[] arr = new int[n];
            String[] secondLine = br.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(secondLine[i]);
            }

            Main main = new Main();
            int result = main.solution(n, k, arr);

            bw.write(result + "\n");

            bw.flush();
            br.close();
            bw.close();

        } catch (IOException e) {
            System.err.println("오류 발생: " + e.getMessage());
        }
    }
}