import java.io.*;

public class Solution {

    public static class PalindromeReconstructor {
        private final String input;
        private final int[] charCount;
        private boolean isImpossible;
        private String middleCharacter;
        private StringBuilder result;

        public PalindromeReconstructor(String input) {
            this.input = input;

            this.charCount = new int[200];
            this.isImpossible = false;
            this.middleCharacter = "";

            this.result = new StringBuilder();
        }

        public String reconstructPalindrome() {

            for (char c : input.toCharArray()) {
                charCount[c]++;
            }

            for (int i = 'Z'; i >= 'A'; i--) {
                if (charCount[i] > 0) {
                    if (charCount[i] % 2 == 1) {
                        if (!middleCharacter.isEmpty()) {
                            isImpossible = true;
                            break;
                        }
                        middleCharacter = String.valueOf((char) i);
                        charCount[i]--;
                    }

                    for (int j = 0; j < charCount[i]; j += 2) {
                        result.insert(0, (char) i);
                        result.append((char) i);
                    }
                }
            }

            if (isImpossible) {
                return "I'm Sorry Hansoo";
            }

            if (!middleCharacter.isEmpty()) {
                result.insert(result.length() / 2, middleCharacter);
            }

            return result.toString();
        }
    }

    public void solution(String input, BufferedWriter bw) throws IOException {
        PalindromeReconstructor reconstructor = new PalindromeReconstructor(input);
        String result = reconstructor.reconstructPalindrome();
        bw.write(result + "\n");
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String input = br.readLine();

            Main main = new Main();
            main.solution(input, bw);

            bw.flush();
            br.close();
            bw.close();

        } catch (IOException e) {
            System.err.println("오류 발생: " + e.getMessage());
        }
    }
}