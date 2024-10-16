import java.io.*;

public class Solution {

    public static class TitleNumberFinder {

        public int findNthTitleNumber(int n) {
            int count = 0;
            int number = 666;

            while (true) {
                if (Integer.toString(number).contains("666")) {
                    count++;
                }

                if (count == n) {
                    return number;
                }

                number++;
            }
        }
    }

    public static int solution(int n) {
        TitleNumberFinder finder = new TitleNumberFinder();
        return finder.findNthTitleNumber(n);
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            int n = Integer.parseInt(br.readLine());

            int result = solution(n);
            bw.write(result + "\n");

            bw.flush();
            br.close();
            bw.close();

        } catch (IOException e) {
            System.err.println("입출력 오류 발생: " + e.getMessage());
        }
    }
}