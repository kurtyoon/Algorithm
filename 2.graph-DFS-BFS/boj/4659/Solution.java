import java.io.*;

public class Solution {

    public static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    public void checkWord(String s, BufferedWriter bw) throws IOException {
        int lcnt = 0;
        int vcnt = 0;
        boolean flag = false;
        boolean hasVowel = false;
        char prev = '\0';

        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);

            if (isVowel(current)) {
                lcnt++;
                vcnt = 0;
                hasVowel = true;
            } else {
                vcnt++;
                lcnt = 0;
            }

            if (vcnt == 3 || lcnt == 3) {
                flag = true;
                break;
            }

            if (i > 0 && current == prev && current != 'e' && current != 'o') {
                flag = true;
                break;
            }

            prev = current;
        }

        if (!hasVowel) {
            flag = true;
        }

        if (flag) {
            bw.write("<" + s + "> is not acceptable.\n");
        } else {
            bw.write("<" + s + "> is acceptable.\n");
        }
    }

    public void solution(BufferedReader br, BufferedWriter bw) throws IOException {
        while (true) {
            String s = br.readLine();

            if (s.equals("end")) {
                break;
            }

            checkWord(s, bw);
        }
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            Solution main = new Solution();
            main.solution(br, bw);

            bw.flush();
            br.close();
            bw.close();

        } catch (IOException e) {
            System.err.println("입출력 오류 발생: " + e.getMessage());
        }
    }
}