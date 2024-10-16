import java.io.*;

public class Solution {

    public static class PrimeSumCounter {
        private final boolean[] isComposite;
        private final int[] primes;
        private final int n;
        private int primeCount;

        public PrimeSumCounter(int n) {
            this.n = n;
            this.isComposite = new boolean[n + 1];
            this.primes = new int[n + 1];
            this.primeCount = 0;
        }

        private void sieve() {
            for (int i = 2; i <= n; i++) {
                if (isComposite[i]) continue;
                primes[primeCount++] = i;
                for (int j = 2 * i; j <= n; j += i) {
                    isComposite[j] = true;
                }
            }
        }

        public int countPrimeSums() {
            sieve();
            int lo = 0, hi = 0, sum = 0, result = 0;

            while (true) {
                if (sum >= n) {
                    sum -= primes[lo++];
                } else if (hi == primeCount) {
                    break;
                } else {
                    sum += primes[hi++];
                }

                if (sum == n) {
                    result++;
                }
            }

            return result;
        }
    }

    public static int solution(int n) {
        PrimeSumCounter counter = new PrimeSumCounter(n);
        return counter.countPrimeSums();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int result = solution(n);

        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}