import java.io.*;
import java.util.*;

public class Solution {

    public static class PairSumFinder {
        private final int[] arr;
        private final int targetSum;
        private int leftPointer, rightPointer, pairCount;

        public PairSumFinder(int[] arr, int targetSum) {
            this.arr = arr;
            this.targetSum = targetSum;
            this.leftPointer = 0;
            this.rightPointer = arr.length - 1;
            this.pairCount = 0;
        }

        public int findPairCount() {
            Arrays.sort(arr);

            while (leftPointer < rightPointer) {
                int sum = arr[leftPointer] + arr[rightPointer];

                if (sum == targetSum) {
                    pairCount++;
                    rightPointer--;
                } else if (sum > targetSum) {
                    rightPointer--;
                } else {
                    leftPointer++;
                }
            }

            return pairCount;
        }
    }

    public static int solution(int n, int[] arr, int x) {
        PairSumFinder finder = new PairSumFinder(arr, x);
        return finder.findPairCount();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int x = Integer.parseInt(br.readLine());

        int result = solution(n, arr, x);

        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}