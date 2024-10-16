import java.io.*;
import java.util.*;

public class Solution {

    public static class Position {
        private final int y;
        private final int x;
        private static final int[] dy = {0, 1, 0, -1};
        private static final int[] dx = {1, 0, -1, 0};

        public Position(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public Position getNextPosition(int direction) {
            return new Position(this.y + dy[direction], this.x + dx[direction]);
        }

        public boolean isValid(int rows, int cols) {
            return y >= 0 && y < rows && x >= 0 && x < cols;
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }
    }

    public static class Water {
        private final Queue<Position> waterQueue;
        private final Queue<Position> waterTempQueue;
        private final boolean[][] visited;

        public Water(int rows, int cols, char[][] grid) {
            waterQueue = new LinkedList<>();
            waterTempQueue = new LinkedList<>();
            visited = new boolean[rows][cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == '.' || grid[i][j] == 'L') {
                        waterQueue.offer(new Position(i, j));
                        visited[i][j] = true;
                    }
                }
            }
        }

        public void clearTempQueue() {
            waterTempQueue.clear();
        }

        public void melt(char[][] grid, int rows, int cols) {
            while (!waterQueue.isEmpty()) {
                Position current = waterQueue.poll();
                for (int i = 0; i < 4; i++) {
                    Position next = current.getNextPosition(i);
                    if (next.isValid(rows, cols) && !visited[next.getY()][next.getX()]) {
                        if (grid[next.getY()][next.getX()] == 'X') {
                            visited[next.getY()][next.getX()] = true;
                            waterTempQueue.offer(next);
                            grid[next.getY()][next.getX()] = '.';
                        }
                    }
                }
            }
        }

        public Queue<Position> getTempQueue() {
            return waterTempQueue;
        }

        public Queue<Position> getQueue() {
            return waterQueue;
        }
    }

    public static class Swan {
        private final Queue<Position> swanQueue;
        private final Queue<Position> swanTempQueue;
        private final boolean[][] visitedSwan;

        public Swan(Position start, int rows, int cols) {
            swanQueue = new LinkedList<>();
            swanTempQueue = new LinkedList<>();
            visitedSwan = new boolean[rows][cols];

            swanQueue.offer(start);
            visitedSwan[start.getY()][start.getX()] = true;
        }

        public void clearTempQueue() {
            swanTempQueue.clear();
        }

        public boolean move(char[][] grid, int rows, int cols) {
            while (!swanQueue.isEmpty()) {
                Position current = swanQueue.poll();
                for (int i = 0; i < 4; i++) {
                    Position next = current.getNextPosition(i);
                    if (next.isValid(rows, cols) && !visitedSwan[next.getY()][next.getX()]) {
                        visitedSwan[next.getY()][next.getX()] = true;
                        if (grid[next.getY()][next.getX()] == '.') {
                            swanQueue.offer(next);
                        } else if (grid[next.getY()][next.getX()] == 'X') {
                            swanTempQueue.offer(next);
                        } else if (grid[next.getY()][next.getX()] == 'L') {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        public Queue<Position> getTempQueue() {
            return swanTempQueue;
        }

        public Queue<Position> getQueue() {
            return swanQueue;
        }
    }

    public static int solution(int rows, int cols, char[][] grid, Position swanStart) {
        Water water = new Water(rows, cols, grid);
        Swan swan = new Swan(swanStart, rows, cols);
        int day = 0;

        while (true) {
            if (swan.move(grid, rows, cols)) break;
            water.melt(grid, rows, cols);

            water.getQueue().addAll(water.getTempQueue());
            swan.getQueue().addAll(swan.getTempQueue());
            water.clearTempQueue();
            swan.clearTempQueue();

            day++;
        }

        return day;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        char[][] grid = new char[R][C];
        Position swanStart = null;

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                grid[i][j] = line.charAt(j);
                if (grid[i][j] == 'L') {
                    swanStart = new Position(i, j);
                }
            }
        }

        int result = solution(R, C, grid, swanStart);
        bw.write(result + "\n");
        bw.flush();
        br.close();
        bw.close();
    }
}
