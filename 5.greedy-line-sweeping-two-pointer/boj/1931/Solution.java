import java.io.*;
import java.util.*;

public class Solution {

    public static class Interval implements Comparable<Interval> {
        private final int start;
        private final int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        @Override
        public int compareTo(Interval other) {
            if (this.end == other.end) {
                return this.start - other.start;
            }
            return this.end - other.end;
        }
    }

    public static int solution(int n, List<Interval> intervals) {
        Collections.sort(intervals);

        int count = 1;
        int lastEnd = intervals.get(0).getEnd();

        for (int i = 1; i < n; i++) {
            if (intervals.get(i).getStart() >= lastEnd) {
                count++;
                lastEnd = intervals.get(i).getEnd();
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        List<Interval> intervals = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            intervals.add(new Interval(from, to));
        }

        int result = solution(n, intervals);

        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}