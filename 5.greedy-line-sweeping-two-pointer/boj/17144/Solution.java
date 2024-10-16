import java.io.*;
import java.util.*;

public class Solution {

    public static class AirPurifier {
        private int[][] room;
        private int[][] temp;
        private int n, m, t;
        private List<int[]> purifier1Path;
        private List<int[]> purifier2Path;
        private static final int[] dy1 = {0, -1, 0, 1};
        private static final int[] dx1 = {1, 0, -1, 0};
        private static final int[] dy2 = {0, 1, 0, -1};
        private static final int[] dx2 = {1, 0, -1, 0};

        public AirPurifier(int n, int m, int t, int[][] room) {
            this.n = n;
            this.m = m;
            this.t = t;
            this.room = room;
            this.temp = new int[54][54];
            this.purifier1Path = new ArrayList<>();
            this.purifier2Path = new ArrayList<>();
        }

        public void start() {
            boolean foundFirstPurifier = false;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (room[i][j] == -1) {
                        if (!foundFirstPurifier) {
                            purifier1Path = createPurifierPath(i, j, dy1, dx1);
                            foundFirstPurifier = true;
                        } else {
                            purifier2Path = createPurifierPath(i, j, dy2, dx2);
                        }
                    }
                }
            }

            while (t-- > 0) {
                spreadDust();
                operatePurifier(purifier1Path);
                operatePurifier(purifier2Path);
            }
        }

        public int getTotalDust() {
            int total = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (room[i][j] != -1) {
                        total += room[i][j];
                    }
                }
            }
            return total;
        }

        private void spreadDust() {
            for (int[] row : temp) {
                Arrays.fill(row, 0);
            }
            Queue<int[]> q = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (room[i][j] > 0) {
                        q.add(new int[]{i, j});
                    }
                }
            }

            while (!q.isEmpty()) {
                int[] curr = q.poll();
                int y = curr[0];
                int x = curr[1];
                int spreadAmount = room[y][x] / 5;

                for (int i = 0; i < 4; i++) {
                    int ny = y + dy1[i];
                    int nx = x + dx1[i];
                    if (ny >= 0 && ny < n && nx >= 0 && nx < m && room[ny][nx] != -1) {
                        temp[ny][nx] += spreadAmount;
                        room[y][x] -= spreadAmount;
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    room[i][j] += temp[i][j];
                }
            }
        }

        private List<int[]> createPurifierPath(int startY, int startX, int[] dy, int[] dx) {
            List<int[]> path = new ArrayList<>();
            int cnt = 0;
            int y = startY;
            int x = startX;

            while (true) {
                int ny = y + dy[cnt];
                int nx = x + dx[cnt];

                if (ny == startY && nx == startX) break;
                if (ny < 0 || ny >= n || nx < 0 || nx >= m) {
                    cnt++;
                    continue;
                }

                path.add(new int[]{ny, nx});
                y = ny;
                x = nx;
            }
            return path;
        }

        private void operatePurifier(List<int[]> path) {
            for (int i = path.size() - 1; i > 0; i--) {
                room[path.get(i)[0]][path.get(i)[1]] = room[path.get(i - 1)[0]][path.get(i - 1)[1]];
            }
            room[path.get(0)[0]][path.get(0)[1]] = 0;
        }
    }

    public static int solution(int n, int m, int t, int[][] room) {
        AirPurifier purifier = new AirPurifier(n, m, t, room);
        purifier.start();

        return purifier.getTotalDust();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        int[][] room = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = solution(n, m, t, room);

        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}