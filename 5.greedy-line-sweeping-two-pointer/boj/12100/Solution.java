import java.io.*;
import java.util.*;

public class Main {

    public static class Board {
        private final int[][] board;
        private final int size;

        public Board(int size, int[][] initialBoard) {
            this.size = size;
            this.board = new int[size][size];
            for (int i = 0; i < size; i++) {
                System.arraycopy(initialBoard[i], 0, this.board[i], 0, size);
            }
        }

        public void rotate90() {
            int[][] temp = new int[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    temp[i][j] = board[size - j - 1][i];
                }
            }
            for (int i = 0; i < size; i++) {
                System.arraycopy(temp[i], 0, board[i], 0, size);
            }
        }

        public void move() {
            int[][] temp = new int[size][size];
            for (int i = 0; i < size; i++) {
                int c = -1, d = 0;
                for (int j = 0; j < size; j++) {
                    if (board[i][j] == 0) continue;
                    if (d != 0 && board[i][j] == temp[i][c]) {
                        temp[i][c] *= 2;
                        d = 0;
                    } else {
                        temp[i][++c] = board[i][j];
                        d = 1;
                    }
                }
                for (c++; c < size; c++) {
                    temp[i][c] = 0;
                }
            }
            for (int i = 0; i < size; i++) {
                System.arraycopy(temp[i], 0, board[i], 0, size);
            }
        }

        public int getMaxValue() {
            int maxValue = 0;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    maxValue = Math.max(maxValue, board[i][j]);
                }
            }
            return maxValue;
        }

        public Board cloneBoard() {
            return new Board(size, this.board);
        }
    }

    public static int findMax(Board board, int depth, int maxDepth) {
        if (depth == maxDepth) {
            return board.getMaxValue();
        }

        int maxValue = 0;
        for (int i = 0; i < 4; i++) {
            Board clonedBoard = board.cloneBoard();
            clonedBoard.move();
            maxValue = Math.max(maxValue, findMax(clonedBoard, depth + 1, maxDepth));
            board.rotate90();
        }

        return maxValue;
    }

    public static int solution(int n, int[][] initialBoard) {
        Board board = new Board(n, initialBoard);
        return findMax(board, 0, 5);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] initialBoard = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                initialBoard[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = solution(n, initialBoard);
        bw.write(result + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}