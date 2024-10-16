import java.io.*;
import java.util.*;

public class Solution {

    public static class RoadRepair {
        private final List<Pair> intervals;
        private final int m;
        private int idx = 0;
        private int ret = 0;

        public RoadRepair(int m, List<Pair> intervals) {
            this.m = m;
            this.intervals = intervals;
            Collections.sort(this.intervals);
        }

        public int calculateMinimumPlanks() {
            for (Pair interval : intervals) {
                if (interval.second <= idx) continue;

                int b;
                if (idx < interval.first) {
                    b = (interval.second - interval.first) / m + ((interval.second - interval.first) % m != 0 ? 1 : 0);
                    idx = interval.first + b * m;
                } else {
                    b = (interval.second - idx) / m + ((interval.second - idx) % m != 0 ? 1 : 0);
                    idx += b * m;
                }

                ret += b;
            }

            return ret;
        }
    }

    public static class Pair implements Comparable<Pair> {
        int first;
        int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public int compareTo(Pair other) {
            return Integer.compare(this.first, other.first);
        }
    }

    public static int solution(int n, int m, List<Pair> intervals) {
        RoadRepair roadRepair = new RoadRepair(m, intervals);
        return roadRepair.calculateMinimumPlanks();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Pair> intervals = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            intervals.add(new Pair(first, second));
        }

        int result = solution(n, m, intervals);
        bw.write(result + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}