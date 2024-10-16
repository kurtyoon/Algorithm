import java.io.*;
import java.util.*;

public class Solution {

    public static class Position {
        private final int x;
        private final int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean isValid(int n, int h) {
            return x >= 1 && x < n && y >= 1 && y <= h;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    public static class Ladder {
        private final int n, h;
        private final int[][] ladderGrid;

        public Ladder(int n, int h) {
            this.n = n;
            this.h = h;
            this.ladderGrid = new int[h + 1][n + 1];
        }

        public void addLine(Position pos) {
            ladderGrid[pos.getY()][pos.getX()] = 1;
        }

        public void removeLine(Position pos) {
            ladderGrid[pos.getY()][pos.getX()] = 0;
        }

        public boolean isValid() {
            for (int i = 1; i <= n; i++) {
                int start = i;
                for (int j = 1; j <= h; j++) {
                    if (ladderGrid[j][start] == 1) start++;
                    else if (ladderGrid[j][start - 1] == 1) start--;
                }
                if (start != i) return false;
            }
            return true;
        }

        public boolean checkGame() {
            return isValid();
        }

        public int getWidth() {
            return n;
        }

        public int getHeight() {
            return h;
        }

        public boolean isLinePresent(Position pos) {
            return ladderGrid[pos.getY()][pos.getX()] == 1;
        }
    }

    public static class GameManager {
        private final Ladder ladder;
        private int minLinesAdded = Integer.MAX_VALUE;

        public GameManager(Ladder ladder) {
            this.ladder = ladder;
        }

        public void addLines(int here, int count) {
            if (count > 3 || count >= minLinesAdded) return;

            if (ladder.checkGame()) {
                minLinesAdded = Math.min(minLinesAdded, count);
                return;
            }

            for (int i = here; i <= ladder.getHeight(); i++) {
                for (int j = 1; j < ladder.getWidth(); j++) {
                    Position pos = new Position(j, i);
                    if (!ladder.isLinePresent(pos) && isValidToAdd(pos)) {
                        ladder.addLine(pos);
                        addLines(i, count + 1);
                        ladder.removeLine(pos);
                    }
                }
            }
        }

        private boolean isValidToAdd(Position pos) {
            int x = pos.getX();
            int y = pos.getY();
            return !ladder.isLinePresent(new Position(x - 1, y)) && !ladder.isLinePresent(new Position(x + 1, y));
        }

        public int getMinLinesAdded() {
            return minLinesAdded == Integer.MAX_VALUE ? -1 : minLinesAdded;
        }
    }

    public static int solution(int n, int m, int h, List<Position> existingLines) {
        Ladder ladder = new Ladder(n, h);

        for (Position pos : existingLines) {
            ladder.addLine(pos);
        }

        GameManager gameManager = new GameManager(ladder);
        gameManager.addLines(1, 0);

        return gameManager.getMinLinesAdded();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        List<Position> existingLines = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            existingLines.add(new Position(b, a));
        }

        int result = solution(n, m, h, existingLines);
        System.out.println(result);
    }
}
