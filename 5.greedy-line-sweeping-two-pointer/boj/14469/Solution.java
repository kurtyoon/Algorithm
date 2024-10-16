import java.io.*;
import java.util.*;

public class Solution {

    public static class Task {
        private final int startTime;
        private final int duration;

        public Task(int startTime, int duration) {
            this.startTime = startTime;
            this.duration = duration;
        }

        public int getStartTime() {
            return startTime;
        }

        public int getDuration() {
            return duration;
        }
    }

    public static int solution(int n, List<Task> tasks) {
        tasks.sort(Comparator.comparingInt(Task::getStartTime));

        int realTime = tasks.get(0).getStartTime() + tasks.get(0).getDuration();
        for (int i = 1; i < tasks.size(); i++) {
            realTime = Math.max(realTime, tasks.get(i).getStartTime());
            realTime += tasks.get(i).getDuration();
        }

        return realTime;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        List<Task> tasks = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int startTime = Integer.parseInt(st.nextToken());
            int duration = Integer.parseInt(st.nextToken());
            tasks.add(new Task(startTime, duration));
        }

        int result = solution(n, tasks);

        bw.write(String.valueOf(result));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}