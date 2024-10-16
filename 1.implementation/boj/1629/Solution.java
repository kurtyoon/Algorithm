import java.io.*;

public class Solution {

    public static class FastExponentiation {
        private final long base;
        private final long exponent;
        private final long mod;

        public FastExponentiation(long base, long exponent, long mod) {
            this.base = base;
            this.exponent = exponent;
            this.mod = mod;
        }

        public long compute() {
            return fastPower(base, exponent);
        }

        private long fastPower(long a, long b) {
            if (b == 1) return a % mod;

            long result = fastPower(a, b / 2);
            result = (result * result) % mod;

            if (b % 2 != 0) {
                result = (result * a) % mod;
            }

            return result;
        }
    }

    public void solution(long a, long b, long c, BufferedWriter bw) throws IOException {
        FastExponentiation fe = new FastExponentiation(a, b, c);
        long result = fe.compute();
        bw.write(result + "\n");
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] input = br.readLine().split(" ");
            long a = Long.parseLong(input[0]);
            long b = Long.parseLong(input[1]);
            long c = Long.parseLong(input[2]);

            Main main = new Main();
            main.solution(a, b, c, bw);

            bw.flush();
            br.close();
            bw.close();

        } catch (IOException e) {
            System.err.println("입출력 오류 발생: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("숫자 형식 오류: " + e.getMessage());
        }
    }
}