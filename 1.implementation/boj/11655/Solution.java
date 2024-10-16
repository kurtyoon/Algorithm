import java.io.*;

public class Solution {

    public static class ROT13Encoder {
        private final String input;

        public ROT13Encoder(String input) {
            this.input = input;
        }

        public String encode() {
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);

                if (c >= 'A' && c <= 'Z') {
                    if (c + 13 > 'Z') {
                        result.append((char) (c + 13 - 26));
                    } else {
                        result.append((char) (c + 13));
                    }
                }

                else if (c >= 'a' && c <= 'z') {
                    if (c + 13 > 'z') {
                        result.append((char) (c + 13 - 26));
                    } else {
                        result.append((char) (c + 13));
                    }
                }

                else {
                    result.append(c);
                }
            }

            return result.toString();
        }
    }

    public String solution(String input) {
        ROT13Encoder encoder = new ROT13Encoder(input);
        return encoder.encode();
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String input = br.readLine();

            Main main = new Main();
            String result = main.solution(input);

            bw.write(result + "\n");

            bw.flush();
            br.close();
            bw.close();

        } catch (IOException e) {
            System.err.println("오류 발생: " + e.getMessage());
        }
    }
}