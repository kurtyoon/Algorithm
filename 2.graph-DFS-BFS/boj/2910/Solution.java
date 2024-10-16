import java.io.*;
import java.util.*;

public class Solution {

    public static class NumberFrequency {
        int number;
        int frequency;
        int firstAppearance;

        public NumberFrequency(int number, int frequency, int firstAppearance) {
            this.number = number;
            this.frequency = frequency;
            this.firstAppearance = firstAppearance;
        }
    }

    public static class FrequencyComparator implements Comparator<NumberFrequency> {
        @Override
        public int compare(NumberFrequency o1, NumberFrequency o2) {
            if (o1.frequency != o2.frequency) {
                return Integer.compare(o2.frequency, o1.frequency);
            }
            return Integer.compare(o1.firstAppearance, o2.firstAppearance);
        }
    }

    public void solution(int n, int[] arr) throws IOException {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        Map<Integer, Integer> firstAppearanceMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int number = arr[i];
            frequencyMap.put(number, frequencyMap.getOrDefault(number, 0) + 1);
            firstAppearanceMap.putIfAbsent(number, i);
        }

        List<NumberFrequency> frequencies = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            int number = entry.getKey();
            int frequency = entry.getValue();
            int firstAppearance = firstAppearanceMap.get(number);
            frequencies.add(new NumberFrequency(number, frequency, firstAppearance));
        }

        frequencies.sort(new FrequencyComparator());

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (NumberFrequency freq : frequencies) {
            for (int i = 0; i < freq.frequency; i++) {
                bw.write(freq.number + " ");
            }
        }
        bw.write("\n");
        bw.flush();
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String[] firstLine = br.readLine().split(" ");
            int n = Integer.parseInt(firstLine[0]);
            int c = Integer.parseInt(firstLine[1]);

            int[] arr = new int[n];
            String[] secondLine = br.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(secondLine[i]);
            }

            Solution main = new Solution();
            main.solution(n, arr);

            br.close();
        } catch (IOException e) {
            System.err.println("입출력 오류 발생: " + e.getMessage());
        }
    }
}