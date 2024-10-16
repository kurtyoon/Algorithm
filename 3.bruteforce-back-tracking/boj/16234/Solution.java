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
            return this.y >= 0 && this.y < n && this.x >= 0 && this.x < n;
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }
    }

    public static class PopulationMovementSimulation {
        private final int[][] populationGrid;
        private final int n;
        private final int l;
        private final int r;
        private final int[][] visited;
        private List<Position> union;
        private int unionSum;

        public PopulationMovementSimulation(int[][] populationGrid, int n, int l, int r) {
            this.populationGrid = populationGrid;
            this.n = n;
            this.l = l;
            this.r = r;
            this.visited = new int[n][n];
        }

        public void exploreUnion(Position startPosition) {
            Queue<Position> queue = new LinkedList<>();
            queue.add(startPosition);
            visited[startPosition.getY()][startPosition.getX()] = 1;

            union = new ArrayList<>();
            union.add(startPosition);
            unionSum = populationGrid[startPosition.getY()][startPosition.getX()];

            while (!queue.isEmpty()) {
                Position currentPosition = queue.poll();
                for (int i = 0; i < 4; i++) {
                    Position nextPosition = currentPosition.getNextPosition(i);
                    if (nextPosition.isValid(n) && visited[nextPosition.getY()][nextPosition.getX()] == 0 &&
                        Math.abs(populationGrid[nextPosition.getY()][nextPosition.getX()] - populationGrid[currentPosition.getY()][currentPosition.getX()]) >= l &&
                        Math.abs(populationGrid[nextPosition.getY()][nextPosition.getX()] - populationGrid[currentPosition.getY()][currentPosition.getX()]) <= r) {

                        queue.add(nextPosition);
                        visited[nextPosition.getY()][nextPosition.getX()] = 1;
                        union.add(nextPosition);
                        unionSum += populationGrid[nextPosition.getY()][nextPosition.getX()];
                    }
                }
            }
        }

        public int updatePopulation() {
            int movementCount = 0;
            while (true) {
                boolean moved = false;
                for (int i = 0; i < n; i++) {
                    Arrays.fill(visited[i], 0);
                }

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (visited[i][j] == 0) {
                            Position startPosition = new Position(i, j);
                            exploreUnion(startPosition);

                            if (union.size() > 1) {
                                int newPopulation = unionSum / union.size();
                                for (Position pos : union) {
                                    populationGrid[pos.getY()][pos.getX()] = newPopulation;
                                }
                                moved = true;
                            }
                        }
                    }
                }

                if (!moved) {
                    break;
                }
                movementCount++;
            }
            return movementCount;
        }
    }

    public static int solution(int n, int l, int r, int[][] grid) {
        PopulationMovementSimulation simulation = new PopulationMovementSimulation(grid, n, l, r);
        return simulation.updatePopulation();
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] firstLine = br.readLine().split(" ");
            int n = Integer.parseInt(firstLine[0]);
            int l = Integer.parseInt(firstLine[1]);
            int r = Integer.parseInt(firstLine[2]);

            int[][] grid = new int[n][n];
            for (int i = 0; i < n; i++) {
                String[] row = br.readLine().split(" ");
                for (int j = 0; j < n; j++) {
                    grid[i][j] = Integer.parseInt(row[j]);
                }
            }

            int result = solution(n, l, r, grid);
            bw.write(result + "\n");

            bw.flush();
            br.close();
            bw.close();

        } catch (IOException e) {
            System.err.println("입출력 오류 발생: " + e.getMessage());
        }
    }
}