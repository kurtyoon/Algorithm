import java.io.*;
import java.util.*;

public class Solution {

    public static class Game {
        private final List<List<Integer>> adj;
        private final int[] diceRolls;
        private final int[] malPositions;
        private final int[] values;
        private final int n = 10;

        public Game(int[] diceRolls) {
            this.diceRolls = diceRolls;
            this.malPositions = new int[4];
            this.values = new int[104];
            this.adj = new ArrayList<>();
            for (int i = 0; i < 54; i++) {
                adj.add(new ArrayList<>());
            }
            setMap();
        }

        private void setMap() {
            for (int i = 0; i <= 19; i++) {
                addEdge(i, i + 1);
            }
            addEdge(5, 21); addEdge(21, 22); addEdge(22, 23); addEdge(23, 24);
            addEdge(10, 25); addEdge(25, 26); addEdge(26, 24);
            addEdge(15, 27); addEdge(27, 28); addEdge(28, 29); addEdge(29, 24);
            addEdge(24, 30); addEdge(30, 31); addEdge(31, 20); addEdge(20, 100);

            values[1] = 2; values[2] = 4; values[3] = 6; values[4] = 8; values[5] = 10;
            values[6] = 12; values[7] = 14; values[8] = 16; values[9] = 18; values[10] = 20;
            values[11] = 22; values[12] = 24; values[13] = 26; values[14] = 28; values[15] = 30;
            values[16] = 32; values[17] = 34; values[18] = 36; values[19] = 38; values[20] = 40;
            values[21] = 13; values[22] = 16; values[23] = 19; values[24] = 25;
            values[25] = 22; values[26] = 24;
            values[27] = 28; values[28] = 27; values[29] = 26;
            values[30] = 30; values[31] = 35;
        }

        private void addEdge(int from, int to) {
            adj.get(from).add(to);
        }

        private int move(int here, int count) {
            if (here == 100) return 100;
            if (adj.get(here).size() >= 2) {
                here = adj.get(here).get(1);
                count--;
            }
            if (count > 0) {
                Queue<Integer> q = new LinkedList<>();
                q.add(here);
                int there = here;
                while (!q.isEmpty()) {
                    int x = q.poll();
                    there = adj.get(x).get(0);
                    q.add(there);
                    if (there == 100) break;
                    count--;
                    if (count == 0) break;
                }
                return there;
            } else {
                return here;
            }
        }

        private boolean isMalPosition(int malIdx, int idx) {
            if (malIdx == 100) return false;
            for (int i = 0; i < 4; i++) {
                if (i == idx) continue;
                if (malPositions[i] == malIdx) return true;
            }
            return false;
        }

        public int playGame(int here) {
            if (here == n) return 0;
            int maxResult = 0;
            for (int i = 0; i < 4; i++) {
                int tmpIdx = malPositions[i];
                int malIdx = move(tmpIdx, diceRolls[here]);
                if (isMalPosition(malIdx, i)) continue;
                malPositions[i] = malIdx;
                maxResult = Math.max(maxResult, playGame(here + 1) + values[malIdx]);
                malPositions[i] = tmpIdx;
            }
            return maxResult;
        }
    }

    public static int solution(int[] diceRolls) {
        Game game = new Game(diceRolls);
        return game.playGame(0);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] diceRolls = new int[10];
        for (int i = 0; i < 10; i++) {
            if (st.hasMoreTokens()) {
                diceRolls[i] = Integer.parseInt(st.nextToken());
            }
        }

        int result = solution(diceRolls);
        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}