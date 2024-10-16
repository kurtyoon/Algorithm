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

        public boolean isValid(int n) {
            return y >= 0 && y < n && x >= 0 && x < n;
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }
    }

    public static class Field {
        private final int[][] area;
        private final int n;
        private final boolean[][] visited;

        public Field(int n, int[][] area) {
            this.n = n;
            this.area = area;
            this.visited = new boolean[n][n];
        }

        private void exploreRegion(Position start, int waterLevel) {
            Stack<Position> stack = new Stack<>();
            stack.push(start);
            visited[start.getY()][start.getX()] = true;

            while (!stack.isEmpty()) {
                Position current = stack.pop();

                for (int i = 0; i < 4; i++) {
                    Position next = current.getNextPosition(i);
                    if (next.isValid(n) && !visited[next.getY()][next.getX()] && area[next.getY()][next.getX()] > waterLevel) {
                        visited[next.getY()][next.getX()] = true;
                        stack.push(next);
                    }
                }
            }
        }

        public int countSafeRegions(int waterLevel) {
            int regionCount = 0;

            for (int i = 0; i < n; i++) {
                Arrays.fill(visited[i], false);
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j] && area[i][j] > waterLevel) {
                        exploreRegion(new Position(i, j), waterLevel);
                        regionCount++;
                    }
                }
            }
            return regionCount;
        }
    }

    public int solution(int[][] area, int n) {
        int maxSafeRegions = 1;
        Field field = new Field(n, area);

        for (int waterLevel = 1; waterLevel <= 100; waterLevel++) {
            int safeRegions = field.countSafeRegions(waterLevel);
            maxSafeRegions = Math.max(maxSafeRegions, safeRegions);
        }

        return maxSafeRegions;
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            int n = Integer.parseInt(br.readLine());
            int[][] area = new int[n][n];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    area[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            Main main = new Main();
            int result = main.solution(area, n);

            bw.write(result + "\n");
            bw.flush();

        } catch (IOException e) {
            System.err.println("입출력 오류 발생: " + e.getMessage());
        }
    }
}