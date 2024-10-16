import java.io.*;
import java.util.*;

public class Solution {

    public static class Task {
        private final int deadline;
        private final int value;

        public Task(int deadline, int value) {
            this.deadline = deadline;
            this.value = value;
        }

        public int getDeadline() {
            return deadline;
        }

        public int getValue() {
            return value;
        }
    }

    public static int solution(int n, Task[] tasks) {
        Arrays.sort(tasks, Comparator.comparingInt(Task::getDeadline));
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (Task task : tasks) {
            pq.add(task.getValue());
            if (pq.size() > task.getDeadline()) {
                pq.poll();
            }
        }

        int ret = 0;
        while (!pq.isEmpty()) {
            ret += pq.poll();
        }

        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        Task[] tasks = new Task[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int value = Integer.parseInt(st.nextToken());
            int deadline = Integer.parseInt(st.nextToken());
            tasks[i] = new Task(deadline, value);
        }

        int result = solution(n, tasks);

        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}