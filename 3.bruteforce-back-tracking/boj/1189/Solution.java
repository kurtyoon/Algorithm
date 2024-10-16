import java.util.Scanner;

public class Solution {

    public static class Position {
        private final int y;
        private final int x;
        private static final int[] dy = {-1, 0, 1, 0};
        private static final int[] dx = {0, 1, 0, -1};

        public Position(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public Position getNextPosition(int direction) {
            return new Position(y + dy[direction], x + dx[direction]);
        }

        public boolean isValid(int n, int m, boolean[][] visited, char[][] grid) {
            return y >= 0 && x >= 0 && y < n && x < m && !visited[y][x] && grid[y][x] != 'T';
        }

        public boolean isEnd(int m) {
            return y == 0 && x == m - 1;
        }
    }

    public static class MazeSolver {
        private final int n;
        private final int m;
        private final int k;
        private final boolean[][] visited;
        private final char[][] grid;

        public MazeSolver(int n, int m, int k, char[][] grid) {
            this.n = n;
            this.m = m;
            this.k = k;
            this.grid = grid;
            this.visited = new boolean[n][m];
        }

        public int findPaths(Position pos, int steps) {
            if (pos.isEnd(m)) {
                return steps == k ? 1 : 0;
            }

            int totalPaths = 0;
            for (int i = 0; i < 4; i++) {
                Position nextPos = pos.getNextPosition(i);
                if (nextPos.isValid(n, m, visited, grid)) {
                    visited[nextPos.y][nextPos.x] = true;
                    totalPaths += findPaths(nextPos, steps + 1);
                    visited[nextPos.y][nextPos.x] = false;
                }
            }

            return totalPaths;
        }
    }

    public static int solution(int n, int m, int k, char[][] grid) {
        MazeSolver solver = new MazeSolver(n, m, k, grid);
        Position start = new Position(n - 1, 0);
        solver.visited[start.y][start.x] = true;
        return solver.findPaths(start, 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        char[][] grid = new char[n][m];

        for (int i = 0; i < n; i++) {
            String row = sc.next();
            grid[i] = row.toCharArray();
        }

        System.out.println(solution(n, m, k, grid));

        sc.close();
    }
}
