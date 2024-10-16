import java.io.*;
import java.util.*;

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
            return new Position(this.y + dy[direction], this.x + dx[direction]);
        }

        public boolean isValid(int n, int m) {
            return this.y >= 0 && this.y < n && this.x >= 0 && this.x < m;
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }
    }

    public static class MazeSolver {
        private final int[][] grid;
        private final int[][] visited;
        private final int n, m;
        private final List<Position> cheeseList;

        public MazeSolver(int[][] grid, int n, int m) {
            this.grid = grid;
            this.n = n;
            this.m = m;
            this.visited = new int[n][m];
            this.cheeseList = new ArrayList<>();
        }

        public void exploreAir(Position position) {
            visited[position.getY()][position.getX()] = 1;

            if (grid[position.getY()][position.getX()] == 1) {
                cheeseList.add(position);
                return;
            }

            for (int i = 0; i < 4; i++) {
                Position nextPosition = position.getNextPosition(i);

                if (nextPosition.isValid(n, m) && visited[nextPosition.getY()][nextPosition.getX()] == 0) {
                    exploreAir(nextPosition);
                }
            }
        }

        public int meltCheese() {
            int cheeseCountBeforeMelt = cheeseList.size();
            for (Position pos : cheeseList) {
                grid[pos.getY()][pos.getX()] = 0;
            }
            return cheeseCountBeforeMelt;
        }

        public boolean allCheeseMelted() {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == 1) {
                        return false;
                    }
                }
            }
            return true;
        }

        public int solve() {
            int rounds = 0;
            int lastCheeseCount = 0;

            while (true) {

                for (int i = 0; i < n; i++) {
                    Arrays.fill(visited[i], 0);
                }
                cheeseList.clear();

                exploreAir(new Position(0, 0));

                if (cheeseList.isEmpty()) {
                    break;
                }

                lastCheeseCount = meltCheese();
                rounds++;

                if (allCheeseMelted()) {
                    break;
                }
            }

            System.out.println(rounds);
            return lastCheeseCount;
        }
    }

    public static int solution(int[][] grid, int n, int m) {
        MazeSolver solver = new MazeSolver(grid, n, m);
        return solver.solve();
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] firstLine = br.readLine().split(" ");
            int n = Integer.parseInt(firstLine[0]);
            int m = Integer.parseInt(firstLine[1]);

            int[][] grid = new int[n][m];

            for (int i = 0; i < n; i++) {
                String[] line = br.readLine().split(" ");
                for (int j = 0; j < m; j++) {
                    grid[i][j] = Integer.parseInt(line[j]);
                }
            }

            int lastCheeseCount = solution(grid, n, m);

            bw.write(lastCheeseCount + "\n");
            bw.flush();
            br.close();
            bw.close();

        } catch (IOException e) {
            System.err.println("입출력 오류 발생: " + e.getMessage());
        }
    }
}