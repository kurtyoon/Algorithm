import java.io.*;

public class Solution {

    public static class Combinator {
        private final int[] array;
        private final int targetSum;
        private int count;

        public Combinator(int[] array, int targetSum) {
            this.array = array;
            this.targetSum = targetSum;
            this.count = 0;
        }

        public void findCombinations() {
            for (int i = 0; i < array.length - 1; i++) {
                for (int j = i + 1; j < array.length; j++) {
                    if (array[i] + array[j] == targetSum) {
                        count++;
                    }
                }
            }
        }

        public int getCount() {
            return count;
        }
    }

    public void solution(int m, int[] array, BufferedWriter bw) throws IOException {
        Combinator combinator = new Combinator(array, m);
        combinator.findCombinations();
        bw.write(combinator.getCount() + "\n");
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            int n = Integer.parseInt(br.readLine());
            int m = Integer.parseInt(br.readLine());

            String[] arrayInput = br.readLine().split(" ");
            int[] array = new int[n];

            for (int i = 0; i < n; i++) {
                array[i] = Integer.parseInt(arrayInput[i]);
            }

            Main main = new Main();
            main.solution(m, array, bw);

            bw.flush();
            br.close();
            bw.close();

        } catch (IOException e) {
            System.err.println("입출력 오류 발생: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("숫자 형식 오류: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("잘못된 인덱스 접근: " + e.getMessage());
        }
    }
}