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

        public boolean isValid(int cols, int rows) {
            return y >= 0 && y < cols && x >= 0 && x < rows;
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }
    }

    public static class Field {
        private final int[][] field;
        private final boolean[][] visited;
        private final int cols;
        private final int rows;

        public Field(int cols, int rows) {
            this.rows = rows;
            this.cols = cols;
            this.field = new int[cols][rows];
            this.visited = new boolean[cols][rows];
        }

        public void plantCabbage(Position position) {
            field[position.getY()][position.getX()] = 1;
        }

        public int countCabbageClusters() {
            int count = 0;

            for (int i = 0; i < cols; i++) {
                for (int j = 0; j < rows; j++) {
                    if (field[i][j] == 1 && !visited[i][j]) {
                        exploreCabbageCluster(new Position(i, j));
                        count++;
                    }
                }
            }

            return count;
        }

        private void exploreCabbageCluster(Position position) {
            visited[position.getY()][position.getX()] = true;

            for (int i = 0; i < 4; i++) {
                Position nextPosition = position.getNextPosition(i);

                if (nextPosition.isValid(cols, rows) && field[nextPosition.getY()][nextPosition.getX()] == 1 && !visited[nextPosition.getY()][nextPosition.getX()]) {
                    exploreCabbageCluster(nextPosition);
                }
            }
        }
    }

    public List<Integer> solution(int t, List<int[]> testCases, List<List<Position>> cabbages) {
        List<Integer> results = new ArrayList<>();

        for (int test = 0; test < t; test++) {
            int m = testCases.get(test)[0];
            int n = testCases.get(test)[1];
            int k = testCases.get(test)[2];

            Field field = new Field(n, m);

            for (int i = 0; i < k; i++) {
                Position cabbagePosition = cabbages.get(test).get(i);
                field.plantCabbage(cabbagePosition);
            }

            results.add(field.countCabbageClusters());
        }

        return results;
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            int t = Integer.parseInt(br.readLine());

            List<int[]> testCases = new ArrayList<>();
            List<List<Position>> cabbages = new ArrayList<>();

            for (int test = 0; test < t; test++) {
                String[] firstLine = br.readLine().split(" ");
                int m = Integer.parseInt(firstLine[0]);
                int n = Integer.parseInt(firstLine[1]);
                int k = Integer.parseInt(firstLine[2]);

                testCases.add(new int[]{m, n, k});

                List<Position> cabbagePositions = new ArrayList<>();
                for (int i = 0; i < k; i++) {
                    String[] coordinates = br.readLine().split(" ");
                    int x = Integer.parseInt(coordinates[0]);
                    int y = Integer.parseInt(coordinates[1]);
                    cabbagePositions.add(new Position(y, x));
                }
                cabbages.add(cabbagePositions);
            }

            Main main = new Main();
            List<Integer> results = main.solution(t, testCases, cabbages);

            for (int result : results) {
                bw.write(result + "\n");
            }

            bw.flush();
            br.close();
            bw.close();

        } catch (IOException e) {
            System.err.println("입출력 오류 발생: " + e.getMessage());
        }
    }
}