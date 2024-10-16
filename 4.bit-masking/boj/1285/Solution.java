import java.io.*;

public class Solution {

    public static class CoinFlip {
        private final int[] coinStates;
        private final int numCoins;
        private int minFlips;

        public CoinFlip(int numCoins, String[] coinStrings) {
            this.numCoins = numCoins;
            this.coinStates = new int[numCoins + 1];
            this.minFlips = Integer.MAX_VALUE;

            for (int i = 1; i <= numCoins; i++) {
                int value = 1;
                for (int j = 0; j < coinStrings[i - 1].length(); j++) {
                    if (coinStrings[i - 1].charAt(j) == 'T') {
                        coinStates[i] |= value;
                    }
                    value *= 2;
                }
            }
        }

        public void findMinFlips() {
            calculateFlips(1);
        }

        private void calculateFlips(int currentCoin) {
            if (currentCoin == numCoins + 1) {
                int sum = 0;
                for (int i = 1; i <= (1 << (numCoins - 1)); i *= 2) {
                    int count = 0;
                    for (int j = 1; j <= numCoins; j++) {
                        if ((coinStates[j] & i) != 0) {
                            count++;
                        }
                    }
                    sum += Math.min(count, numCoins - count);
                }
                minFlips = Math.min(minFlips, sum);
                return;
            }

            calculateFlips(currentCoin + 1);
            coinStates[currentCoin] = ~coinStates[currentCoin];
            calculateFlips(currentCoin + 1);
            coinStates[currentCoin] = ~coinStates[currentCoin];
        }

        public int getMinFlips() {
            return minFlips;
        }
    }

    public static int solution(int n, String[] coinStrings) {
        CoinFlip coinFlip = new CoinFlip(n, coinStrings);
        coinFlip.findMinFlips();
        return coinFlip.getMinFlips();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] coinStrings = new String[n];

        for (int i = 0; i < n; i++) {
            coinStrings[i] = br.readLine();
        }

        int result = solution(n, coinStrings);
        System.out.println(result);

        br.close();
    }
}