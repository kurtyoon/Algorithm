import java.io.*;

public class Solution {

    public static class Position {
        private final int y;
        private final int x;

        public Position(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public Position getNextPosition(int direction, int[] dy, int[] dx) {
            return new Position(this.y + dy[direction], this.x + dx[direction]);
        }

        public boolean isValid(int R, int C) {
            return this.y >= 0 && this.y < R && this.x >= 0 && this.x < C;
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }
    }

    public static class AlphabetPath {
        private final char[][] board;
        private final int R;
        private final int C;
        private final int[] dy = {-1, 0, 1, 0};
        private final int[] dx = {0, 1, 0, -1};
        private int maxPathLength = 0;

        public AlphabetPath(char[][] board, int R, int C) {
            this.board = board;
            this.R = R;
            this.C = C;
        }

        public int getMaxPathLength() {
            return maxPathLength;
        }

        public void findMaxPath(Position start) {
            explore(start, 1 << (board[start.getY()][start.getX()] - 'A'), 1);
        }

        private void explore(Position currentPosition, int visited, int count) {
            maxPathLength = Math.max(maxPathLength, count);

            for (int i = 0; i < 4; i++) {
                Position nextPosition = currentPosition.getNextPosition(i, dy, dx);

                if (nextPosition.isValid(R, C)) {
                    int nextCharMask = 1 << (board[nextPosition.getY()][nextPosition.getX()] - 'A');

                    if ((visited & nextCharMask) == 0) {
                        explore(nextPosition, visited | nextCharMask, count + 1);
                    }
                }
            }
        }
    }

    public static int solution(int R, int C, char[][] board) {
        AlphabetPath alphabetPath = new AlphabetPath(board, R, C);
        alphabetPath.findMaxPath(new Position(0, 0));
        return alphabetPath.getMaxPathLength();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] dimensions = br.readLine().split(" ");
        int R = Integer.parseInt(dimensions[0]);
        int C = Integer.parseInt(dimensions[1]);

        char[][] board = new char[R][C];
        for (int i = 0; i < R; i++) {
            board[i] = br.readLine().toCharArray();
        }

        int result = solution(R, C, board);
        System.out.println(result);
    }
}