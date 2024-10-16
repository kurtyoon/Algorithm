import java.io.*;
import java.util.*;

public class Solution {

    public static class Position {
        private final int y;
        private final int x;

        public Position(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public int getDistance(Position other) {
            return Math.abs(this.y - other.y) + Math.abs(this.x - other.x);
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }
    }

    public static class Chicken {
        private final int n;
        private final int m;
        private final int[][] grid;
        private final List<Position> homes;
        private final List<Position> chickenShops;
        private final List<List<Integer>> chickenCombinations;
        private int result = Integer.MAX_VALUE;

        public Chicken(int n, int m, int[][] grid, List<Position> homes, List<Position> chickenShops) {
            this.n = n;
            this.m = m;
            this.grid = grid;
            this.homes = homes;
            this.chickenShops = chickenShops;
            this.chickenCombinations = new ArrayList<>();
        }

        public void calculateChickenCombinations() {
            List<Integer> combination = new ArrayList<>();
            generateCombinations(-1, combination);
        }

        private void generateCombinations(int start, List<Integer> combination) {
            if (combination.size() == m) {
                chickenCombinations.add(new ArrayList<>(combination));
                return;
            }
            for (int i = start + 1; i < chickenShops.size(); i++) {
                combination.add(i);
                generateCombinations(i, combination);
                combination.remove(combination.size() - 1);
            }
        }

        public int findMinimumChickenDistance() {
            for (List<Integer> selectedShops : chickenCombinations) {
                int totalDistance = 0;
                for (Position home : homes) {
                    int minDistance = Integer.MAX_VALUE;
                    for (int shopIndex : selectedShops) {
                        Position chickenShop = chickenShops.get(shopIndex);
                        minDistance = Math.min(minDistance, home.getDistance(chickenShop));
                    }
                    totalDistance += minDistance;
                }
                result = Math.min(result, totalDistance);
            }
            return result;
        }
    }

    public static int solution(int n, int m, int[][] grid) {
        List<Position> homes = new ArrayList<>();
        List<Position> chickenShops = new ArrayList<>();

        // 집과 치킨집의 위치를 찾음
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    homes.add(new Position(i, j));
                } else if (grid[i][j] == 2) {
                    chickenShops.add(new Position(i, j));
                }
            }
        }

        Chicken solver = new Chicken(n, m, grid, homes, chickenShops);
        solver.calculateChickenCombinations();
        return solver.findMinimumChickenDistance();
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] firstLine = br.readLine().split(" ");
            int n = Integer.parseInt(firstLine[0]);
            int m = Integer.parseInt(firstLine[1]);

            int[][] grid = new int[n][n];

            for (int i = 0; i < n; i++) {
                String[] line = br.readLine().split(" ");
                for (int j = 0; j < n; j++) {
                    grid[i][j] = Integer.parseInt(line[j]);
                }
            }

            int result = solution(n, m, grid);
            bw.write(result + "\n");

            bw.flush();
            br.close();
            bw.close();

        } catch (IOException e) {
            System.err.println("입출력 오류 발생: " + e.getMessage());
        }
    }
}