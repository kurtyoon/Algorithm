import java.io.*;
import java.util.*;

public class Solution {

    public static class MazeSolver {
        private final int[][] maze;
        private final int[][] visited;
        private final int rows, cols;

        private static final int[] dy = {-1, 0, 1, 0};
        private static final int[] dx = {0, 1, 0, -1};

        public MazeSolver(int[][] maze, int n, int m) {
            this.maze = maze;
            this.visited = new int[n][m];
            this.rows = n;
            this.cols = m;
        }

        public int getShortestPathLength() {
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{0, 0});
            visited[0][0] = 1;

            while (!queue.isEmpty()) {
                int[] current = queue.poll();
                int y = current[0];
                int x = current[1];

                for (int i = 0; i < 4; i++) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];

                    if (ny >= 0 && ny < rows && nx >= 0 && nx < cols && maze[ny][nx] == 1 && visited[ny][nx] == 0) {
                        visited[ny][nx] = visited[y][x] + 1;
                        queue.add(new int[]{ny, nx});
                    }
                }
            }

            return visited[rows - 1][cols - 1];
        }
    }

    public int solution(int[][] maze, int n, int m) {
        MazeSolver solver = new MazeSolver(maze, n, m);
        return solver.getShortestPathLength();
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String[] firstLine = br.readLine().split(" ");
            int n = Integer.parseInt(firstLine[0]);
            int m = Integer.parseInt(firstLine[1]);

            int[][] maze = new int[n][m];

            for (int i = 0; i < n; i++) {
                String line = br.readLine();
                for (int j = 0; j < m; j++) {
                    maze[i][j] = line.charAt(j) - '0';
                }
            }

            Main main = new Main();

            System.out.println(main.solution(maze, n, m));

        } catch (IOException e) {
            System.err.println("입출력 오류 발생: " + e.getMessage());
        }
    }
}