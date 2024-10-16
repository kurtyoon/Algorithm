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
            List<Position> nextPositions = new ArrayList<>();
            nextPositions.add(new Position(this.value + 1));
            nextPositions.add(new Position(this.value - 1));
            nextPositions.add(new Position(this.value * 2));
            return nextPositions;
        }

        public boolean isValid(int maxLimit) {
            return this.value >= 0 && this.value <= maxLimit;
        }
    }

    public static class PathFinder {
        private static final int MAX = 200000;
        private final int[] visited = new int[MAX + 1];
        private final int[] dist = new int[MAX + 1];
        private final List<Integer> path = new ArrayList<>();
        private int ret;

        public int[] findShortestPath(int start, int target) {
            Queue<Position> queue = new LinkedList<>();
            visited[start] = 1;
            queue.add(new Position(start));

            while (!queue.isEmpty()) {
                Position current = queue.poll();

                if (current.getValue() == target) {
                    ret = visited[target];
                    return tracePath(target, start);
                }

                for (Position next : current.getNextPositions()) {
                    if (next.isValid(MAX) && visited[next.getValue()] == 0) {
                        visited[next.getValue()] = visited[current.getValue()] + 1;
                        dist[next.getValue()] = current.getValue();
                        queue.add(next);
                    }
                }
            }
            return null;
        }

        private int[] tracePath(int target, int start) {
            for (int i = target; i != start; i = dist[i]) {
                path.add(i);
            }
            path.add(start);
            Collections.reverse(path);
            return new int[]{ret - 1, path.size()};
        }

        public List<Integer> getPath() {
            return path;
        }
    }

    public static int[] solution(int start, int target, PathFinder pathFinder) {
        return pathFinder.findShortestPath(start, target);
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String[] input = br.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            int k = Integer.parseInt(input[1]);

            PathFinder pathFinder = new PathFinder();
            int[] result = solution(n, k, pathFinder);
            List<Integer> path = pathFinder.getPath();

            bw.write(result[0] + "\n");
            for (int i : path) {
                bw.write(i + " ");
            }
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}