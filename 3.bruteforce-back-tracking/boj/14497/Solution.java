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
            return y >= 0 && y < n && x >= 0 && x < m;
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }
    }

    public static int solution(int n, int m, Position start, Position end, char[][] grid) {
        Queue<Position> queue = new LinkedList<>();
        int[][] visited = new int[n][m];
        queue.add(start);
        visited[start.getY()][start.getX()] = 1;

        int steps = 0;
        while (grid[end.getY()][end.getX()] != '0') {
            steps++;
            Queue<Position> tempQueue = new LinkedList<>();
            while (!queue.isEmpty()) {
                Position current = queue.poll();
                for (int i = 0; i < 4; i++) {
                    Position next = current.getNextPosition(i);
                    if (next.isValid(n, m) && visited[next.getY()][next.getX()] == 0) {
                        visited[next.getY()][next.getX()] = steps;
                        if (grid[next.getY()][next.getX()] != '0') {
                            grid[next.getY()][next.getX()] = '0';
                            tempQueue.add(next);
                        } else {
                            queue.add(next);
                        }
                    }
                }
            }
            queue = tempQueue;
        }

        return visited[end.getY()][end.getX()];
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String[] firstLine = br.readLine().split(" ");
            int n = Integer.parseInt(firstLine[0]);
            int m = Integer.parseInt(firstLine[1]);

            String[] secondLine = br.readLine().split(" ");
            int y1 = Integer.parseInt(secondLine[0]) - 1;
            int x1 = Integer.parseInt(secondLine[1]) - 1;
            int y2 = Integer.parseInt(secondLine[2]) - 1;
            int x2 = Integer.parseInt(secondLine[3]) - 1;

            char[][] grid = new char[n][m];
            for (int i = 0; i < n; i++) {
                grid[i] = br.readLine().toCharArray();
            }

            Position start = new Position(y1, x1);
            Position end = new Position(y2, x2);

            int result = solution(n, m, start, end, grid);
            bw.write(result + "\n");
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}