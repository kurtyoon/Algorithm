import java.io.*;
import java.util.*;

public class Solution {

    public static class Jewel implements Comparable<Jewel> {
        private final long weight;
        private final long value;

        public Jewel(long weight, long value) {
            this.weight = weight;
            this.value = value;
        }

        public long getWeight() {
            return weight;
        }

        public long getValue() {
            return value;
        }

        @Override
        public int compareTo(Jewel other) {
            return Long.compare(this.weight, other.weight);
        }
    }

    public static long solution(int n, int k, List<Jewel> jewels, List<Long> bags) {
        Collections.sort(jewels);
        Collections.sort(bags);

        PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
        long totalValue = 0;
        int jewelIndex = 0;

        for (long bagCapacity : bags) {
            while (jewelIndex < n && jewels.get(jewelIndex).getWeight() <= bagCapacity) {
                pq.add(jewels.get(jewelIndex).getValue());
                jewelIndex++;
            }
            if (!pq.isEmpty()) {
                totalValue += pq.poll();
            }
        }

        return totalValue;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<Jewel> jewels = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            long weight = Long.parseLong(st.nextToken());
            long value = Long.parseLong(st.nextToken());
            jewels.add(new Jewel(weight, value));
        }

        List<Long> bags = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            bags.add(Long.parseLong(br.readLine()));
        }

        long result = solution(n, k, jewels, bags);

        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}