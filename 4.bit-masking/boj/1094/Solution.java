import java.io.*;
import java.util.*;

public class Main {

    public static class PoleCutter {
        private final int targetLength;
        private int cutsRequired;

        public PoleCutter(int targetLength) {
            this.targetLength = targetLength;
            this.cutsRequired = 1;
        }

        public int calculateCuts() {
            int length = targetLength;
            while (length != 1) {
                if ((length & 1) == 1) {
                    cutsRequired++;
                }
                length /= 2;
            }
            return cutsRequired;
        }
    }

    public static int solution(int targetLength) {
        PoleCutter cutter = new PoleCutter(targetLength);
        return cutter.calculateCuts();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int targetLength = Integer.parseInt(br.readLine());
        int result = solution(targetLength);

        bw.write(result + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}