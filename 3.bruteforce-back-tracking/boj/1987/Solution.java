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

    public static class Board {
        private final char[][] board;
        private final int rows, cols;
        private final boolean[] visitedAlphabets;
        private int maxSteps;

        public Board(int rows, int cols, char[][] board) {
            this.rows = rows;
            this.cols = cols;
            this.board = board;
            this.visitedAlphabets = new boolean[26];
            this.maxSteps = 0;
        }

        public void move(Position pos, int steps) {
            maxSteps = Math.max(maxSteps, steps);

            for (int i = 0; i < 4; i++) {
                Position nextPos = pos.getNextPosition(i);
                if (nextPos.isValid(rows, cols)) {
                    char nextChar = board[nextPos.getY()][nextPos.getX()];
                    int nextIdx = nextChar - 'A';
                    if (!visitedAlphabets[nextIdx]) {
                        visitedAlphabets[nextIdx] = true;
                        move(nextPos, steps + 1);
                        visitedAlphabets[nextIdx] = false;
                    }
                }
            }
        }

        public int getMaxSteps() {
            return maxSteps;
        }
    }

    public static int solution(int R, int C, char[][] board) {
        Board gameBoard = new Board(R, C, board);
        Position start = new Position(0, 0);
        gameBoard.visitedAlphabets[board[0][0] - 'A'] = true;
        gameBoard.move(start, 1);
        return gameBoard.getMaxSteps();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        char[][] board = new char[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        int result = solution(R, C, board);
        bw.write(result + "\n");
        bw.flush();
        br.close();
        bw.close();
    }
}
