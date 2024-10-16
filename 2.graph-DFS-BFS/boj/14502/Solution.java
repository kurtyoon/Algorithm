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

        public boolean isValidPosition(int rows, int cols) {
            return y >= 0 && y < rows && x >= 0 && x < cols;
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }
    }

    public static class VirusSpreadSimulation {
        private final int[][] grid;
        private final int n, m;
        private final List<Position> virusPositions;
        private final List<Position> wallPositions;

        public VirusSpreadSimulation(int[][] grid, List<Position> virusPositions, List<Position> wallPositions) {
            this.grid = grid;
            this.n = grid.length;
            this.m = grid[0].length;
            this.virusPositions = virusPositions;
            this.wallPositions = wallPositions;
        }

        private void spreadVirus(int[][] visited, Position pos) {
            visited[pos.getY()][pos.getX()] = 1; // 현재 위치를 방문 처리

            for (int i = 0; i < 4; i++) {
                Position nextPos = pos.getNextPosition(i);

                if (nextPos.isValidPosition(n, m) && visited[nextPos.getY()][nextPos.getX()] == 0 && grid[nextPos.getY()][nextPos.getX()] == 0) {
                    spreadVirus(visited, nextPos); // 재귀적으로 주변에 바이러스를 퍼뜨림
                }
            }
        }

        public int calculateSafeZone() {
            int[][] visited = new int[n][m];

            for (Position virus : virusPositions) {
                spreadVirus(visited, virus);
            }

            int safeZone = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == 0 && visited[i][j] == 0) {
                        safeZone++;
                    }
                }
            }

            return safeZone;
        }
    }

    public static int solution(int n, int m, int[][] grid) {
        List<Position> virusList = new ArrayList<>();
        List<Position> wallList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    virusList.add(new Position(i, j));
                } else if (grid[i][j] == 0) {
                    wallList.add(new Position(i, j));
                }
            }
        }

        int maxSafeZone = 0;

        for (int i = 0; i < wallList.size(); i++) {
            for (int j = i + 1; j < wallList.size(); j++) {
                for (int k = j + 1; k < wallList.size(); k++) {

                    grid[wallList.get(i).getY()][wallList.get(i).getX()] = 1;
                    grid[wallList.get(j).getY()][wallList.get(j).getX()] = 1;
                    grid[wallList.get(k).getY()][wallList.get(k).getX()] = 1;

                    VirusSpreadSimulation simulation = new VirusSpreadSimulation(grid, virusList, wallList);
                    maxSafeZone = Math.max(maxSafeZone, simulation.calculateSafeZone());

                    grid[wallList.get(i).getY()][wallList.get(i).getX()] = 0;
                    grid[wallList.get(j).getY()][wallList.get(j).getX()] = 0;
                    grid[wallList.get(k).getY()][wallList.get(k).getX()] = 0;
                }
            }
        }

        return maxSafeZone;
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

            int result = solution(n, m, grid);
            bw.write(result + "\n");

            bw.flush();
            br.close();
            bw.close();

        } catch (IOException e) {
            System.err.println("입출력 오류 발생: " + e.getMessage());
        }
    }
}