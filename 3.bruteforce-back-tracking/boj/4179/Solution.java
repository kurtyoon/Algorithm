import java.io.*;
import java.util.*;

public class Solution {

    public static class Position {
        private final int y;
        private final int x;

        private static final int[] dy = {-1, 0, 1, 0};
        private static final int[] dx = {0, -1, 0, 1};

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

    public static class FireEscapeSimulation {

        private static final int INF = 987654321;
        private final char[][] grid;
        private final int[][] fireSpreadTime;
        private final int[][] personEscapeTime;
        private final int rows;
        private final int cols;

        private Position personStart;

        public FireEscapeSimulation(int rows, int cols, char[][] grid) {
            this.rows = rows;
            this.cols = cols;
            this.grid = grid;
            this.fireSpreadTime = new int[rows  + 1][cols + 1];
            this.personEscapeTime = new int[rows + 1][cols + 1];

            for (int i = 0; i <= rows; i++) {
                Arrays.fill(fireSpreadTime[i], INF);
            }
        }

        public void simulateFireSpread(Queue<Position> fireQueue) {
            while (!fireQueue.isEmpty()) {
                Position currentPosition = fireQueue.poll();

                int currentY = currentPosition.getY();
                int currentX = currentPosition.getX();

                for (int i = 0; i < 4; i++) {
                    Position nextPosition = currentPosition.getNextPosition(i);

                    if (nextPosition.isValid(rows, cols) && fireSpreadTime[nextPosition.getY()][nextPosition.getX()] == INF && grid[nextPosition.getY()][nextPosition.getX()] != '#') {
                        fireSpreadTime[nextPosition.getY()][nextPosition.getX()] = fireSpreadTime[currentY][currentX] + 1;
                        fireQueue.add(nextPosition);
                    }
                }
            }
        }

        public int simulatePersonEscape() {
            Queue<Position> queue = new LinkedList<>();
            personEscapeTime[personStart.getY()][personStart.getX()] = 1;
            queue.add(personStart);

            while (!queue.isEmpty()) {
                Position currentPosition = queue.poll();

                int currentY = currentPosition.getY();
                int currentX = currentPosition.getX();

                if (currentY == 1 || currentY == rows || currentX == 1 || currentX == cols) {
                    return personEscapeTime[currentY][currentX];
                }

                for (int i = 0; i < 4; i++) {
                    Position nextPosition = currentPosition.getNextPosition(i);

                    if (nextPosition.isValid(rows, cols) && personEscapeTime[nextPosition.getY()][nextPosition.getX()] == 0 && grid[nextPosition.getY()][nextPosition.getX()] != '#') {
                        if (fireSpreadTime[nextPosition.getY()][nextPosition.getX()] > personEscapeTime[nextPosition.getY()][nextPosition.getX()]) {
                            personEscapeTime[nextPosition.getY()][nextPosition.getX()] = personEscapeTime[currentY][currentX] + 1;
                            queue.add(nextPosition);
                        }
                    }
                }
            }

            return 0;
        }

        public void setPersonStart(Position personStart) {
            this.personStart = personStart;
        }
    }

    public static String solution(int rows, int cols, char[][] grid) {

        Queue<Position> fireQueue = new LinkedList<>();
        Position startPosition = null;

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (grid[i][j] == 'F') {
                    fireQueue.add(new Position(i, j));
                } else if (grid[i][j] == 'J') {
                    startPosition = new Position(i, j);
                }
            }
        }

        FireEscapeSimulation simulation = new FireEscapeSimulation(rows, cols, grid);

        simulation.simulateFireSpread(fireQueue);
        simulation.setPersonStart(startPosition);

        int result = simulation.simulatePersonEscape();

        return result == 0 ? "IMPOSSIBLE" : String.valueOf(result);
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] firstLine = br.readLine().split(" ");
            int n = Integer.parseInt(firstLine[0]);
            int m = Integer.parseInt(firstLine[1]);

            char[][] grid = new char[n][m];

            for (int i = 0; i < n; i++) {
                String line = br.readLine();
                for (int j = 0; j < m; j++) {
                    grid[i][j] = line.charAt(j);
                }
            }

            String result = solution(n, m, grid);
            bw.write(result + "\n");

            bw.flush();
            br.close();
            bw.close();

        } catch (IOException e) {
            System.err.println("입출력 오류 발생: " + e.getMessage());
        }
    }
}