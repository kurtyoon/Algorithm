import java.io.*;
import java.util.*;

public class Solution {

    public static class Pokedex {
        private final Map<String, Integer> nameToNumber;
        private final Map<Integer, String> numberToName;

        public Pokedex() {
            this.nameToNumber = new HashMap<>();
            this.numberToName = new HashMap<>();
        }

        public void addEntry(String name, int number) {
            nameToNumber.put(name, number);
            numberToName.put(number, name);
        }

        public String findEntry(String query) {

            if (isNumeric(query)) {
                int num = Integer.parseInt(query);
                return numberToName.get(num);
            } else {
                return String.valueOf(nameToNumber.get(query));
            }
        }

        private boolean isNumeric(String query) {
            try {
                Integer.parseInt(query);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }

    public void solution(int n, int m, BufferedReader br, BufferedWriter bw) throws IOException {
        Pokedex pokedex = new Pokedex();

        for (int i = 1; i <= n; i++) {
            String name = br.readLine();
            pokedex.addEntry(name, i);
        }

        for (int i = 0; i < m; i++) {
            String query = br.readLine();
            String result = pokedex.findEntry(query);
            bw.write(result + "\n");
        }
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] input = br.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            int m = Integer.parseInt(input[1]);

            Main main = new Main();
            main.solution(n, m, br, bw);

            bw.flush();
            br.close();
            bw.close();

        } catch (IOException e) {
            System.err.println("오류 발생: " + e.getMessage());
        }
    }
}