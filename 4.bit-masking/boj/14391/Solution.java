import java.io.*;
import java.util.StringTokenizer;

public class Solution {

    public static class PaperCutting {
        private final int[][] grid;
        private final int rows, cols;
        private int maxSum = 0;

        public PaperCutting(int rows, int cols, int[][] grid) {
            this.grid = grid;
            this.rows = rows;
            this.cols = cols;
        }

        public void calculateMaxSum() {
            for (int s = 0; s < (1 << (rows * cols)); s++) {
                int sum = 0;

                for (int i = 0; i < rows; i++) {
                    int cur = 0;
                    for (int j = 0; j < cols; j++) {
                        int k = i * cols + j;
                        if ((s & (1 << k)) == 0) {
                            cur = cur * 10 + grid[i][j];
                        } else {
                            sum += cur;
                            cur = 0;
                        }
                    }
                    sum += cur;
                }

                for (int j = 0; j < cols; j++) {
                    int cur = 0;
                    for (int i = 0; i < rows; i++) {
                        int k = i * cols + j;
                        if ((s & (1 << k)) != 0) {
                            cur = cur * 10 + grid[i][j];
                        } else {
                            sum += cur;
                            cur = 0;
                        }
                    }
                    sum += cur;
                }

                maxSum = Math.max(maxSum, sum);
            }
        }

        public int getMaxSum() {
            return maxSum;
        }
    }

    public static int solution(int n, int m, int[][] grid) throws IOException {
        PaperCutting paperCutting = new PaperCutting(n, m, grid);
        paperCutting.calculateMaxSum();

        return paperCutting.getMaxSum();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] grid = new int[n][m];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                grid[i][j] = line.charAt(j) - '0';
            }
        }

        int result = solution(n, m, grid);

        bw.write(String.valueOf(result));

        bw.flush();
        br.close();
        bw.close();
    }
}