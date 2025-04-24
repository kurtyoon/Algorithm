import java.io.*;
import java.util.*;

public class Solution {

    public static class SetOperations {
        private int set = 0;

        public void add(int x) {
            set |= (1 << x);
        }

        public void remove(int x) {
            set &= ~(1 << x);
        }

        public int check(int x) {
            return (set & (1 << x)) != 0 ? 1 : 0;
        }

        public void toggle(int x) {
            set ^= (1 << x);
        }

        public void all() {
            set = (1 << 21) - 1;
        }

        public void empty() {
            set = 0;
        }
    }

    public static List<String> solution(int m, List<String> commands) {
        SetOperations setOps = new SetOperations();
        List<String> results = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            String[] parts = commands.get(i).split(" ");
            String command = parts[0];
            int x = 0;

            if (parts.length > 1) {
                x = Integer.parseInt(parts[1]);
            }

            switch (command) {
                case "add":
                    setOps.add(x);
                    break;
                case "remove":
                    setOps.remove(x);
                    break;
                case "check":
                    results.add(String.valueOf(setOps.check(x)));
                    break;
                case "toggle":
                    setOps.toggle(x);
                    break;
                case "all":
                    setOps.all();
                    break;
                case "empty":
                    setOps.empty();
                    break;
            }
        }

        return results;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int m = Integer.parseInt(br.readLine());
        List<String> commands = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            commands.add(br.readLine());
        }

        List<String> results = solution(m, commands);

        for (String result : results) {
            bw.write(result + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}