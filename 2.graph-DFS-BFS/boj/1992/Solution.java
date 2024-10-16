import java.io.*;

public class Solution {

    public static class QuadTree {
        private final char[][] matrix;

        public QuadTree(char[][] matrix) {
            this.matrix = matrix;
        }

        public String compress(int y, int x, int size) {
            if (size == 1) {
                return String.valueOf(matrix[y][x]);
            }

            char firstValue = matrix[y][x];
            boolean isSame = true;

            for (int i = y; i < y + size; i++) {
                for (int j = x; j < x + size; j++) {
                    if (matrix[i][j] != firstValue) {
                        isSame = false;
                        break;
                    }
                }
                if (!isSame) break;
            }

            if (isSame) {
                return String.valueOf(firstValue);
            } else {
                StringBuilder result = new StringBuilder("(");
                int newSize = size / 2;

                result.append(compress(y, x, newSize));
                result.append(compress(y, x + newSize, newSize));
                result.append(compress(y + newSize, x, newSize));
                result.append(compress(y + newSize, x + newSize, newSize));

                result.append(")");
                return result.toString();
            }
        }
    }

    public String solution(int n, char[][] matrix) {
        QuadTree quadTree = new QuadTree(matrix);
        return quadTree.compress(0, 0, n);
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            int n = Integer.parseInt(br.readLine());
            char[][] matrix = new char[n][n];

            for (int i = 0; i < n; i++) {
                String line = br.readLine();
                matrix[i] = line.toCharArray();
            }

            Solution main = new Solution();
            String result = main.solution(n, matrix);

            bw.write(result + "\n");

            bw.flush();
            br.close();
            bw.close();
        } catch (IOException e) {
            System.err.println("입출력 오류 발생: " + e.getMessage());
        }
    }
}