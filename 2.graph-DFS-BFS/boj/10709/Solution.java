import java.io.*;

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

    public static class CloudGrid {
        private final int[][] grid;
        private final int rows;
        private final int cols;

        public CloudGrid(int rows, int cols) {
            this.grid = new int[rows][cols];
            this.rows = rows;
            this.cols = cols;
        }

        public void setCloud(Position position) {
            grid[position.getY()][position.getX()] = 0;
        }

        public void setEmpty(Position position) {
            grid[position.getY()][position.getX()] = -1;
        }

        public void calculateCloudPropagation() {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == 0) {
                        int cnt = 1;

                        while (j + 1 < cols && grid[i][j + 1] == -1) {
                            grid[i][j + 1] = cnt++;
                            j++;
                        }
                    }
                }
            }
        }

        public int[][] getGrid() {
            return grid;
        }
    }

    public int[][] solution(int n, int m, String[] gridData) {
        CloudGrid cloudGrid = new CloudGrid(n, m);

        for (int i = 0; i < n; i++) {
            String row = gridData[i];
            for (int j = 0; j < m; j++) {
                if (row.charAt(j) == '.') {
                    cloudGrid.setEmpty(new Position(i, j));
                } else {
                    cloudGrid.setCloud(new Position(i, j));
                }
            }
        }

        cloudGrid.calculateCloudPropagation();

        return cloudGrid.getGrid();
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] firstLine = br.readLine().split(" ");
            int n = Integer.parseInt(firstLine[0]);
            int m = Integer.parseInt(firstLine[1]);

            String[] gridData = new String[n];
            for (int i = 0; i < n; i++) {
                gridData[i] = br.readLine();
            }

            Solution solution = new Solution();
            int[][] resultGrid = solution.solution(n, m, gridData);

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    bw.write(resultGrid[i][j] + " ");
                }
                bw.write("\n");
            }

            bw.flush();
            br.close();
            bw.close();
        } catch (IOException e) {
            System.err.println("입출력 오류 발생: " + e.getMessage());
        }
    }
}