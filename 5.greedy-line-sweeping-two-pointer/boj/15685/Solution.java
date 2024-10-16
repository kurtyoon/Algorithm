import java.io.*;
import java.util.*;

public class Main {

    public static class Position {
        private final int y;
        private final int x;

        private final int[] dy = {1, -1, 0, 0};
        private final int[] dx = {0, 0, 1, -1};

        public Position(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public int getx() {
            return x;
        }

        public Position getNextPosition(int direction) {
            return new Position(this.y + dy[direction], this.x + dx[direction]);
        }
    }

    public static class Grid {
        private final int[][] grid;
        private int completeSquareCount;

        public Grid() {
            this.grid = new int[101][101];
            this.completeSquareCount = 0;
        }

        public int getCompleteSquareCount() {
            return this.completeSquareCount;
        }

        public void drawDragonCurve(Position startPosition, List<Integer> directions) {
            Position currentPosition = startPosition;

            markPosition(currentPosition);

            for (int direction : directions) {
                Position nextPosition = currentPosition.getNextPosition(direction);
                markPosition(nextPosition);
            }
        }

        public void countCompleteSquares() {
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    if (grid[i][j] == 1 && grid[i + 1][j] == 1 && grid[i][j + 1] == 1 && grid[i + 1][j + 1] == 1) {
                        completeSquareCount++;
                    }
                }
            }
        }

        public void markPosition(Position currentPosition) {
            grid[currentPosition.getY()][currentPosition.getx()] = 1;
        }

    }

    public static class DragonCurve {
        private List<Integer> directions;

        public DragonCurve(int d, int g) {
            directions = new ArrayList<>();
            generateDirections(d, g);
        }

        private void generateDirections(int d, int g) {
            directions.add(d);
            for (int i = 1; i <= g; i++) {
                List<Integer> newDirections = new ArrayList<>(directions);
                Collections.reverse(newDirections);
                for (int j = 0; j < newDirections.size(); j++) {
                    newDirections.set(j, (newDirections.get(j) + 1) % 4);
                }
                directions.addAll(newDirections);
            }
        }

        public List<Integer> getDirections() {
            return directions;
        }
    }

    public static int solution(int n, int[][] dragonCurves) {
        Grid grid = new Grid();

        for (int i = 0; i < n; i++) {
            int x = dragonCurves[i][0];
            int y = dragonCurves[i][1];
            int d = dragonCurves[i][2];
            int g = dragonCurves[i][3];

            Position startPosition = new Position(y, x);
            DragonCurve dragonCurve = new DragonCurve(d, g);

            grid.drawDragonCurve(startPosition, dragonCurve.getDirections());
        }

        grid.countCompleteSquares();

        return grid.getCompleteSquareCount();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] dragonCurves = new int[n][4];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            dragonCurves[i][0] = Integer.parseInt(st.nextToken());
            dragonCurves[i][2] = Integer.parseInt(st.nextToken());
            dragonCurves[i][3] = Integer.parseInt(st.nextToken());
            dragonCurves[i][4] = Integer.parseInt(st.nextToken());
        }

        int result = solution(n, dragonCurves);

        bw.write(String.valueOf(result) + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}