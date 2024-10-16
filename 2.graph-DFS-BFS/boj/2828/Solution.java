import java.io.*;

public class Solution {

    public static class Basket {
        private int left;
        private final int size;
        private int movement;

        public Basket(int size) {
            this.size = size;
            this.left = 1;
            this.movement = 0;
        }

        public void catchFruit(int fruitPosition) {
            int right = left + size - 1;

            if (fruitPosition < left) {
                int moveLeft = left - fruitPosition;
                movement += moveLeft;
                left = fruitPosition;
            } else if (fruitPosition > right) {
                int moveRight = fruitPosition - right;
                movement += moveRight;
                left += moveRight;
            }
        }

        public int getMovement() {
            return movement;
        }
    }

    public int solution(int n, int m, int j, int[] fruits) {
        Basket basket = new Basket(m);

        for (int fruitPosition : fruits) {
            basket.catchFruit(fruitPosition);
        }

        return basket.getMovement();
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] firstLine = br.readLine().split(" ");
            int n = Integer.parseInt(firstLine[0]);
            int m = Integer.parseInt(firstLine[1]);

            int j = Integer.parseInt(br.readLine());

            int[] fruits = new int[j];
            for (int i = 0; i < j; i++) {
                fruits[i] = Integer.parseInt(br.readLine());
            }

            Main main = new Main();
            int result = main.solution(n, m, j, fruits);

            bw.write(result + "\n");
            bw.flush();

            br.close();
            bw.close();

        } catch (IOException e) {
            System.err.println("입출력 오류 발생: " + e.getMessage());
        }
    }
}