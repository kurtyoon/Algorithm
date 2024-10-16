import java.io.*;
import java.util.*;

public class Solution {

    public static class Castle {
        private final int n, m;
        private final int[][] grid;
        private final int[][] visited;
        private final int[] dy = {0, -1, 0, 1};
        private final int[] dx = {-1, 0, 1, 0};
        private final int[] compSize;
        private int roomCount = 0;
        private int largestRoomSize = 0;
        private int largestMergedRoom = 0;

        public Castle(int n, int m, int[][] grid) {
            this.n = n;
            this.m = m;
            this.grid = grid;
            this.visited = new int[m][n];
            this.compSize = new int[m * n + 1];
        }

        public void exploreCastle() {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (visited[i][j] == 0) {
                        roomCount++;
                        compSize[roomCount] = dfs(i, j, roomCount);
                        largestRoomSize = Math.max(largestRoomSize, compSize[roomCount]);
                    }
                }
            }
            calculateLargestMergedRoom();
        }

        private int dfs(int y, int x, int roomId) {
            if (visited[y][x] != 0) return 0;
            visited[y][x] = roomId;
            int roomSize = 1;
            for (int i = 0; i < 4; i++) {
                if ((grid[y][x] & (1 << i)) == 0) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];
                    if (isValidPosition(ny, nx)) {
                        roomSize += dfs(ny, nx, roomId);
                    }
                }
            }
            return roomSize;
        }

        private boolean isValidPosition(int y, int x) {
            return y >= 0 && y < m && x >= 0 && x < n;
        }

        private void calculateLargestMergedRoom() {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i + 1 < m) {
                        int room1 = visited[i + 1][j];
                        int room2 = visited[i][j];
                        if (room1 != room2) {
                            largestMergedRoom = Math.max(largestMergedRoom, compSize[room1] + compSize[room2]);
                        }
                    }
                    if (j + 1 < n) {
                        int room1 = visited[i][j + 1];
                        int room2 = visited[i][j];
                        if (room1 != room2) {
                            largestMergedRoom = Math.max(largestMergedRoom, compSize[room1] + compSize[room2]);
                        }
                    }
                }
            }
        }

        public int getRoomCount() {
            return roomCount;
        }

        public int getLargestRoomSize() {
            return largestRoomSize;
        }

        public int getLargestMergedRoom() {
            return largestMergedRoom;
        }
    }

    public static int[] solution(int n, int m, int[][] grid) {
        Castle castle = new Castle(n, m, grid);
        castle.exploreCastle();
        return new int[]{castle.getRoomCount(), castle.getLargestRoomSize(), castle.getLargestMergedRoom()};
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] grid = new int[m][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] result = solution(n, m, grid);
        bw.write(result[0] + "\n" + result[1] + "\n" + result[2] + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}