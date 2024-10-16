import java.io.*;
import java.util.*;

public class Solution {

    public static class PathValidator {
        private final int[][] grid;
        private final int size;
        private final int slopeLength;
        private int pathCount;

        public PathValidator(int[][] grid, int size, int slopeLength) {
            this.grid = grid;
            this.size = size;
            this.slopeLength = slopeLength;
            this.pathCount = 0;
        }

        public void validatePaths() {
            for (int i = 0; i < size; i++) {
                checkPath(grid[i]);
            }
        }

        public int getPathCount() {
            return pathCount;
        }

        private void checkPath(int[] path) {
            int count = 1;
            for (int j = 0; j < size - 1; j++) {
                if (path[j] == path[j + 1]) {
                    count++;
                } else if (path[j] + 1 == path[j + 1] && count >= slopeLength) {
                    count = 1;
                } else if (path[j] - 1 == path[j + 1] && count >= 0) {
                    count = -slopeLength + 1;
                } else {
                    return;
                }
            }
            if (count >= 0) {
                pathCount++;
            }
        }
    }

    public static int solution(int n, int l, int[][] grid) {
        int[][] transposedGrid = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                transposedGrid[j][i] = grid[i][j];
            }
        }

        PathValidator rowValidator = new PathValidator(grid, n, l);
        rowValidator.validatePaths();

        PathValidator columnValidator = new PathValidator(transposedGrid, n, l);
        columnValidator.validatePaths();

        return rowValidator.getPathCount() + columnValidator.getPathCount();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        int[][] grid = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = solution(n, l, grid);

        bw.write(String.valueOf(result));

        bw.flush();
        bw.close();
        br.close();
    }
}