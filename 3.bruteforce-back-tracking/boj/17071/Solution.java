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

        public boolean isValid() {
            return this.value >= 0 && this.value <= 500000;
        }
    }

    public static class Finder {
        private final int[][] visited;
        private final int maxN = 500000;

        public Finder() {
            this.visited = new int[2][maxN + 1];
        }

        public int findMinimumTurns(int start, int target) {
            if (start == target) {
                return 0;
            }

            Queue<Position> queue = new LinkedList<>();
            visited[0][start] = 1;
            queue.add(new Position(start));

            int turn = 1;
            while (!queue.isEmpty()) {
                target += turn;
                if (target > maxN) {
                    break;
                }

                if (visited[turn % 2][target] > 0) {
                    return turn;
                }

                int qSize = queue.size();
                for (int i = 0; i < qSize; i++) {
                    Position current = queue.poll();
                    for (Position next : current.getNextPositions()) {
                        if (next.isValid() && visited[turn % 2][next.getValue()] == 0) {
                            visited[turn % 2][next.getValue()] = visited[(turn + 1) % 2][current.getValue()] + 1;
                            if (next.getValue() == target) {
                                return turn;
                            }
                            queue.add(next);
                        }
                    }
                }
                turn++;
            }

            return -1;
        }
    }

    public static int solution(int start, int target) {
        Finder finder = new Finder();
        return finder.findMinimumTurns(start, target);
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String[] input = br.readLine().split(" ");
            int start = Integer.parseInt(input[0]);
            int target = Integer.parseInt(input[1]);

            int result = solution(start, target);
            bw.write(result + "\n");
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}