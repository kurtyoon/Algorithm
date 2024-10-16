import java.io.*;
import java.util.*;

public class Solution {

    public static class Health {
        private final int a;
        private final int b;
        private final int c;

        public Health(int a, int b, int c) {
            this.a = Math.max(0, a);
            this.b = Math.max(0, b);
            this.c = Math.max(0, c);
        }

        public Health getNextHealth(int[] attackPattern) {
            return new Health(this.a - attackPattern[0], this.b - attackPattern[1], this.c - attackPattern[2]);
        }

        public boolean isZero() {
            return a == 0 && b == 0 && c == 0;
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }

        public int getC() {
            return c;
        }
    }

    public static class GameSimulation {
        private static final int[][] attackPatterns = {
            {9, 3, 1},
            {9, 1, 3},
            {3, 1, 9},
            {3, 9, 1},
            {1, 3, 9},
            {1, 9, 3}
        };
        private final int[][][] visited = new int[64][64][64];
        private final Queue<Health> queue = new LinkedList<>();

        public int solve(Health initialHealth) {
            visited[initialHealth.getA()][initialHealth.getB()][initialHealth.getC()] = 1;
            queue.add(initialHealth);

            while (!queue.isEmpty()) {
                Health current = queue.poll();
                if (current.isZero()) {
                    return visited[0][0][0] - 1;
                }

                for (int[] attackPattern : attackPatterns) {
                    Health nextHealth = current.getNextHealth(attackPattern);
                    if (visited[nextHealth.getA()][nextHealth.getB()][nextHealth.getC()] == 0) {
                        visited[nextHealth.getA()][nextHealth.getB()][nextHealth.getC()] = visited[current.getA()][current.getB()][current.getC()] + 1;
                        queue.add(nextHealth);
                    }
                }
            }
            return -1;
        }
    }

    public static int solution(int n, int[] healthPoints) {
        int a = n >= 1 ? healthPoints[0] : 0;
        int b = n >= 2 ? healthPoints[1] : 0;
        int c = n >= 3 ? healthPoints[2] : 0;

        Health initialHealth = new Health(a, b, c);
        GameSimulation game = new GameSimulation();
        return game.solve(initialHealth);
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            int n = Integer.parseInt(br.readLine());
            int[] healthPoints = new int[3];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                healthPoints[i] = Integer.parseInt(st.nextToken());
            }

            int result = solution(n, healthPoints);
            bw.write(result + "\n");

            bw.flush();
            br.close();
            bw.close();
        } catch (IOException e) {
            System.err.println("입출력 오류 발생: " + e.getMessage());
        }
    }
}