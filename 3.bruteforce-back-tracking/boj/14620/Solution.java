import java.util.*;

public class Solution {

    public static class Garden {
        private int n;
        private int[][] flowerMap;
        private int[][] visited;
        private int[] dy = {-1, 1, 0, 0};
        private int[] dx = {0, 0, -1, 1};
        private int minCost = Integer.MAX_VALUE;

        public Garden(int n, int[][] flowerMap) {
            this.n = n;
            this.flowerMap = flowerMap;
            this.visited = new int[n][n];
        }

        private boolean isValidPosition(int y, int x) {
            if (visited[y][x] == 1) return false;
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (ny < 0 || ny >= n || nx < 0 || nx >= n || visited[ny][nx] == 1) {
                    return false;
                }
            }
            return true;
        }

        private int setFlower(int y, int x) {
            visited[y][x] = 1;
            int sum = flowerMap[y][x];
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                visited[ny][nx] = 1;
                sum += flowerMap[ny][nx];
            }
            return sum;
        }

        private void removeFlower(int y, int x) {
            visited[y][x] = 0;
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                visited[ny][nx] = 0;
            }
        }

        public void plantFlowers(int cnt, int cost) {
            if (cnt == 3) {
                minCost = Math.min(minCost, cost);
                return;
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (isValidPosition(i, j)) {
                        int flowerCost = setFlower(i, j);
                        plantFlowers(cnt + 1, cost + flowerCost);
                        removeFlower(i, j);
                    }
                }
            }
        }

        public int getMinCost() {
            return minCost;
        }
    }

    public static int solution(int n, int[][] flowerMap) {
        Garden garden = new Garden(n, flowerMap);
        garden.plantFlowers(0, 0);
        return garden.getMinCost();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[][] flowerMap = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                flowerMap[i][j] = sc.nextInt();
            }
        }

        System.out.println(solution(n, flowerMap));
        sc.close();
    }
}
