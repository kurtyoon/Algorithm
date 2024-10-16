import java.io.*;
import java.util.*;

public class Solution {

    public static class Operation {
        private final int row;
        private final int col;
        private final int range;

        public Operation(int row, int col, int range) {
            this.row = row;
            this.col = col;
            this.range = range;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public int getRange() {
            return range;
        }
    }

    public static class ArrayRotator {
        private final int[][] grid;
        private final int n;
        private final int m;
        private final List<Operation> operations;

        private final int[] dy = {0, 1, 0, -1};
        private final int[] dx = {1, 0, -1, 0};

        public ArrayRotator(int[][] grid, List<Operation> operations, int n, int m) {
            this.grid = grid;
            this.operations = operations;
            this.n = n;
            this.m = m;
        }

        private List<int[]> generatePath(int sy, int sx, int ey, int ex) {
            List<int[]> path = new ArrayList<>();
            int dir = 0;
            int y = sy;
            int x = sx;

            while (true) {
                path.add(new int[]{y, x});
                int ny = y + dy[dir];
                int nx = x + dx[dir];

                if (ny == sy && nx == sx) break;
                if (ny < sy || ny > ey || nx < sx || nx > ex) {
                    dir++;
                    ny = y + dy[dir];
                    nx = x + dx[dir];
                }
                if (ny == sy && nx == sx) break;

                y = ny;
                x = nx;
            }
            return path;
        }

        private void rotateSubArray(int[][] copy, int row, int col, int range) {
            for (int r = 1; r <= range; r++) {
                int sy = row - r;
                int sx = col - r;
                int ey = row + r;
                int ex = col + r;

                List<int[]> path = generatePath(sy, sx, ey, ex);

                List<Integer> values = new ArrayList<>();
                for (int[] pos : path) {
                    values.add(copy[pos[0]][pos[1]]);
                }

                Collections.rotate(values, 1);

                for (int i = 0; i < path.size(); i++) {
                    int[] pos = path.get(i);
                    copy[pos[0]][pos[1]] = values.get(i);
                }
            }
        }

        public int solve(List<Integer> opOrder) {
            int[][] copy = new int[n][m];
            for (int i = 0; i < n; i++) {
                System.arraycopy(grid[i], 0, copy[i], 0, m);
            }

            for (int idx : opOrder) {
                Operation op = operations.get(idx);
                rotateSubArray(copy, op.getRow(), op.getCol(), op.getRange());
            }

            int minSum = Integer.MAX_VALUE;
            for (int[] row : copy) {
                int sum = 0;
                for (int val : row) {
                    sum += val;
                }
                minSum = Math.min(minSum, sum);
            }

            return minSum;
        }
    }

    public static int solution(int n, int m, int k, int[][] grid, List<Operation> operations) {
        ArrayRotator rotator = new ArrayRotator(grid, operations, n, m);
        List<Integer> opOrder = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            opOrder.add(i);
        }

        int minResult = Integer.MAX_VALUE;
        do {
            minResult = Math.min(minResult, rotator.solve(opOrder));
        } while (nextPermutation(opOrder));

        return minResult;
    }

    private static boolean nextPermutation(List<Integer> array) {
        int i = array.size() - 1;
        while (i > 0 && array.get(i - 1) >= array.get(i)) i--;
        if (i == 0) return false;

        int j = array.size() - 1;
        while (array.get(i - 1) >= array.get(j)) j--;

        Collections.swap(array, i - 1, j);
        Collections.reverse(array.subList(i, array.size()));
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        List<Operation> operations = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            operations.add(new Operation(r, c, s));
        }

        int result = solution(n, m, k, grid, operations);

        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}