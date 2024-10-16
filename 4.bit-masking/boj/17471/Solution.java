import java.io.*;
import java.util.*;

public class Solution {

    static class City {
        private final int population;
        private final List<Integer> neighbors;

        public City(int population) {
            this.population = population;
            this.neighbors = new ArrayList<>();
        }

        public int getPopulation() {
            return population;
        }

        public List<Integer> getNeighbors() {
            return neighbors;
        }

        public void addNeighbor(int neighbor) {
            neighbors.add(neighbor);
        }
    }

    static class District {
        private final City[] cities;
        private final int cityCount;
        private final int[] districtLabel;
        private final boolean[] visited;
        private int minDifference = Integer.MAX_VALUE;

        public District(City[] cities, int cityCount) {
            this.cities = cities;
            this.cityCount = cityCount;
            this.districtLabel = new int[cityCount + 1];
            this.visited = new boolean[cityCount + 1];
        }

        public int getMinDifference() {
            return minDifference == Integer.MAX_VALUE ? -1 : minDifference;
        }

        public void calculateMinDifference() {
            for (int i = 1; i < (1 << cityCount) - 1; i++) {
                Arrays.fill(districtLabel, 0);
                Arrays.fill(visited, false);
                int group1Start = -1, group2Start = -1;

                for (int j = 0; j < cityCount; j++) {
                    if ((i & (1 << j)) != 0) {
                        districtLabel[j + 1] = 1;
                        group1Start = j + 1;
                    } else {
                        group2Start = j + 1;
                    }
                }

                if (group1Start == -1 || group2Start == -1) continue;

                Pair group1 = dfs(group1Start, 1);
                Pair group2 = dfs(group2Start, 0);

                if (group1.cityCount + group2.cityCount == cityCount) {
                    minDifference = Math.min(minDifference, Math.abs(group1.populationSum - group2.populationSum));
                }
            }
        }

        private Pair dfs(int cityIndex, int label) {
            visited[cityIndex] = true;
            Pair result = new Pair(1, cities[cityIndex].getPopulation());

            for (int neighbor : cities[cityIndex].getNeighbors()) {
                if (districtLabel[neighbor] != label || visited[neighbor]) continue;
                Pair tempResult = dfs(neighbor, label);
                result.cityCount += tempResult.cityCount;
                result.populationSum += tempResult.populationSum;
            }
            return result;
        }

        private static class Pair {
            private int cityCount;
            private int populationSum;

            public Pair(int cityCount, int populationSum) {
                this.cityCount = cityCount;
                this.populationSum = populationSum;
            }
        }
    }

    public static int solution(int cityCount, int[] populations, List<List<Integer>> adjList) {
        City[] cities = new City[cityCount + 1];

        for (int i = 1; i <= cityCount; i++) {
            cities[i] = new City(populations[i]);
        }

        for (int i = 1; i <= cityCount; i++) {
            for (int neighbor : adjList.get(i)) {
                cities[i].addNeighbor(neighbor);
                cities[neighbor].addNeighbor(i);
            }
        }

        District district = new District(cities, cityCount);
        district.calculateMinDifference();

        return district.getMinDifference();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cityCount = Integer.parseInt(br.readLine());

        int[] populations = new int[cityCount + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= cityCount; i++) {
            populations[i] = Integer.parseInt(st.nextToken());
        }

        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= cityCount; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 1; i <= cityCount; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            for (int j = 0; j < m; j++) {
                int neighbor = Integer.parseInt(st.nextToken());
                adjList.get(i).add(neighbor);
            }
        }

        int result = solution(cityCount, populations, adjList);
        System.out.println(result);
    }
}