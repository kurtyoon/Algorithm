import java.io.*;
import java.util.*;

public class Solution {

    public static class SnakeGame {
        private final int n;
        private final int[][] board;
        private final int[][] visited;
        private final List<DirectionChange> directionChanges;
        private final Deque<Position> snake;
        private final int[] dy = {-1, 0, 1, 0};
        private final int[] dx = {0, 1, 0, -1};
        private int currentTime;
        private int currentDirection;
        private int currentIndex;

        public SnakeGame(int n, List<Position> apples, List<DirectionChange> directionChanges) {
            this.n = n;
            this.board = new int[n][n];
            this.visited = new int[n][n];
            this.snake = new ArrayDeque<>();
            this.directionChanges = directionChanges;
            this.currentTime = 0;
            this.currentDirection = 1;
            this.currentIndex = 0;

            for (Position apple : apples) {
                board[apple.y][apple.x] = 1;
            }

            snake.add(new Position(0, 0));
            visited[0][0] = 1;
        }

        public int playGame() {
            while (!snake.isEmpty()) {
                currentTime++;
                Position head = snake.peekFirst();
                int ny = head.y + dy[currentDirection];
                int nx = head.x + dx[currentDirection];

                if (ny >= n || ny < 0 || nx >= n || nx < 0 || visited[ny][nx] == 1) {
                    break;
                }

                if (board[ny][nx] == 1) {
                    board[ny][nx] = 0;
                } else {
                    Position tail = snake.pollLast();
                    visited[tail.y][tail.x] = 0;
                }

                visited[ny][nx] = 1;
                snake.addFirst(new Position(ny, nx));

                if (currentIndex < directionChanges.size() && currentTime == directionChanges.get(currentIndex).time) {
                    currentDirection = (currentDirection + directionChanges.get(currentIndex).direction) % 4;
                    currentIndex++;
                }
            }
            return currentTime;
        }
    }

    public static class Position {
        private final int y;
        private final int x;

        public Position(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static class DirectionChange {
        private final int time;
        private final int direction;

        public DirectionChange(int time, char dir) {
            this.time = time;
            this.direction = (dir == 'D') ? 1 : 3;
        }
    }

    public static int solution(int n, int k, List<int[]> apples, int l, List<String[]> directionChanges) {
        List<Position> appleList = new ArrayList<>();
        List<DirectionChange> directionChangeList = new ArrayList<>();

        for (int[] apple : apples) {
            appleList.add(new Position(apple[0] - 1, apple[1] - 1)); // Adjust for 0-indexing
        }

        for (String[] dir : directionChanges) {
            directionChangeList.add(new DirectionChange(Integer.parseInt(dir[0]), dir[1].charAt(0)));
        }

        SnakeGame game = new SnakeGame(n, appleList, directionChangeList);
        return game.playGame();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());

        List<int[]> apples = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            apples.add(new int[]{y, x});
        }

        st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken());

        List<String[]> directionChanges = new ArrayList<>();
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            String t = st.nextToken();
            String dir = st.nextToken();
            directionChanges.add(new String[]{t, dir});
        }

        int result = solution(n, k, apples, l, directionChanges);
        bw.write(result + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}