import java.io.*;
import java.util.*;

public class Solution {

    public static class Position {
        private final int value;

        public Position(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public List<Position> getNextPositions() {
            return Arrays.asList(
                new Position(value - 1),
                new Position(value + 1),
                new Position(value * 2)
            );
        }

        public boolean isValid() {
            return value >= 0 && value <= 200000;
        }
    }

    public static class PathFinder {
        private static final int MAX = 200000;
        private final int[] visited = new int[MAX + 1];
        private final long[] count = new long[MAX + 1];

        public PathFinder() {
            Arrays.fill(visited, 0);
            Arrays.fill(count, 0);
        }

        public int[] findShortestPath(int start, int target) {
            if (start == target) {
                return new int[]{0, 1};
            }

            Queue<Position> queue = new LinkedList<>();
            visited[start] = 1;
            count[start] = 1;
            queue.add(new Position(start));

            while (!queue.isEmpty()) {
                Position current = queue.poll();

                for (Position next : current.getNextPositions()) {
                    if (next.isValid()) {
                        int nextValue = next.getValue();

                        if (visited[nextValue] == 0) {
                            queue.add(next);
                            visited[nextValue] = visited[current.getValue()] + 1;
                            count[nextValue] += count[current.getValue()];
                        } else if (visited[nextValue] == visited[current.getValue()] + 1) {
                            count[nextValue] += count[current.getValue()];
                        }
                    }
                }
            }

            return new int[]{visited[target] - 1, (int) count[target]};
        }
    }

    public static int[] solution(int start, int target) {
        PathFinder pathFinder = new PathFinder();
        return pathFinder.findShortestPath(start, target);
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] input = br.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            int m = Integer.parseInt(input[1]);

            int[] result = solution(n, m);

            bw.write(result[0] + "\n" + result[1] + "\n");

            bw.flush();
            br.close();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}