import java.io.*;
import java.util.*;

public class Solution {

    public static class NonRepeatingSubarrayCounter {
        private final long[] arr;
        private final long[] count;
        private final int n;
        private long start, end, result;

        public NonRepeatingSubarrayCounter(long[] arr, int n) {
            this.arr = arr;
            this.n = n;
            this.count = new long[100001];
            this.start = 0;
            this.end = 0;
            this.result = 0;
        }

        public long countSubarrays() {
            while (end < n) {
                if (count[(int) arr[(int) end]] == 0) {
                    count[(int) arr[(int) end]]++;
                    end++;
                } else {
                    result += (end - start);
                    count[(int) arr[(int) start]]--;
                    start++;
                }
            }
            result += (long) (end - start) * (end - start + 1) / 2;
            return result;
        }
    }

    public static long solution(int n, long[] arr) {
        NonRepeatingSubarrayCounter counter = new NonRepeatingSubarrayCounter(arr, n);
        return counter.countSubarrays();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        long[] arr = new long[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            if (st.hasMoreTokens()) {
                arr[i] = Long.parseLong(st.nextToken());
            }
        }

        long result = solution(n, arr);

        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}