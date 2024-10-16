import java.io.*;
import java.util.*;

public class Solution {

    public static class WardrobeCalculator {
        private final Map<String, Integer> clothesMap;

        public WardrobeCalculator() {
            clothesMap = new HashMap<>();
        }

        public void addClothing(String type) {
            clothesMap.put(type, clothesMap.getOrDefault(type, 0) + 1);
        }

        public long calculateCombinations() {
            long result = 1;
            for (int count : clothesMap.values()) {
                result *= (count + 1);
            }
            return result - 1;
        }
    }

    public void solution(int t, BufferedReader br, BufferedWriter bw) throws IOException {
        for (int testCase = 0; testCase < t; testCase++) {
            int n = Integer.parseInt(br.readLine());
            WardrobeCalculator wardrobeCalculator = new WardrobeCalculator();

            for (int i = 0; i < n; i++) {
                String[] input = br.readLine().split(" ");
                String type = input[1];
                wardrobeCalculator.addClothing(type);
            }

            long result = wardrobeCalculator.calculateCombinations();
            bw.write(result + "\n");
        }
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            int t = Integer.parseInt(br.readLine());

            Main main = new Main();
            main.solution(t, br, bw);

            bw.flush();
            br.close();
            bw.close();

        } catch (IOException e) {
            System.err.println("오류 발생: " + e.getMessage());
        }
    }
}