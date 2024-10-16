import java.io.*;
import java.util.*;

public class Solution{

    public static class Coordinate {
        private final int y;
        private final int x;

        private static final int[] dy = {-1, 0, 1, 0};
        private static final int[] dx = {0, 1, 0, -1};

        public Coordinate(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public Coordinate getNextCoordinate(int direction) {
            return new Coordinate(this.y + dy[direction], this.x + dx[direction]);
        }

        public boolean isWithinBounds(int rows, int cols) {
            return y >= 0 && y < rows && x >= 0 && x < cols;
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }
    }

    public static class TreasureMapSolver {
        private final char[][] map;
        private final int rows, cols;
        private int maxDistance;

        public TreasureMapSolver(char[][] map, int rows, int cols) {
            this.map = map;
            this.rows = rows;
            this.cols = cols;
            this.maxDistance = 0;
        }

        public void calculateMaxDistanceFrom(Coordinate startCoordinate) {
            int[][] visited = new int[rows][cols];
            visited[startCoordinate.getY()][startCoordinate.getX()] = 1;

            Queue<Coordinate> queue = new LinkedList<>();
            queue.offer(startCoordinate);

            while (!queue.isEmpty()) {
                Coordinate current = queue.poll();
                for (int i = 0; i < 4; i++) {
                    Coordinate nextCoordinate = current.getNextCoordinate(i);
                    if (nextCoordinate.isWithinBounds(rows, cols) && visited[nextCoordinate.getY()][nextCoordinate.getX()] == 0
                        && map[nextCoordinate.getY()][nextCoordinate.getX()] == 'L') {
                        visited[nextCoordinate.getY()][nextCoordinate.getX()] = visited[current.getY()][current.getX()] + 1;
                        queue.offer(nextCoordinate);
                        maxDistance = Math.max(maxDistance, visited[nextCoordinate.getY()][nextCoordinate.getX()]);
                    }
                }
            }
        }

        public int getMaxDistance() {
            return maxDistance - 1;
        }
    }

    public static int findMaxTreasureDistance(int n, int m, char[][] map) {
        TreasureMapSolver solver = new TreasureMapSolver(map, n, m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 'L') {
                    solver.calculateMaxDistanceFrom(new Coordinate(i, j));
                }
            }
        }
        return solver.getMaxDistance();
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] firstLine = br.readLine().split(" ");
            int n = Integer.parseInt(firstLine[0]);
            int m = Integer.parseInt(firstLine[1]);

            char[][] map = new char[n][m];

            for (int i = 0; i < n; i++) {
                map[i] = br.readLine().toCharArray();
            }

            int result = findMaxTreasureDistance(n, m, map);
            bw.write(result + "\n");

            bw.flush();
            br.close();
            bw.close();

        } catch (IOException e) {
            System.err.println("입출력 오류 발생: " + e.getMessage());
        }
    }
}