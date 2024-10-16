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

    public static class Field {
        private final int[][] area;
        private final boolean[][] visited;
        private final int rows;
        private final int cols;

        public Field(int rows, int cols) {
            this.rows = rows;
            this.cols = cols;
            this.area = new int[rows][cols];
            this.visited = new boolean[rows][cols];
        }

        public void markRectangle(int x1, int y1, int x2, int y2) {
            for (int x = x1; x < x2; x++) {
                for (int y = y1; y < y2; y++) {
                    area[y][x] = 1;
                }
            }
        }

        public int exploreArea(Position start) {
            Stack<Position> stack = new Stack<>();
            stack.push(start);
            visited[start.getY()][start.getX()] = true;

            int areaSize = 1;

            while (!stack.isEmpty()) {
                Position current = stack.pop();

                for (int i = 0; i < 4; i++) {
                    Position nextPosition = current.getNextPosition(i);
                    if (nextPosition.isValid(rows, cols) && !visited[nextPosition.getY()][nextPosition.getX()] && area[nextPosition.getY()][nextPosition.getX()] == 0) {
                        visited[nextPosition.getY()][nextPosition.getX()] = true;
                        stack.push(nextPosition);
                        areaSize++;
                    }
                }
            }

            return areaSize;
        }

        public List<Integer> findAllEmptyAreas() {
            List<Integer> areas = new ArrayList<>();

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (area[i][j] == 0 && !visited[i][j]) {
                        areas.add(exploreArea(new Position(i, j)));
                    }
                }
            }

            return areas;
        }
    }

    public List<Integer> solution(int m, int n, int[][] rectangles) {
        Field field = new Field(m, n);

        for (int[] rect : rectangles) {
            int x1 = rect[0];
            int y1 = rect[1];
            int x2 = rect[2];
            int y2 = rect[3];
            field.markRectangle(x1, y1, x2, y2);
        }

        List<Integer> areas = field.findAllEmptyAreas();
        Collections.sort(areas);
        return areas;
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] firstLine = br.readLine().split(" ");
            int m = Integer.parseInt(firstLine[0]);
            int n = Integer.parseInt(firstLine[1]);
            int k = Integer.parseInt(firstLine[2]);

            int[][] rectangles = new int[k][4];
            for (int i = 0; i < k; i++) {
                String[] rectInput = br.readLine().split(" ");
                rectangles[i][0] = Integer.parseInt(rectInput[0]);
                rectangles[i][1] = Integer.parseInt(rectInput[1]);
                rectangles[i][2] = Integer.parseInt(rectInput[2]);
                rectangles[i][3] = Integer.parseInt(rectInput[3]);
            }

            Main main = new Main();
            List<Integer> areas = main.solution(m, n, rectangles);

            bw.write(areas.size() + "\n");
            for (int area : areas) {
                bw.write(area + " ");
            }
            bw.write("\n");

            bw.flush();
            br.close();
            bw.close();

        } catch (IOException e) {
            System.err.println("입출력 오류 발생: " + e.getMessage());
        }
    }
}