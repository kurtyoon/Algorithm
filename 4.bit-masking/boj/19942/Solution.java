import java.io.*;
import java.util.*;

public class Solution {

    static class Nutrient {
        private final int protein;
        private final int fat;
        private final int carbohydrate;
        private final int vitamin;
        private final int cost;

        public Nutrient(int protein, int fat, int carbohydrate, int vitamin, int cost) {
            this.protein = protein;
            this.fat = fat;
            this.carbohydrate = carbohydrate;
            this.vitamin = vitamin;
            this.cost = cost;
        }

        public int getProtein() {
            return protein;
        }

        public int getFat() {
            return fat;
        }

        public int getCarbohydrate() {
            return carbohydrate;
        }

        public int getVitamin() {
            return vitamin;
        }

        public int getCost() {
            return cost;
        }
    }

    public static String solution(int n, int requiredProtein, int requiredFat, int requiredCarbohydrate, int requiredVitamin, Nutrient[] nutrients) {
        int INF = 987654321;
        int minCost = INF;
        Map<Integer, List<List<Integer>>> resultMap = new HashMap<>();

        for (int i = 1; i < (1 << n); i++) {
            int totalProtein = 0, totalFat = 0, totalCarbohydrate = 0, totalVitamin = 0, totalCost = 0;
            List<Integer> selectedIngredients = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    selectedIngredients.add(j + 1);
                    totalProtein += nutrients[j].getProtein();
                    totalFat += nutrients[j].getFat();
                    totalCarbohydrate += nutrients[j].getCarbohydrate();
                    totalVitamin += nutrients[j].getVitamin();
                    totalCost += nutrients[j].getCost();
                }
            }

            if (totalProtein >= requiredProtein && totalFat >= requiredFat && totalCarbohydrate >= requiredCarbohydrate && totalVitamin >= requiredVitamin) {
                if (totalCost <= minCost) {
                    minCost = totalCost;
                    resultMap.putIfAbsent(minCost, new ArrayList<>());
                    resultMap.get(minCost).add(new ArrayList<>(selectedIngredients));
                }
            }
        }

        if (minCost == INF) {
            return "-1";
        } else {
            List<List<Integer>> possibleSolutions = resultMap.get(minCost);
            possibleSolutions.sort(new Comparator<List<Integer>>() {
                @Override
                public int compare(List<Integer> o1, List<Integer> o2) {
                    int size = Math.min(o1.size(), o2.size());
                    for (int i = 0; i < size; i++) {
                        if (!o1.get(i).equals(o2.get(i))) {
                            return o1.get(i) - o2.get(i);
                        }
                    }
                    return o1.size() - o2.size();
                }
            });

            StringBuilder sb = new StringBuilder();
            sb.append(minCost).append("\n");
            for (int ingredient : possibleSolutions.get(0)) {
                sb.append(ingredient).append(" ");
            }
            return sb.toString().trim();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int requiredProtein = Integer.parseInt(st.nextToken());
        int requiredFat = Integer.parseInt(st.nextToken());
        int requiredCarbohydrate = Integer.parseInt(st.nextToken());
        int requiredVitamin = Integer.parseInt(st.nextToken());

        Nutrient[] nutrients = new Nutrient[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int protein = Integer.parseInt(st.nextToken());
            int fat = Integer.parseInt(st.nextToken());
            int carbohydrate = Integer.parseInt(st.nextToken());
            int vitamin = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            nutrients[i] = new Nutrient(protein, fat, carbohydrate, vitamin, cost);
        }

        String result = solution(n, requiredProtein, requiredFat, requiredCarbohydrate, requiredVitamin, nutrients);
        bw.write(result);
        bw.flush();
        br.close();
        bw.close();
    }
}
