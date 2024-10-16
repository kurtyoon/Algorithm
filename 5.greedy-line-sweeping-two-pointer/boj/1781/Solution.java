import java.io.*;
import java.util.*;

public class Solution {

    public static class TaskProcessor {
        private final List<Task> tasks = new ArrayList<>();
        private final PriorityQueue<Integer> pq = new PriorityQueue<>();
        private int totalScore = 0;

        public void addTask(int deadline, int score) {
            tasks.add(new Task(deadline, score));
        }

        public int processTasks() {
            tasks.sort(Comparator.comparingInt(task -> task.deadline));

            for (Task task : tasks) {
                totalScore += task.score;
                pq.add(task.score);

                if (pq.size() > task.deadline) {
                    totalScore -= pq.poll();
                }
            }

            return totalScore;
        }
    }

    public static class Task {
        private final int deadline;
        private final int score;

        public Task(int deadline, int score) {
            this.deadline = deadline;
            this.score = score;
        }
    }

    public static int solution(int n, int[][] inputTasks) {
        TaskProcessor processor = new TaskProcessor();

        for (int i = 0; i < n; i++) {
            processor.addTask(inputTasks[i][0], inputTasks[i][1]);
        }

        return processor.processTasks();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] inputTasks = new int[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            inputTasks[i][0] = Integer.parseInt(st.nextToken());
            inputTasks[i][1] = Integer.parseInt(st.nextToken());
        }

        int result = solution(n, inputTasks);

        bw.write(String.valueOf(result));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}