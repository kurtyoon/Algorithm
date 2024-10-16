import java.io.*;
import java.util.*;

public class Solution {

    static class HeightCount {
        long height;
        int count;

        public HeightCount(long height, int count) {
            this.height = height;
            this.count = count;
        }
    }

    public static long solution(int n, long[] heights) {
        Stack<HeightCount> stack = new Stack<>();
        long ret = 0;

        for (int i = 0; i < n; i++) {
            long temp = heights[i];
            int cnt = 1;

            while (!stack.isEmpty() && stack.peek().height <= temp) {
                ret += stack.peek().count;
                if (stack.peek().height == temp) {
                    cnt = stack.peek().count + 1;
                } else {
                    cnt = 1;
                }
                stack.pop();
            }

            if (!stack.isEmpty()) {
                ret++;
            }

            stack.push(new HeightCount(temp, cnt));
        }

        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        long[] heights = new long[n];

        for (int i = 0; i < n; i++) {
            heights[i] = Long.parseLong(br.readLine());
        }

        long result = solution(n, heights);

        bw.write(String.valueOf(result));
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }
}