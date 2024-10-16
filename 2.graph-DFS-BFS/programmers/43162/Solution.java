class Solution {

    public static class Network {
        private int n;
        private int[][] computers;
        private boolean[] visited;

        public Network(int n, int[][] computers) {
            this.n = n;
            this.computers = computers;
            this.visited = new boolean[n];
        }

        public int countNetworks() {
            int networkCount = 0;

            for (int i = 0; i < n; i++) {
                // 방문하지 않은 노드가 존재한다면
                if (!visited[i]) {
                    networkCount++;
                    dfs(i);
                }
            }

            return networkCount;
        }

        private void dfs(int node) {
            // 방문처리
            visited[node] = true;

            for (int i = 0; i < n; i++) {
                // 연결되어있고, 방문하지 않았다
                if (computers[node][i] == 1 && !visited[i]) {
                    dfs(i);
                }
            }
        }
    }

    public int solution(int n, int[][] computers) {

        Network network = new Network(n, computers);

        return network.countNetworks();
    }
}