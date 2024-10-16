import java.io.*;
import java.util.Stack;

public class Solution {

    public static class Solution {

        private int[] array;
        private int[] result;

        public Solution(int[] array) {
            this.array = array;
            this.result = new int[array.length];
        }

        public int[] findNextGreaterElements() {
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < result.length; i++) {
                result[i] = -1;
            }

            for (int i = 0; i < array.length; i++) {
                while (!stack.isEmpty() && array[stack.peek()] < array[i]) {
                    result[stack.pop()] = array[i];
                }
                stack.push(i);
            }

            return result;
        }
    }

    public static int[] solution(int[] array) {
        Solution solver = new Solution(array);
        return solver.findNextGreaterElements();
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            int n = Integer.parseInt(br.readLine());
            int[] array = new int[n];
            String[] input = br.readLine().split(" ");

            for (int i = 0; i < n; i++) {
                array[i] = Integer.parseInt(input[i]);
            }

            int[] result = solution(array);
      
            for (int value : result) {
                bw.write(value + " ");
            }
            bw.write("\n");

            bw.flush();
            br.close();
            bw.close();

        } catch (IOException e) {
            System.err.println("입출력 오류 발생: " + e.getMessage());
        }
    }
}